package com.example.sean.attendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sean.attendanceapp.LecturerHomescreen;
import com.example.sean.attendanceapp.R;
import com.example.sean.attendanceapp.StudentHomescreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Test to launch lecturer homescreen
        Button lecturer = (Button) findViewById(R.id.lecturer_btn);
        lecturer.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this,
                        LecturerHomescreen.class);
                startActivity(myIntent);
            }
        });


        //Test to launch student homescreen
        Button student = (Button) findViewById(R.id.student_btn);
        student.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(MainActivity.this,
                        StudentHomescreen.class);
                startActivity(myIntent);
            }
        });

    }
}
