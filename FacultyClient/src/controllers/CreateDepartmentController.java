package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by David on 07/05/2017.
 */
public class CreateDepartmentController {
    NavigationController naviCon;
    @FXML
    TextField name;
    public void setNaviCon(NavigationController naviCon) {
        this.naviCon = naviCon;
    }

    public void createDepartment()
    {
        naviCon.createDepartment(name.getText());
        closeWindow();
    }

    public void closeWindow()
    {
        ((Stage)name.getScene().getWindow()).close();
    }
}
