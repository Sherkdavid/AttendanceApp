package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Module;
import model.User;
import tools.ServletInterfaceController;
import tools.WrappedMenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by David on 11/04/2017.
 */
public class MenuController {
    private User currentUser;
    private ClassController con;
    private ServletInterfaceController servletInterface;
    @FXML
    Menu classes,modules,department;
    @FXML
    VBox layout;

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void logout(){
        try {
            Parent login = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            BorderPane root = (BorderPane) layout.getScene().getRoot();
            root.setTop(null);
            if(con!=null)
                if(con.hasWindow())
                    con.closeWindow();
            root.setCenter(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void preload()
    {
        HashMap<String,String> params = new HashMap<>();
        params.put("faculty_id", currentUser.getId());
        try {
            ArrayList<model.Class> classList = (ArrayList<model.Class>) servletInterface.sendPostRequest("GetClassByLecturer", params);
            loadClasses(classList);
            ArrayList<Module> moduleList = (ArrayList<Module>) servletInterface.sendPostRequest("GetModuleByFacultyID", params);
            loadModules(moduleList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadModules(ArrayList<Module> list) {
        if (!list.isEmpty()) {
            for (Module mod : list) {
                MenuItem m = new MenuItem(mod.getName());
                m.setOnAction(e -> {
                    BorderPane root = (BorderPane) layout.getScene().getRoot();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/module.fxml"));
                        Parent layout = loader.load();
                        ModuleController modCon = loader.getController();
                        modCon.setServletInterface(servletInterface);
                        modCon.setModule(mod);
                        modCon.preload();
                        root.setCenter(layout);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });
                modules.getItems().add(m);
            }
        }else
        {
            modules.setDisable(true);
            modules.setVisible(false);
        }
    }

    public void loadClasses(ArrayList<model.Class> list) {
        if (!list.isEmpty()) {
            for (model.Class c : list) {
                MenuItem m = new MenuItem(c.getClassId());
                //TODO Use Singleton pattern to open and manage class window
                m.setOnAction(e -> {
                    BorderPane root = (BorderPane) layout.getScene().getRoot();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/class.fxml"));
                        Parent layout = loader.load();
                        con = loader.getController();
                        con.setServletInterface(servletInterface);
                        con.loadView(c.getClassId());
                        root.setCenter(layout);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });
                classes.getItems().add(m);
            }
        }else
        {
            classes.setDisable(true);
            classes.setVisible(false);
        }
    }
}

