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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    
					//Network actions must be carried out on a seperate thread to the main UI thread
					new Thread(new Runnable() {
                        @Override
                        public void run() {
							//Create GetServletObject with host URL
                            GetServletObject get = new GetServletObject("http://138.68.147.88:8080/GroupProject/");
                            try {
								//Any UI Components you want to call in the thread must be instantiated in thread.
                                final TextView text = (TextView) findViewById(R.id.textView);
								//Cast for object type
                                boolean b = (boolean) get.sendPostRequest("TestServlet", new HashMap<String, String>());
                                if (b)
                                    text.setText("It Works");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}