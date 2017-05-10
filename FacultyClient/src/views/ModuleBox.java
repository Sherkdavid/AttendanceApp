package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.Module;
import model.Student;

/**
 * Created by David on 02/05/2017.
 */
public class ModuleBox extends HBox {
    Module module;
    public ModuleBox(Module m)
    {
        super(50);
        getChildren().addAll(new Label(m.getName()),new Label(m.getId()),new Label(m.getLecturer()));
    }

    public Module getModule()
    {
        return module;
    }
}
