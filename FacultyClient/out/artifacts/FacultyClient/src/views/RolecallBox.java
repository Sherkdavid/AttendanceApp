package views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Student;

import java.util.Observable;

/**
 * Created by David on 20/04/2017.
 */
public class RolecallBox extends HBox {
    Student student;
    CheckBox check;
    public RolecallBox(Student s)
    {
        super(50);
        check = new CheckBox("Present");
        check.setSelected(true);
        student = s;
        HBox container = new HBox();
        container.setMinWidth(100);
        container.getChildren().add(new Label(s.getName()));
        getChildren().addAll(container,check);
    }

    public boolean isAbsent()
    {
        if(check.isSelected())
            return false;
        else
            return true;
    }

    public Student getStudent() {
        return student;
    }
}
