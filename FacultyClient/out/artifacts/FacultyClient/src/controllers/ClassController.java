package controllers;

import com.opencsv.CSVWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Absence;
import model.Module;
import model.Student;
import tools.ServletInterfaceController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by David on 14/04/2017.
 */
public class ClassController {
    ArrayList<Student> list = null;
    Module mod = null;
    model.Class myClass = null;
    Stage window;
    ServletInterfaceController servletInterface;
    NavigationController navigationController;
    @FXML
    VBox vboxAttendance;
    @FXML
    Label id, title;
    @FXML
    private TableView<Student> students;
    @FXML
    private TableColumn<Student, String> nameCol, idCol;
    private ArrayList<Absence> absences;
    //TODO Create tableview with class list and a listener to open an attendance view for the student
    private boolean windowOpen;

    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void takeAttendance() {
        if (!windowOpen) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/rolecall.fxml"));
                Parent layout = null;
                layout = loader.load();
                RolecallController con = loader.getController();
                con.setServletInterface(servletInterface);
                con.setParent(this);
                con.loadClass(list, myClass);
                window = new Stage();
                window.setTitle(myClass.getClassId());
                window.setScene(new Scene(layout));
                window.show();
                window.setOnCloseRequest(e -> {
                    windowOpen = false;
                });
                windowOpen = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            window.requestFocus();
        }

    }

    public boolean hasWindow() {
        return windowOpen;
    }

    public void closeWindow() {
        if (windowOpen) {
            window.close();
        }
    }

    public void open_enrolment_window() {
        if (window != null)
            if (window.isShowing())
                window.close();
        window = new Stage();
        window.setTitle("Add Enrolments");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/enrol_students.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        EnrolStudentsController con = loader.getController();
        con.setClass(myClass);
        con.setNavigationController(navigationController);
        window.setResizable(false);
        window.setScene(new Scene(layout));
        window.show();
    }

    public void loadView(String classId) {
        windowOpen = false;
        id.setText(classId);
        HashMap<String, String> params = new HashMap();
        params.put("class_id", classId);
        try {
            list = (ArrayList<Student>) servletInterface.sendPostRequest("GetStudentsByClassID", params);
            absences = (ArrayList<Absence>) servletInterface.sendPostRequest("GetAbsenceForStudentByClass", params);
            mod = (Module) servletInterface.sendPostRequest("GetModuleByClassID", params);
            myClass = (model.Class) servletInterface.sendPostRequest("GetClassByID", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        title.setText(mod.getName());
        students.getItems().clear();
        students.getItems().addAll(list);
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        students.getSelectionModel().selectedItemProperty().addListener(e -> {
            loadSelection();
        });
    }

    public void loadSelection() {
        String pattern = "###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        vboxAttendance.getChildren().clear();
        double i = 0;
        for (Absence a : absences) {
            if (a.getStudent().equals(students.getSelectionModel().getSelectedItem().getId()))
                i = i + 1;
        }
        vboxAttendance.getChildren().addAll(new Label("Absences : " + (int) i + " -> " + df.format((i / myClass.getLectureCount()) * 100) + "%"));
    }

    public void refresh() {
        navigationController.refresh();
    }

    public void saveAttendance() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(title.getScene().getWindow());

        if (file != null) {
            saveAttendanceToCSV(file);
        }
    }

    private void saveAttendanceToCSV(File file) {
        try {
            String pattern = "###.##";
            DecimalFormat df = new DecimalFormat(pattern);
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeNext(new String[]{"Name", "ID", "Percentage Absence"});
            for (Student s : students.getItems()) {
                double i = 0;
                for (Absence a : absences) {
                    if (a.getStudent().equals(s.getId()))
                        i++;
                }
                writer.writeNext(new String[]{s.getName(), s.getId(), df.format((i / myClass.getLectureCount()) * 100)+"%"});
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert confirmed = new Alert(Alert.AlertType.INFORMATION);
                confirmed.setTitle("Saved");
                confirmed.setHeaderText(null);
                confirmed.setContentText("CSV Saved at " + file.getPath());
                confirmed.show();
            }
        });
    }
}