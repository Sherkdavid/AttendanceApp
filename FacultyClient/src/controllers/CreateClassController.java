package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import model.Faculty;
import model.Module;
import tools.ServletInterfaceController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 01/05/2017.
 */
public class CreateClassController {
    @FXML
    TextField class_id;
    @FXML
    ComboBox<Faculty> lecturer;
    @FXML
    Button filechooser, cancel, confirm;
    Module module;
    ServletInterfaceController servletInterface;
    NavigationController navigationController;
    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
        cancel.setOnAction(e-> {
            cancel.getScene().getWindow().fireEvent(new WindowEvent(cancel.getScene().getWindow(),WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        confirm.setOnAction(e-> createClass());
    }

    public void setModule(Module module) {
        this.module = module;
        HashMap<String,String> map = new HashMap<>();
        map.put("department",module.getDepartment());
        try {
            ArrayList<Faculty> list = (ArrayList<Faculty>) servletInterface.sendPostRequest("GetFacultyInDepartment", map);
            for(Faculty f: list)
            {
                lecturer.getItems().add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createClass() {
        navigationController.createClass(class_id.getText(),lecturer.getValue().getId(),module.getId());
        ((Stage)class_id.getScene().getWindow()).close();
    }
}


