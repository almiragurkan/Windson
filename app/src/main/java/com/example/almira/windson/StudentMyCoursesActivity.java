package com.example.almira.windson;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StudentMyCoursesActivity extends AppCompatActivity {
    SearchView mySearchView;
    ListView mList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_mycourses_activity);
        mySearchView = (SearchView)findViewById(R.id.searchbox);
        mList = (ListView)findViewById(R.id.mlist);
        list = new ArrayList<String>();
        list.add("Mobile Application Development");
        list.add("French");

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        mList.setAdapter(adapter);

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        ExpandableListView courses = (ExpandableListView)findViewById(R.id.course);
        courses.setAdapter(new process(this));

        courses.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(process.courseLists[childPosition].equals("Mobile Application and Development")) {
                    Intent i = new Intent(getApplicationContext(), StudentMobileAppDevActivity.class);
                    String n = ((TextView) v).getText().toString();
                    i.putExtra("courseName", process.courseLists[childPosition]);
                    startActivity(i);
                }
                return false;
            }
        });


    }

}