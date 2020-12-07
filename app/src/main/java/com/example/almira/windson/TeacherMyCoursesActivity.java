package com.example.almira.windson;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ExpandableListView;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TeacherMyCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_mycourses_activity);

        ExpandableListView courses2 = (ExpandableListView)findViewById(R.id.course2);
        courses2.setAdapter(new process(this));

        courses2.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(process.courseLists[childPosition].equals("Mobile Application and Development")) {
                    Intent i = new Intent(getApplicationContext(), TeacherMobileAppDevActivity.class);
                    String n = ((TextView) v).getText().toString();
                    i.putExtra("courseName", tprocess.courseLists2[childPosition]);
                    startActivity(i);
                }
                return false;
            }
        });


    }

}