package com.example.kieran.attnedancetracker.UiMenus;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import model.Module;
import model.Class;
import com.example.kieran.attnedancetracker.R;
import com.example.kieran.attnedancetracker.tools.ServletInterfaceController;

import java.util.ArrayList;
import java.util.HashMap;


public class ManageAttendanceScreen extends AppCompatActivity {


    private static final String TAG = "testing" ;
    private Button takeAttendanceBtn, reviewAttendanceBtn;
    private Spinner moduleSpinner;
    String lecturer_id;

    ArrayList<Class> myClassList = new ArrayList<Class>();
    ArrayList<Module> myModules = new ArrayList<Module>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_attendance_screen);


        Bundle bundle = getIntent().getExtras();
        lecturer_id = bundle.getString("id");



        initializeUI();

    }

    private void initializeUI() {

        new GetModules().execute();
        moduleSpinner = (Spinner) findViewById(R.id.spinner1);


        takeAttendanceBtn = (Button)findViewById(R.id.take_attendance_btn);
        takeAttendanceBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(ManageAttendanceScreen.this,
                        TakeAttendanceScreen.class);

                Module test = (Module) moduleSpinner.getSelectedItem();
                String moduleName = test.getName();
                myIntent.putExtra("moduleName", moduleName);

                int a = moduleSpinner.getSelectedItemPosition();
                String class_id = myClassList.get(a).getClassId();
                myIntent.putExtra("class_id", class_id);

                startActivity(myIntent);
            }
        });


        reviewAttendanceBtn = (Button)findViewById(R.id.review_student_attendance_btn);
        reviewAttendanceBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(ManageAttendanceScreen.this,
                        ReviewAttendanceScreen.class);
                Module test = (Module) moduleSpinner.getSelectedItem();
                String moduleName = test.getName();
                String moduleId   = test.getId();

                int a = moduleSpinner.getSelectedItemPosition();
                String class_id = myClassList.get(a).getClassId();
                int lectureCount = myClassList.get(a).getLectureCount();
                myIntent.putExtra("class_id", class_id);
                myIntent.putExtra("lectureCount", lectureCount);

                myIntent.putExtra("moduleName", moduleName);
                myIntent.putExtra("moduleId", moduleId);
                startActivity(myIntent);
            }
        });


    }


    private class GetModules extends AsyncTask<Void,Void,ArrayList<Module>> {

        @Override
        protected ArrayList<Module> doInBackground(Void... params) {

            ArrayList<Module> results = new ArrayList<Module>();
            ServletInterfaceController req = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");

            try {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("faculty_id", lecturer_id);
                myClassList = (ArrayList<Class>)  req.sendPostRequest("GetClassByLecturer",map);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for(int i=0;i<myClassList.size();i++) {
                String class_id = myClassList.get(i).getClassId().toString();

                try {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("class_id", class_id);
                    results.add((Module) req.sendPostRequest("GetModuleByClassID", map));

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }



            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<Module> results) {


            moduleSpinner = (Spinner) findViewById(R.id.spinner1);
            final ArrayAdapter<Module> adapter =
                    new ArrayAdapter<Module>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, results);
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            moduleSpinner.setAdapter(adapter);


        }
    }




}
