package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Department;
import model.Faculty;

import java.util.ArrayList;

/**
 * Created by David on 08/05/2017.
 */
public class CreateModuleController {
    private NavigationController naviCon;
    Department department;
    @FXML
    TextField title, id;
    @FXML
    ComboBox<Faculty> faculty;
    public void setNaviCon(NavigationController naviCon) {
        this.naviCon = naviCon;
    }

    public void createModule()
    {
        if(title.getText()!=null&&id.getText()!=null&&faculty.getValue()!=null)
        naviCon.createModule(title.getText(),id.getText(),faculty.getValue(),department);
        ((Stage)title.getScene().getWindow()).close();
    }

    public void setDepartment(Department department, ArrayList<Faculty> f_list) {
        for(Faculty f: f_list)
        {
            faculty.getItems().add(f);
        }
        this.department = department;
    }
}
