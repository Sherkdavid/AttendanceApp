package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Absence;
import model.Module;
import model.Student;
import tools.ServletInterfaceController;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 14/04/2017.
 */
public class ClassController {
    ArrayList<Student> list = null;
    Module mod = null;
    model.Class myClass = null;
    Stage window;
    ServletInterfaceController servletInterface;
    @FXML
    VBox vboxAttendance;
    @FXML
    Label id,title;
    @FXML
    private TableView<Student> students;
    @FXML
    private TableColumn<Student,String> nameCol,idCol;
    private ArrayList<Absence> absences;
    //TODO Create tableview with class list and a listener to open an attendance view for the student
    private boolean windowOpen;

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void takeAttendance()
    {
        if(!windowOpen) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/rolecall.fxml"));
                Parent layout = null;
                layout = loader.load();
                RolecallController con = loader.getController();
                con.setServletInterface(servletInterface);
                con.setParent(this);
                con.loadClass(list, myClass);
                window = new Stage();
                window.setTitle(myClass.getClassId());
                window.setScene(new Scene(layout));
                window.show();
                window.setOnCloseRequest(e -> {
                    windowOpen = false;
                });
                windowOpen = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            window.requestFocus();
        }

    }

    public boolean hasWindow()
    {
        return windowOpen;
    }

    public void closeWindow()
    {
        if(windowOpen)
        {
            window.close();
        }
    }

    public void loadView(String classId) {
        windowOpen = false;
        id.setText(classId);
        HashMap<String, String> params = new HashMap();
        params.put("class_id", classId);
        try {
            list = (ArrayList<Student>) servletInterface.sendPostRequest("GetStudentsByClassID", params);
            absences = (ArrayList<Absence>) servletInterface.sendPostRequest("GetAbsenceForStudentByClass", params);
            mod = (Module) servletInterface.sendPostRequest("GetModuleByClassID", params);
            myClass = (model.Class) servletInterface.sendPostRequest("GetClassByID", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        title.setText(mod.getName());
        students.getItems().clear();
        students.getItems().addAll(list);
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        students.getSelectionModel().selectedItemProperty().addListener(e -> {
            loadSelection();
        });
    }

    public void loadSelection()
    {
        String pattern = "###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        vboxAttendance.getChildren().clear();
        double i = 0;
        for (Absence a : absences) {
            if (a.getStudent().equals(students.getSelectionModel().getSelectedItem().getId()))
                i = i + 1;
        }
        vboxAttendance.getChildren().addAll(new Label("Absences : " + (int)i + " -> " + df.format((i / myClass.getLectureCount()) * 100) + "%"));
    }

    public void refresh(String classId)
    {
        HashMap<String, String> params = new HashMap();
        params.put("class_id", classId);
        try
        {
            absences = (ArrayList<Absence>) servletInterface.sendPostRequest("GetAbsenceForStudentByClass", params);
            myClass = (model.Class) servletInterface.sendPostRequest("GetClassByID", params);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        loadSelection();
    }
}
