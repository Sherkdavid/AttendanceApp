package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Class;
import model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by David on 10/05/2017.
 */
public class FacultyProfileController {
    Faculty faculty;
    NavigationController navigationController;
    @FXML
    Label id_label, name_label, department_label;
    @FXML
    ListView listView;
    public void setFaculty(Faculty faculty)
    {
        this.faculty = faculty;
        id_label.setText(faculty.getId());
        name_label.setText(faculty.getName());
        department_label.setText(faculty.getDepartment());
        HashMap<String, String> map = new HashMap();
        map.put("faculty_id",faculty.getId());
        try {
            ArrayList<Class> list = (ArrayList<Class>) navigationController.getServletInterface().sendPostRequest("GetClassByLecturer", map);
            for(model.Class c:list)
            {
                listView.getItems().add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNavigationController(NavigationController navigationController) {
        this.navigationController = navigationController;
    }
}
