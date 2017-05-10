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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 09/05/2017.
 */
public class EnrolStudentsController {
    model.Class subject;
    NavigationController naviCon;
    @FXML
    TableView<Student> table;
    @FXML
    TableColumn idCol,nameCol,emailCol;
    @FXML
    TextField id_field;

    public void setClass(model.Class c)
    {
        subject = c;
    }

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
        addStudentToTable(id_field.getText());
        id_field.clear();
    }

    public void addStudentToTable(String id)
    {
        HashMap<String,String> map = new HashMap<>();
        map.put("student_id",id);
        try {
            Student student = (Student) naviCon.getServletInterface().sendPostRequest("GetStudentsByID",map);
            table.getItems().add(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeSelected()
    {
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }

    public void confirm()
    {
        naviCon.EnrolStudents(table.getItems(), subject);
        cancel();
    }

    public void cancel()
    {
        ((Stage)table.getScene().getWindow()).close();
    }

    public void chooseFile()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Enrolment File");
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
                    addStudentToTable(s[0]);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
