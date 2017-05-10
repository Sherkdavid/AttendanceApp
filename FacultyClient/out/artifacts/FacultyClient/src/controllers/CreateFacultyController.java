package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Department;

/**
 * Created by David on 08/05/2017.
 */
public class CreateFacultyController {
    NavigationController naviCon;
    Department department;
    @FXML
    TextField name, id, email,password;
    @FXML
    Button confirm, cancel;


    public void setNavigationController(NavigationController naviCon)
    {
        this.naviCon = naviCon;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void createFaculty()
    {
        naviCon.createFaculty(id.getText(),name.getText(),email.getText(),department,password.getText());
        ((Stage)email.getScene().getWindow()).close();
    }
}
