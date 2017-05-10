package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Department;
import model.Faculty;
import model.Module;
import tools.ServletInterfaceController;
import views.ModuleBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 02/05/2017.
 */
public class DepartmentController {
    Department department;
    NavigationController parentController;
    ServletInterfaceController servletInterface;
    ArrayList<Faculty> f_list;
    @FXML
    ListView<Module> module_list;
    @FXML
    ListView<Faculty> faculty_list;
    @FXML
    Label header;
    @FXML
    Button create_module;
    Stage stage;
    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void create_module_window()  {
        if(stage!=null)
            if(stage.isShowing())
                stage.close();
        stage = new Stage();
        stage.setTitle("Create Department");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/create_module.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateModuleController con = loader.getController();
        con.setNaviCon(parentController);
        con.setDepartment(department,f_list);
        stage.setResizable(false);
        stage.setScene(new Scene(layout));
        stage.show();
    }

    public void create_faculty_window()  {
        if(stage!=null)
            if(stage.isShowing())
                stage.close();
        stage = new Stage();
        stage.setTitle("Create Faculty");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/create_faculty.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateFacultyController con = loader.getController();
        con.setNavigationController(parentController);
        con.setDepartment(department);
        stage.setResizable(false);
        stage.setScene(new Scene(layout));
        stage.show();
    }

    public void setDepartment(Department dep)
    {
        department = dep;
        HashMap<String,String> map = new HashMap();
        map.put("department", dep.getName());
        try {
            ArrayList<Module> list = (ArrayList<Module>) servletInterface.sendPostRequest("GetModulesByDepartment", map);
            f_list = (ArrayList<Faculty>) servletInterface.sendPostRequest("GetFacultyInDepartment", map);
            for(Faculty f : f_list) {
                faculty_list.getItems().add(f);
            }
            for(Module m: list)
            {
                module_list.getItems().add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        header.setText(dep.getName());
    }


    public void setParentController(NavigationController parentController) {
        this.parentController = parentController;
    }
}
