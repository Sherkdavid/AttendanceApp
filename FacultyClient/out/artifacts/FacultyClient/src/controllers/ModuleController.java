package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Module;
import tools.ServletInterfaceController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 22/04/2017.
 */
public class ModuleController {
    @FXML
    private Label id,title;
    @FXML
    HBox container;
    @FXML
    TableView<model.Class> tableview;
    @FXML
    TableColumn<model.Class, String> lecturerCol, idCol;
    @FXML
    BorderPane bp;
    Parent layout = null;
    NavigationController navigationController;
    Module module;
    ServletInterfaceController servletInterface;
    Stage window;

    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void createClass()
    {
        if(window!=null)
        {
            if(window.isShowing())
            {
                window.close();
            }
        }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/create_class.fxml"));
            try {
                layout = loader.load();
                window = new Stage();
                window.setScene(new Scene(layout));
                window.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            CreateClassController con = loader.getController();
            con.setServletInterface(servletInterface);
            con.setModule(module);
            con.setNavigationController(navigationController);
        }


    public void setModule(Module module)
    {
        this.module = module;
    }
    public void preload()
    {
        id.setText(module.getId());
        title.setText(module.getName());
        HashMap<String, String> map = new HashMap<>();
        map.put("module_id", module.getId());
        try {
            ArrayList<model.Class> list = (ArrayList<model.Class>) servletInterface.sendPostRequest("GetClassesByModuleID", map);
            lecturerCol.setCellValueFactory(new PropertyValueFactory<model.Class, String>("lecturerId"));
            idCol.setCellValueFactory(new PropertyValueFactory<model.Class, String>("classId"));
            tableview.getItems().addAll(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
