package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import tools.ServletInterfaceController;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by David on 11/04/2017.
 */
public class LoginController {
    @FXML
    PasswordField password;
    @FXML
    TextField id;

    public void login()
    {
        ServletInterfaceController serv = new ServletInterfaceController("http://localhost:8080/GroupProject/");
        HashMap<String,String> params = new HashMap<>();
        params.put("faculty_id",id.getText());
        params.put("password",password.getText());
        User logged = null;
        try {
            logged = (User) serv.sendPostRequest("LoginFaculty",params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(logged!=null)
        {
            id.setText("Logging in");
            BorderPane root = (BorderPane) id.getScene().getRoot();
            try {
                root.setCenter(FXMLLoader.load(getClass().getResource("../views/home.fxml")));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/banner.fxml"));
                Parent menu = loader.load();
                MenuController con = loader.getController();
                con.setCurrentUser(logged);
                con.loadClasses();
                root.setTop(menu);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}