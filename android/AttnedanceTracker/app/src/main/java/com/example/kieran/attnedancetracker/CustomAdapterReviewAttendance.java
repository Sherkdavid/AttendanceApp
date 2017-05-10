package com.example.kieran.attnedancetracker;

/**
 * Created by Kieran on 20/03/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kieran.attnedancetracker.tools.StudentAbsence;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Absence;
import model.Student;

public class CustomAdapterReviewAttendance extends ArrayAdapter<Student>{
    ArrayList<Student> modelItems = null;
    ArrayList<Absence> absenceList = null;
    int lectureCount;
    Context context;
    public CustomAdapterReviewAttendance(Context context, ArrayList<Student> resource, ArrayList<Absence> absenceList, int lectureCount) {
        super(context, R.layout.row, resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
        this.absenceList=absenceList;
        this.lectureCount=lectureCount;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        TextView studentid = (TextView) convertView.findViewById(R.id.textView2);
        TextView attendanceRate = (TextView) convertView.findViewById(R.id.textView3);

        name.setText(modelItems.get(position).getName().toString());
        studentid.setText(modelItems.get(position).getId().toString());
        //attendanceRate.setText("70%");

        double numberOfAbsences = 0;
        for (int i = 0; i<absenceList.size(); i++)
        {
            if (modelItems.get(position).getId().equals(absenceList.get(i).getStudent()))
            {
                numberOfAbsences = numberOfAbsences +1;
            }
        }

        //attendanceRate.setText(numberOfAbsences+"yo");

        String pattern = "###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        attendanceRate.setText(df.format(((lectureCount - numberOfAbsences) / lectureCount) * 100) + "%");


        return convertView;
    }
}
