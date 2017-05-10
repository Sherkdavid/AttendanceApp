package views;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import model.Module;

/**
 * Created by David on 09/05/2017.
 */
public class TreeItemModule extends TreeItem{

    TreeItem classes;
    public TreeItemModule(Module m)
    {
        super(m, new Label(m.getId()));
        classes = new TreeItem("Classes");
        getChildren().addAll(classes);
    }

    public void addToClasses(TreeItem c)
    {
        classes.getChildren().add(c);
    }
}
