package com.example.kieran.attnedancetracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kieran.attnedancetracker.test.GetServletObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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


        // Test for server connection
        Button test = (Button) findViewById(R.id.test_btn);
        test.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                //Instantiate with host name
                GetServletObject post = new GetServletObject("http://138.68.147.88/GroupProject/");
                //create map
                HashMap map = new HashMap();

                map.put("param1","arbitrary_value");
                boolean result = false;
                try {
                    result = (boolean) post.sendPostRequest("TestServlet",map);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                TextView label = (TextView) findViewById(R.id.label);
                label.setText("connection = " + result );

            }
        });
    }

}
