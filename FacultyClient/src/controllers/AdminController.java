package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by David on 07/05/2017.
 */
public class AdminController {
    Stage stage;
    @FXML
    MenuItem department, faculty;
    NavigationController naviCon;

    public void setNaviCon(NavigationController naviCon) {
        this.naviCon = naviCon;
    }

    public void add_students_window(){
        if(stage!=null)
            if(stage.isShowing())
                stage.close();
        stage = new Stage();
        stage.setTitle("Create Department");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/add_students.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddStudentsController con = loader.getController();
        con.setNavigationController(naviCon);
        stage.setResizable(false);
        stage.setScene(new Scene(layout));
        stage.show();
    }
    public void create_department_window()  {
        if(stage!=null)
        if(stage.isShowing())
            stage.close();
        stage = new Stage();
        stage.setTitle("Create Department");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/create_department.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CreateDepartmentController con = loader.getController();
        con.setNaviCon(naviCon);
        stage.setResizable(false);
        stage.setScene(new Scene(layout));
        stage.show();
    }

}
