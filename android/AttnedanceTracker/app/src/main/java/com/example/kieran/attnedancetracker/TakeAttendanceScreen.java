package com.example.kieran.attnedancetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.kieran.attnedancetracker.users.Student;

public class TakeAttendanceScreen extends AppCompatActivity {


    ListView lv;
    Model[] modelItems;
   // Intent i = getIntent();
    //Module a  = (Module)i.getExtras();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance_screen);

        Student[] studentList = {
                new Student("001","alan","dnet","alan@cit",1),
                new Student("002","bob","dnet","bob@cit",1),
                new Student("003","mike","dnet","mike@cit",1),
                new Student("004","jane","dnet","jane@cit",1),
                new Student("005","jack","dnet","jack@cit",1),
                new Student("001","alan","dnet","alan@cit",1),
                new Student("002","bob","dnet","bob@cit",1),
                new Student("003","mike","dnet","mike@cit",1),
                new Student("004","jane","dnet","jane@cit",1),
                new Student("005","jack","dnet","jack@cit",1),
        };

        lv = (ListView) findViewById(R.id.listView1);
        CustomAdapter adapter = new CustomAdapter(this, studentList);
        lv.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
