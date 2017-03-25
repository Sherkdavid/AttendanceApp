package com.example.kieran.attnedancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;


import com.example.kieran.attnedancetracker.modules.Module;
import com.example.kieran.attnedancetracker.test.GetServletObject;

import java.util.ArrayList;


public class ManageAttendanceScreen extends AppCompatActivity {

    //Here to test array list of modules
    ArrayList<Module> moduleList = new ArrayList<Module>();
    Module ex1 = new Module("M001", "program", "L001", "D001");
    Module ex2 = new Module("M002", "network", "L002", "D002");
    Module ex3 = new Module("M003", "software", "L003", "D003");
    Module ex4 = new Module("M004", "hardware", "L004", "D004");

    private Button takeAttendanceBtn, reviewAttendanceBtn;
    private Spinner moduleSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_attendance_screen);

        initializeUI();

    }

    private void initializeUI() {


        moduleSpinner = (Spinner) findViewById(R.id.spinner1);

        //Adding modules to module array list - testing
        ArrayList<Module> moduleList = new ArrayList<>();
        moduleList.add(ex1);
        moduleList.add(ex2);
        moduleList.add(ex3);
        moduleList.add(ex4);




        ArrayAdapter<Module> adapter =
                new ArrayAdapter<Module>(getApplicationContext(), R.layout.simple_spinner_dropdown_item, moduleList);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        moduleSpinner.setAdapter(adapter);

        takeAttendanceBtn = (Button)findViewById(R.id.take_attendance_btn);
        takeAttendanceBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(ManageAttendanceScreen.this,
                        TakeAttendanceScreen.class);
                myIntent.putExtra("Module", moduleSpinner.getSelectedItemPosition());
                startActivity(myIntent);
            }
        });

        reviewAttendanceBtn = (Button)findViewById(R.id.review_student_attendance_btn);
        reviewAttendanceBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(ManageAttendanceScreen.this,
                        TakeAttendanceScreen.class);
                startActivity(myIntent);
            }
        });

    }




}
