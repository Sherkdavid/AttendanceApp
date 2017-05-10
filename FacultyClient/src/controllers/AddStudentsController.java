package controllers;

import com.opencsv.CSVReader;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by David on 09/05/2017.
 */
public class AddStudentsController {
    NavigationController naviCon;
    @FXML
    TableView<Student> table;
    @FXML
    TableColumn idCol,nameCol,emailCol;
    @FXML
    TextField id_field, name_field, email_field;
    public void setNavigationController(NavigationController naviCon) {
    this.naviCon = naviCon;
    idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
    idCol.setEditable(true);
    nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
    nameCol.setEditable(true);
    emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
    emailCol.setEditable(true);
    table.setEditable(true);
    }

    public void addStudent()
    {
        addStudentToTable(id_field.getText(),name_field.getText(),email_field.getText());
        id_field.clear();
        name_field.clear();
        email_field.clear();
    }

    public void addStudentToTable(String id, String name, String email)
    {
        table.getItems().add(new Student(id,name,email));
    }

    public void removeSelected()
    {
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }

    public void confirm()
    {
        naviCon.createStudents(table.getItems());
        cancel();
    }

    public void cancel()
    {
        ((Stage)table.getScene().getWindow()).close();
    }

    public void chooseFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Student File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV File", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(table.getScene().getWindow());
        if(selectedFile!=null)
        {
            try {
                CSVReader reader = new CSVReader(new FileReader(selectedFile));
                List<String[]> entries = reader.readAll();
                for(String[] s: entries)
                {
                    table.getItems().add(new Student(s[0],s[1],s[2]));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
