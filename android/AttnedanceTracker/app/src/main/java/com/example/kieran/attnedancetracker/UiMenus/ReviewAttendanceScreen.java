package com.example.kieran.attnedancetracker.UiMenus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.kieran.attnedancetracker.CustomAdapterReviewAttendance;

import model.Absence;
import model.Student;
import com.example.kieran.attnedancetracker.R;
import com.example.kieran.attnedancetracker.tools.ServletInterfaceController;
import com.opencsv.CSVWriter;

public class ReviewAttendanceScreen extends AppCompatActivity {


    ListView listViewOfStudents;
    String class_Id;
    int lectureCount;
    ArrayList<Student> studentList = new ArrayList<>();
    ArrayList<Absence> absenceList = new ArrayList<>();

    private TextView dateLabel, classListLabel; //Will be used to display todays date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_attendance_screen);


        Bundle bundle = getIntent().getExtras();
        String moduleName = bundle.getString("moduleName");
        String moduleId = bundle.getString("moduleId");
        class_Id = bundle.getString("class_id");
        lectureCount = bundle.getInt("lectureCount");



        //Setting nsme of class to be displayed
        classListLabel = (TextView) findViewById(R.id.classListLabel);
        classListLabel.setText(moduleName);

        //Getting and settingdate to be displayed
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = sdf.format(date);
        dateLabel = (TextView) findViewById(R.id.todaysDateLabel);
        dateLabel.setText("Todays date: "+ dateString);

        new GetStudents().execute();

        Button myButton = (Button) findViewById(R.id.cancelBtn);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });

        Button savecsvBtn = (Button) findViewById(R.id.savecsvBtn);
        savecsvBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                savecsv();
                finish();

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class GetStudents extends AsyncTask<Void,Void,ArrayList<Absence>> {

        @Override
        protected ArrayList<Absence> doInBackground(Void... params) {

            ArrayList<Absence> results = new ArrayList<Absence>();

            HashMap<String, String> map = new HashMap();
            map.put("class_id", class_Id);

            try {

                ServletInterfaceController req = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
               // results = (ArrayList<Absence>) req.sendPostRequest("GetAbsenceForStudentByClass", map);
                studentList = (ArrayList<Student>) req.sendPostRequest("GetStudentsByClassID", map);
                absenceList = (ArrayList<Absence>) req.sendPostRequest("GetAbsenceForStudentByClass", map);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<Absence> results) {

            displayResults();



        }
    }

    public void displayResults()
    {
        listViewOfStudents = (ListView) findViewById(R.id.listView1);
        CustomAdapterReviewAttendance adapter = new CustomAdapterReviewAttendance(this, studentList, absenceList, lectureCount);
        listViewOfStudents.setAdapter(adapter);

    }


    public void savecsv()
    {
        String filename = "attendnace_"+class_Id+".csv";

        File file = new File(Environment.getExternalStorageDirectory(), filename);




        saveAttendanceToCSV(file);

    }

    private void saveAttendanceToCSV(File file) {
        try {
            String pattern = "###.##";
            DecimalFormat df = new DecimalFormat(pattern);
            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeNext(new String[]{"Name", "ID", "Attendance"});

            for(int i=0;i<studentList.size();i++)
            {
                double numberOfAbsences = 0;
                for (int x = 0; x<absenceList.size(); x++)
                {
                    if (studentList.get(i).getId().equals(absenceList.get(x).getStudent()))
                    {
                        numberOfAbsences = numberOfAbsences +1;
                    }
                }
                writer.writeNext(new String[]{studentList.get(i).getName(), studentList.get(i).getId(),
                        df.format(((lectureCount -numberOfAbsences) / lectureCount) * 100)+"%"});
            }

            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
