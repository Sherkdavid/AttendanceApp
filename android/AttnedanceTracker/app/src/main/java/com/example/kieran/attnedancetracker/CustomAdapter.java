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


import java.util.ArrayList;

import model.Student;


public class CustomAdapter extends ArrayAdapter<Student>{
        ArrayList<Student> modelItems = null;
        Context context;
        Boolean[] checked;

        public CustomAdapter(Context context, ArrayList<Student> resource, Boolean[] checked) {
                super(context, R.layout.row2,resource);
                // TODO Auto-generated constructor stub
                this.context = context;
                this.modelItems = resource;
                this.checked=checked;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.row2, parent, false);
                TextView name = (TextView) convertView.findViewById(R.id.textView1);
                TextView studentid = (TextView) convertView.findViewById(R.id.textView2);
                final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
                name.setText(modelItems.get(position).getName());
                studentid.setText(modelItems.get(position).getId());



                // Register listener
                cb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                checked[position] = cb.isChecked();
                        }
                });


                return convertView;
        }

        public void selectAll() {
                selectAll(true);
                notifyDataSetChanged();
        }

        public void selectNone() {
                selectAll(false);
                notifyDataSetChanged();
        }

        void selectAll(boolean selected) {
                for (int i = 0; i < checked.length; i++) {
                        checked[i] = selected;
                }
        }

        public boolean isChecked(int index) {
                return checked[index];
        }
}
