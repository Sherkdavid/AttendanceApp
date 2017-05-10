package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.*;
import model.Class;
import tools.ServletInterfaceController;
import views.TreeItemDepartment;
import views.TreeItemModule;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 07/05/2017.
 */
public class NavigationController {
    ServletInterfaceController servletInterface;
    TreeItem active, rootNode, studentRoot, deptRoot;
    @FXML
    TreeView tree;
    @FXML
    BorderPane root;
    User activeUser;
    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
        loadTables();
    }

    public void loadTables(){
        if(activeUser instanceof Faculty)
        {
            tree.setRoot(getFacultyRoot((Faculty) activeUser));
        }
        if(activeUser instanceof Admin){
            tree.setRoot(getAdminRoot());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/admin_menu.fxml"));
            Parent layout = null;
            try {
                layout = loader.load();
                AdminController adminController = loader.getController();
                root.setTop(layout);
                adminController.setNaviCon(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        NavigationController naviCon = this;
        tree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Object item = ((TreeItem)newValue).getValue();
                if(item instanceof Department)
                {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/department.fxml"));
                        Parent layout = loader.load();
                        DepartmentController depcon = loader.getController();
                        root.setCenter(layout);
                        depcon.setServletInterface(servletInterface);
                        depcon.setParentController(naviCon);
                        depcon.setDepartment((Department) item);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    active = (TreeItemDepartment) newValue;
                }
                if(item instanceof Faculty)
                {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/faculty_profile.fxml"));
                        Parent layout = loader.load();
                        FacultyProfileController factCon = loader.getController();
                        factCon.setNavigationController(naviCon);
                        factCon.setFaculty((Faculty) item);
                        root.setCenter(layout);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(item instanceof Module)
                {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/module.fxml"));
                        Parent layout = loader.load();
                        ModuleController modCon = loader.getController();
                        modCon.setServletInterface(servletInterface);
                        modCon.setNavigationController(naviCon);
                        modCon.setModule((Module) item);
                        modCon.preload();
                        root.setCenter(layout);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    active = (TreeItemModule) newValue;
                }
                if(item instanceof model.Class)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/class.fxml"));
                    Parent layout = null;
                    try {
                        layout = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ClassController con = loader.getController();
                    con.setNavigationController(naviCon);
                    con.setServletInterface(servletInterface);
                    con.loadView(((model.Class)item).getClassId());
                    root.setCenter(layout);
                    active =(TreeItem) newValue;
                }
            }
        });
    }

    public ServletInterfaceController getServletInterface()
    {
        return servletInterface;
    }

    public TreeItem<String> getAdminRoot(){
        rootNode = new TreeItem<String>("Admin");
        deptRoot = new TreeItem("Departments");
        studentRoot = new TreeItem<>("Students");
        try {
            ArrayList<Department> deptList = (ArrayList<Department>) servletInterface.sendPostRequest("GetDepartments", new HashMap<>());
            ArrayList<Student> studentList = (ArrayList<Student>) servletInterface.sendPostRequest("GetStudents", new HashMap<>());
            for(Department d: deptList)
            {
                deptRoot.getChildren().add(getDepartmentRoot(d));
            }
            for(Student s: studentList)
            {
                studentRoot.getChildren().add(new TreeItem(s));
            }
            rootNode.getChildren().addAll(deptRoot,studentRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootNode;
    }

    public TreeItem getClassRoot(model.Class c) throws Exception {
        TreeItem classRoot = new TreeItem<model.Class>(c);
        HashMap<String,String> map = new HashMap();
        map.put("class_id", c.getClassId());
        ArrayList<Student> studentList = (ArrayList<Student>) servletInterface.sendPostRequest("GetStudentsByClassID",map);
        for(Student s: studentList)
        {
            classRoot.getChildren().add(new TreeItem<Student>(s));
        }
        return classRoot;
    }

    public TreeItem getModuleRoot(Module module) throws Exception {
        HashMap<String,String> map = new HashMap();
        map.put("module_id",module.getId());
        ArrayList<model.Class> classList = (ArrayList<model.Class>) servletInterface.sendPostRequest("GetClassesByModuleID",map);
        TreeItemModule modRoot = new TreeItemModule(module);
        for(model.Class c: classList)
        {
            modRoot.addToClasses(getClassRoot(c));
        }
        return modRoot;
    }

    public TreeItem getDepartmentRoot(Department department) throws Exception {
        TreeItemDepartment deptRoot = new TreeItemDepartment(department);
        HashMap<String,String> map = new HashMap<>();
        map.put("department",department.getName());
        ArrayList<Module> modList = (ArrayList<Module>) servletInterface.sendPostRequest("GetModulesByDepartment", map);
        ArrayList<Faculty> facultyList = (ArrayList<Faculty>) servletInterface.sendPostRequest("GetFacultyInDepartment", map);
        for(Faculty f: facultyList)
        {
            deptRoot.addToFaculty(new TreeItem(f));
        }
        for(Module m: modList)
        {
            deptRoot.addToModules(getModuleRoot(m));
        }
        return deptRoot;
    }

    public void refresh()
    {
        int x = tree.getSelectionModel().getSelectedIndex();
        tree.getSelectionModel().select(0);
        tree.getSelectionModel().select(x);
    }

    public TreeItem getFacultyRoot(Faculty faculty)
    {
        TreeItem treeRoot = new TreeItem<String>(faculty.getName());
        try {
            HashMap<String,String> params = new HashMap<>();
            params.put("faculty_id", faculty.getId());
            ArrayList<Department> departmentList = (ArrayList<Department>) servletInterface.sendPostRequest("GetDepartmentByHead", params);
            ArrayList<model.Class> classList = (ArrayList<model.Class>) servletInterface.sendPostRequest("GetClassByLecturer", params);
            ArrayList<Module> moduleList = (ArrayList<Module>) servletInterface.sendPostRequest("GetModuleByFacultyID", params);
            if(departmentList.size()>0)
            {
                TreeItem departmentRoot = new TreeItem("Department");
                treeRoot.getChildren().add(departmentRoot);
                for(Department d: departmentList)
                {
                    departmentRoot.getChildren().add(getDepartmentRoot(d));
                }
            }
            else
            {
                if(moduleList.size()>0)
                {
                    TreeItem moduleRoot = new TreeItem("Modules");
                    for(Module m: moduleList)
                    {
                        moduleRoot.getChildren().add(getModuleRoot(m));
                    }
                }
                if(classList.size()>0)
                {
                    TreeItem classRoot = new TreeItem("Classes");
                    for(model.Class c: classList)
                    {
                        classRoot.getChildren().add(getClassRoot(c));
                    }
                }
            }
        }
            catch (Exception e) {
                e.printStackTrace();
            }
            return treeRoot;
    }




    public void createDepartment(String name)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        try {
            servletInterface.sendPostRequest("InsertIntoDepartment",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        deptRoot.getChildren().add(new TreeItemDepartment(new Department(name,null)));
        refresh();
    }
    public void createFaculty(String id, String name, String email, Department department, String password)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("faculty_id",id);
        map.put("name",name);
        map.put("email",email);
        map.put("department",department.getName());
        map.put("password",password);
        try {
            servletInterface.sendPostRequest("InsertIntoFaculty",map);
            servletInterface.sendPostRequest("InsertIntoFacultyLogin",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((TreeItemDepartment)active).addToFaculty(new TreeItem(new Faculty(id,name,email,department.getName())));
        refresh();
    }
    public void createModule(String title, String id, Faculty faculty, Department department)
    {
        HashMap<String, String> map = new HashMap<>();
        map.put("module_id", id);
        map.put("title",title);
        map.put("faculty_id",faculty.getId());
        map.put("department",department.getName());
        try {
            servletInterface.sendPostRequest("InsertIntoModule",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((TreeItemDepartment)active).addToModules(new TreeItem(new Module(id,title,department.getName(),faculty.getId())));
        refresh();
    }
    public void createClass(String class_id, String faculty_id, String module_id)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("class_id",class_id);
        map.put("lecturer_id",faculty_id);
        map.put("module_id",module_id);
        try {
            servletInterface.sendPostRequest("InsertIntoClass",map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((TreeItemModule)active).addToClasses(new TreeItem(new model.Class(class_id,module_id,faculty_id,0)));
        refresh();
    }

    public void createStudents(ObservableList<Student> items) {
        for(Student s: items)
        {
            HashMap<String, String> map = new HashMap();
            map.put("student_id", s.getId());
            map.put("name",s.getName());
            map.put("year","0");
            map.put("email", s.getEmail());
            try {
                servletInterface.sendPostRequest("InsertIntoStudent",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            studentRoot.getChildren().add(new TreeItem(s));
        }
    }

    public void EnrolStudents(ObservableList<Student> items, model.Class subject) {
        for(Student s: items)
        {
            HashMap<String, String> map = new HashMap();
            map.put("student_id", s.getId());
            map.put("class_id",subject.getClassId());
            map.put("enrol_date", Timestamp.valueOf(LocalDateTime.now()).toString());
            try {
                servletInterface.sendPostRequest("InsertIntoEnrolment",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            active.getChildren().add(new TreeItem(s));
            refresh();
        }
    }
}
