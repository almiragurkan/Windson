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

    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_mobileappdev_activity);

        videoView = (VideoView)findViewById(R.id.videoView);
        mediaController = new MediaController(this);


        ExpandableListView topic1 = (ExpandableListView)findViewById(R.id.topic1);
        topic1.setAdapter(new ProcessMobileApp(this));

        topic1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(ProcessMobileApp.mytopic[groupPosition].equals("   Introduction to Mobile Application Development")) {
                    if (ProcessMobileApp.topicLists[childPosition].equals("Lesson1")) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +
                                R.raw.tanitim);
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        videoView.start();
                    } else if (ProcessMobileApp.topicLists[childPosition].equals("Lesson2")) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +
                                R.raw.tanitim);
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        videoView.start();
                    } else if (ProcessMobileApp.topicLists[childPosition].equals("Quiz")) {

                    } else {

                    }
                }
                else if(ProcessMobileApp.mytopic[groupPosition].equals("   User Interface Layout Management")){
                    if (ProcessMobileApp.topicLists[childPosition].equals("Lesson1")) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +
                                R.raw.tanitim);
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        videoView.start();
                    } else if (ProcessMobileApp.topicLists[childPosition].equals("Lesson2")) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +
                                R.raw.tanitim);
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        videoView.start();
                    } else if (ProcessMobileApp.topicLists[childPosition].equals("Quiz")) {

                    } else {

                    }
                }else if(ProcessMobileApp.mytopic[groupPosition].equals("    Event-driven Programming")){
                    if (ProcessMobileApp.topicLists[childPosition].equals("Lesson1")) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +
                                R.raw.tanitim);
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        videoView.start();
                    } else if (ProcessMobileApp.topicLists[childPosition].equals("Lesson2")) {
                        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +
                                R.raw.tanitim);
                        videoView.setVideoURI(uri);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        videoView.start();
                    } else if (ProcessMobileApp.topicLists[childPosition].equals("Quiz")) {

                    } else {

                    }
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
                Intent intent = new Intent(StudentMobileAppDevActivity.this, StudentProfile.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}
}
