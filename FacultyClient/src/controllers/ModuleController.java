package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Module;
import tools.ServletInterfaceController;

/**
 * Created by David on 22/04/2017.
 */
public class ModuleController {
    @FXML
    private Label id,title;
    Module module;
    ServletInterfaceController servletInterface;

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void setModule(Module module)
    {
        this.module = module;
    }
    public void preload()
    {
        id.setText(module.getId());
        title.setText(module.getName());
    }
}
