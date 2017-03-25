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
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.kieran.attnedancetracker.users.Student;

public class CustomAdapter extends ArrayAdapter<Student>{
        Student[] modelItems = null;
        Context context;
public CustomAdapter(Context context, Student[] resource) {
        super(context,R.layout.row,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
        }
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row2, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        TextView studentid = (TextView) convertView.findViewById(R.id.textView2);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        name.setText(modelItems[position].getName());
        studentid.setText(modelItems[position].getId());
       // if(modelItems[position].getValue() == 1)
        //cb.setChecked(true);
        //else
        //cb.setChecked(false);
        return convertView;
        }
        }
