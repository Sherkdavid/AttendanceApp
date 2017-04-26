package controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Student;
import tools.ServletInterfaceController;
import views.RolecallBox;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by David on 20/04/2017.
 */
public class RolecallController {
    private ClassController parent;
    private model.Class sub;
    @FXML
    DatePicker date;
    @FXML
    VBox container;
    @FXML
    ScrollPane scroller;
    ServletInterfaceController servletInterface;

    public void setServletInterface(ServletInterfaceController servletInterface) {
        this.servletInterface = servletInterface;
    }

    public void setParent(ClassController parent) {
        this.parent = parent;
    }

    public void loadClass(ArrayList<Student> students, model.Class sub)
    {
        date.setValue(LocalDate.now());
        for(Student s: students)
        {
            container.getChildren().add(new RolecallBox(s));
        }
        this.sub = sub;
    }

    public void submit()
    {
        ObservableList<Node> nodes = container.getChildren();
        ArrayList<Student> absentees = new ArrayList<>();
        for(Node n: nodes) {
            if (n instanceof RolecallBox) {
                RolecallBox role = (RolecallBox) n;
                if (role.isAbsent()) {
                    absentees.add(((RolecallBox) n).getStudent());
                }
            }
        }
        String message = "";
        for(Student s: absentees)
        {
            message = message + (s.getName() + " : " + s.getId() + "\n");
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Submit with these students marked absent");
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean wait = false;
                    HashMap<String,String> map = new HashMap<>();
                    map.put("class_id", sub.getClassId());

                    try {
                        servletInterface.sendPostRequest("IncrementLectureCount",map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for(Node n: nodes) {
                        if (n instanceof RolecallBox) {
                            if (((RolecallBox) n).isAbsent()) {
                                try {
                                    HashMap<String, String> param = new HashMap();
                                    param.put("student_id", ((RolecallBox) n).getStudent().getId());
                                    param.put("class_id", sub.getClassId());
                                    param.put("date", Timestamp.valueOf(date.getValue().atStartOfDay()).toString());
                                    servletInterface.sendPostRequest("InsertIntoAbsence", param);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            parent.refresh(sub.getClassId());
                            Stage window = (Stage) date.getScene().getWindow();
                            window.fireEvent(new WindowEvent(window,WindowEvent.WINDOW_CLOSE_REQUEST));
                            Alert confirmed = new Alert(Alert.AlertType.INFORMATION);
                            confirmed.setTitle("Confirmed");
                            confirmed.setHeaderText(null);
                            confirmed.setContentText("Attendance has been submitted");
                            confirmed.show();
                        }
                    });}
            }).start();
        } else {
            alert.close();
        }
    }
}
