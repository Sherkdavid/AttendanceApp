package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import model.User;
import tools.ServletInterfaceController;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by David on 11/04/2017.
 */
public class MenuController {
    private User currentUser;

    @FXML
    Menu classes;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void loadClasses()
    {
        ServletInterfaceController server = new ServletInterfaceController("http://localhost:8080/GroupProject/");
        HashMap<String,String> params = new HashMap<>();
        params.put("lecturer_id", currentUser.getId());
        try {
            ArrayList<model.Class> list = (ArrayList<model.Class>) server.sendPostRequest("GetClassByLecturer", params);
            for(model.Class c: list)
            {
                MenuItem m = new MenuItem(c.getClassId());
              //  m.setOnAction(e->  ); //TODO pass class_id to class manager window
                classes.getItems().add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
