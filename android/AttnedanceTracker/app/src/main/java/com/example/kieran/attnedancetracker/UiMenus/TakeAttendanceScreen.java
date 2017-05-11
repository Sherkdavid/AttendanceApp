package com.example.kieran.attnedancetracker.UiMenus;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Date;

import com.example.kieran.attnedancetracker.R;
import com.example.kieran.attnedancetracker.tools.StudentSelector;
import com.example.kieran.attnedancetracker.tools.ServletInterfaceController;
import com.opencsv.CSVWriter;

import model.Student;

public class TakeAttendanceScreen extends Activity {

    private static final String TAG = "testing" ;

    ArrayList<Student> studentList = new ArrayList<Student>();
    MyCustomAdapter dataAdapter = null;
    String class_id;
    private TextView dateLabel, classListLabel; //Will be used to display todays date


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance_screen);

        Bundle bundle = getIntent().getExtras();
        class_id = bundle.getString("class_id");
        String moduleName = bundle.getString("moduleName");

        classListLabel = (TextView) findViewById(R.id.classListLabel);
        classListLabel.setText(moduleName);

        //Getting and settingdate to be displayed
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = sdf.format(date);
        dateLabel = (TextView) findViewById(R.id.todaysDateLabel);
        dateLabel.setText("Todays date: "+ dateString);


        new GetStudents().execute();
        //Generate list View from ArrayList
        //displayListView();

        checkButtonClick();

        Button myButton = (Button) findViewById(R.id.cancelBtn);
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }

    private void displayListView() {

        //Array list of students converted in StttudentSelector
        ArrayList<StudentSelector> studentSelectorList = new ArrayList<StudentSelector>();
        for (int i=0;i<studentList.size();i++)
        {
            StudentSelector student = new StudentSelector(studentList.get(i).getId(),studentList.get(i).getName(),true);
            studentSelectorList.add(student);
        }

        //create an ArrayAdaptar from StudentSelectorList
        dataAdapter = new MyCustomAdapter(this,
                R.layout.list_item, studentSelectorList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

    }

    private class MyCustomAdapter extends ArrayAdapter<StudentSelector> {

        private ArrayList<StudentSelector> studentSelectorList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<StudentSelector> studentSelectorList) {
            super(context, textViewResourceId, studentSelectorList);
            this.studentSelectorList = new ArrayList<StudentSelector>();
            this.studentSelectorList.addAll(studentSelectorList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_item, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);


                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        StudentSelector studentSelector = (StudentSelector) cb.getTag();
                        studentSelector.setSelected(cb.isChecked());
                    }
                });

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            StudentSelector studentSelector = studentSelectorList.get(position);
            holder.code.setText(" (" +  studentSelector.getCode() + ")");
            holder.name.setText(studentSelector.getName());
            holder.name.setChecked(studentSelector.isSelected());
            holder.name.setTag(studentSelector);

            return convertView;

        }

    }

    //Executed once save attendance is clicked
    private void checkButtonClick() {

        Button myButton = (Button) findViewById(R.id.findSelected);
        myButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                new SendAbsences().execute();


                StringBuffer responseText = new StringBuffer();
                responseText.append("The following are marked abscent...\n");
                ArrayList<StudentSelector> studentSelectorList = dataAdapter.studentSelectorList;
                for(int i = 0; i< studentSelectorList.size(); i++){
                    StudentSelector studentSelector = studentSelectorList.get(i);
                    if(!studentSelector.isSelected()){
                        responseText.append("\n" + studentSelector.getName());
                    }
                }
                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

                finish();

            }
        });

    }

    //Retrieves list of student to be displayed
    private class GetStudents extends AsyncTask<Void,Void,ArrayList<Student>> {

        @Override
        protected ArrayList<Student> doInBackground(Void... params) {

            ArrayList<Student> results = new ArrayList<Student>();
            Log.d(TAG, "doInBackground: " + class_id);

            try {
                ServletInterfaceController req = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("class_id", class_id);
                results = (ArrayList<Student>)  req.sendPostRequest("GetStudentsByClassID",map);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(ArrayList<Student> results) {
            studentList=results;
            displayListView();
        }
    }


    private class SendAbsences extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {

            String result = null;

            ServletInterfaceController req = new ServletInterfaceController("http://138.68.147.88:8080/GroupProject/");
            HashMap<String,String> map = new HashMap<>();
            map.put("class_id", class_id);

            Timestamp a = new Timestamp(System.currentTimeMillis());
            String date = a.toString();
            Log.d(TAG, "time stampppppppp : " + a);


            //Increments the lecture count
            try {
                req.sendPostRequest("IncrementLectureCount",map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Sends data to servlet if student is marked as abscent
            ArrayList<StudentSelector> studentSelectorList = dataAdapter.studentSelectorList;
            for(int i = 0; i< studentSelectorList.size(); i++){
                StudentSelector studentSelector = studentSelectorList.get(i);
                if(!studentSelector.isSelected()){
                    try {
                        HashMap<String, String> param = new HashMap();
                        param.put("student_id", studentSelectorList.get(i).getCode());
                        param.put("class_id", class_id);
                        param.put("date", Timestamp.valueOf(date).toString());
                        result = (String) req.sendPostRequest("InsertIntoAbsence", param);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            Log.d(TAG, "succes or no: " + result);



        }
    }




}