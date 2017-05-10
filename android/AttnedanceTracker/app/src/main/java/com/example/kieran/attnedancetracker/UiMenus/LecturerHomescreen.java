package com.example.kieran.attnedancetracker.UiMenus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.kieran.attnedancetracker.R;


public class LecturerHomescreen extends AppCompatActivity {


    private Button manageAttendanceBtn, logOutBtn;
    String lecturer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_homescreen);

        Bundle bundle = getIntent().getExtras();
        lecturer_id = bundle.getString("id");


        //Navigate to manage attendance
        manageAttendanceBtn = (Button)findViewById(R.id.manage_attendance_btn);
        manageAttendanceBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent myIntent = new Intent(LecturerHomescreen.this,
                        ManageAttendanceScreen.class);
                myIntent.putExtra("id", lecturer_id);
                startActivity(myIntent);
            }
        });

        //Navigate to manage attendance
        logOutBtn = (Button)findViewById(R.id.logout_btn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                finish();
            }
        });

    }


}
