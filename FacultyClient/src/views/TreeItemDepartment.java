package views;

import javafx.scene.control.TreeItem;
import model.Department;
import model.Faculty;
import model.Module;
import sun.reflect.generics.tree.Tree;

/**
 * Created by David on 09/05/2017.
 */
public class TreeItemDepartment extends TreeItem{
    TreeItem faculty, modules;
    public TreeItemDepartment(Department d)
    {
        super(d);
        faculty = new TreeItem("Faculty");
        modules = new TreeItem("Modules");
        getChildren().addAll(faculty,modules);
    }

    public void addToFaculty(TreeItem f)
    {
        faculty.getChildren().add(f);
    }

    public void addToModules(TreeItem m)
    {
        modules.getChildren().add(m);
    }

}
