package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Admin;
import model.User;
import tools.ServletInterfaceController;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by David on 11/04/2017.
 */
public class LoginController implements Initializable {
    @FXML
    PasswordField password;
    @FXML
    TextField id;
    @FXML
    VBox container;
    @FXML
    Button goButton;

    public void login() {
        ServletInterfaceController serv = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
        if (id.getText().equals("admin")) {
            BorderPane root = (BorderPane) id.getScene().getRoot();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/navigation.fxml"));
                Parent adminPane = loader.load();
                NavigationController con = loader.getController();
                con.setServletInterface(serv);
                con.setActiveUser(new Admin());
                root.setCenter(adminPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            HashMap<String, String> params = new HashMap<>();
            params.put("faculty_id", id.getText());
            params.put("password", password.getText());
            User logged = null;
            try {
                logged = (User) serv.sendPostRequest("LoginFaculty", params);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (logged != null) {
                id.setText("Logging in");
                BorderPane root = (BorderPane) id.getScene().getRoot();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/navigation.fxml"));
                    Parent adminPane = loader.load();
                    NavigationController con = loader.getController();
                    con.setServletInterface(serv);
                    con.setActiveUser(logged);
                    root.setCenter(adminPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        container.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                    goButton.fire();
                ev.consume();
            }
        });
    }
}