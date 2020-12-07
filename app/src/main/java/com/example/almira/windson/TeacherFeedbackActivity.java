package com.example.almira.windson;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherFeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_feedback_activity);


        ExpandableListView listname = (ExpandableListView)findViewById(R.id.listname);
        listname.setAdapter(new tprocesstudentlist(this));

        listname.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(tprocesstudentlist.listname[groupPosition].equals("   Students Lists")) {
                    if (tprocesstudentlist.studentsLists[childPosition].equals("Student1")) {

                    } else if (tprocesstudentlist.studentsLists[childPosition].equals("Student2")) {

                    } else if (tprocesstudentlist.studentsLists[childPosition].equals("Student3")) {

                    } else if (tprocesstudentlist.studentsLists[childPosition].equals("Student4")) {


                    } else if (tprocesstudentlist.studentsLists[childPosition].equals("Student5")) {

                    } else {

                    }
                }

                return false;
            }
        });

    }
}
