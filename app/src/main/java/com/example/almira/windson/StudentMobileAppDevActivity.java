package com.example.almira.windson;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class StudentMobileAppDevActivity extends AppCompatActivity {

    Button btn_lessons, btn_quizzes, btn_quiz_ans, btn_moduleexm, btn_moduleexm_ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_mobileappdev_activity);

        btn_lessons = findViewById(R.id.btn_lessons);
        btn_quizzes = findViewById(R.id.btn_quizzes);
        btn_quiz_ans = findViewById(R.id.btn_quiz_ans);
        btn_moduleexm = findViewById(R.id.btn_moduleexm);
        btn_moduleexm_ans = findViewById(R.id.btn_moduleexm_ans);


        btn_lessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //videolu versiyonu olcak unutma!!!!!!!!!!!!!!!!!!!!!!!!!!!
                startActivity( new Intent(StudentMobileAppDevActivity.this , VideoActivityLessons.class));
            }
        });

        btn_quizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StudentMobileAppDevActivity.this , ImagesActivityQuizzes.class));
                
            }
        });

        btn_quiz_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StudentMobileAppDevActivity.this , ImagesActivityQuizAnswers.class));

            }
        });


        btn_moduleexm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StudentMobileAppDevActivity.this , ImagesActivityModules.class));

            }
        });

        btn_moduleexm_ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StudentMobileAppDevActivity.this , ImagesActivityModuleExamAnswers.class));

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
                Intent intent = new Intent(StudentMobileAppDevActivity.this, StudentProfile.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}
}
