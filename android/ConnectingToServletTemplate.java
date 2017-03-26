package com.example.david.misc;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private class TestTask extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean b = false;
            try {
                b= (boolean) new GetServletObject("http://138.68.147.88:8080/GroupProject/").sendPostRequest("TestServlet", new HashMap<String, String>());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return b;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            final TextView text = (TextView) findViewById(R.id.textView);
            if(b)
                text.setText("True");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new TestTask().execute();

            }

        });
    }
}