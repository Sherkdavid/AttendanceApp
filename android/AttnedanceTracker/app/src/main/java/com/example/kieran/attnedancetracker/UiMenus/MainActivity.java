package com.example.kieran.attnedancetracker.UiMenus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import model.User;
import com.example.kieran.attnedancetracker.R;
import com.example.kieran.attnedancetracker.tools.GetServletObject;
import com.example.kieran.attnedancetracker.tools.ServletInterfaceController;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {


    EditText idInput, passwordInput;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Test to login
        Button login = (Button) findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                login();
            }
        });

    }


    public void login() {

        idInput = (EditText) findViewById(R.id.userID);
        passwordInput = (EditText) findViewById(R.id.password);

        username = idInput.getText().toString();
        password = passwordInput.getText().toString();


        new Logging().execute();

    }


    private class Logging extends AsyncTask<Void,Void,User> {

        @Override
        protected User doInBackground(Void... params) {

            ServletInterfaceController serv = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
            HashMap<String,String> map = new HashMap<>();
            map.put("faculty_id",username);
            map.put("password",password);
            User logged = null;
            try {
                logged = (User) serv.sendPostRequest("LoginFaculty",map);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return logged;
        }

        @Override
        protected void onPostExecute(User logged) {

            if(logged!=null)
            {
                Intent myIntent = new Intent(MainActivity.this,
                        LecturerHomescreen.class);
                myIntent.putExtra("id", logged.getId());
                startActivity(myIntent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Wrong User or password", Toast.LENGTH_LONG).show();
            }
        }
    }





}
