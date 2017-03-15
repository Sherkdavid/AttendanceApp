package com.example.sean.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LecturerHomescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_homescreen);


        //Test to launch view attendance
        Button takeAttendance = (Button) findViewById(R.id.manage_attendance_btn);
        takeAttendance.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(LecturerHomescreen.this,
                        TakeAttendance.class);
                startActivity(myIntent);
            }
        });


        //launch Review attendance
        Button reviewAttendance = (Button) findViewById(R.id.review_attendance_btn);
        reviewAttendance.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(LecturerHomescreen.this,
                        ReviewAttendance.class);
                startActivity(myIntent);
            }
        });
    }
}
