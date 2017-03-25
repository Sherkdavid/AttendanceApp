package com.example.kieran.attnedancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LecturerHomescreen extends AppCompatActivity {


    private Button manageAttendanceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_homescreen);

        //Navigate to manage attendance
        manageAttendanceBtn = (Button)findViewById(R.id.manage_attendance_btn);
        manageAttendanceBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(LecturerHomescreen.this,
                        ManageAttendanceScreen.class);
                startActivity(myIntent);
            }
        });

    }


}
