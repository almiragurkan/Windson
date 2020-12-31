package com.example.almira.windson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StudentProfile extends AppCompatActivity {
    private TextView textView_email;
    private TextView textView_fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_profile);

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

        textView_email = (TextView) findViewById(R.id.profile_email);
        textView_fullname = (TextView) findViewById(R.id.profile_name);
        textView_email.setText(email);
        textView_fullname.setText(ID_name);


        Button btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });

    }

}
