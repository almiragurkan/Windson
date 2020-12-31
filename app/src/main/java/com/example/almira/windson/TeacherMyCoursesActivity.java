package com.example.almira.windson;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ExpandableListView;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class TeacherMyCoursesActivity extends AppCompatActivity {

    private TextView textView_fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_mycourses_activity);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        String name="";
        char[] ch = email.toCharArray();
        for (char c : ch){
            if(Character.valueOf(c).compareTo(Character.valueOf('@')) != 0){
                name = name+c;
            }
            else{
                break;
            }
        }
        String ID_name = new String(name);

        textView_fullname = (TextView) findViewById(R.id.textView71);
        textView_fullname.setText(ID_name);


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.commit();
                Intent intent = new Intent(TeacherMyCoursesActivity.this, StudentProfile.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}
}