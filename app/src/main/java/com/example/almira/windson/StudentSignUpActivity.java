package com.example.almira.windson;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class StudentSignUpActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText txt_name11,txt_surname11, txt_email11, txt_ID11, txt_password11;
    Button btnSignup;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_signup_activity);

        txt_name11 = findViewById(R.id.txt_name11);
        txt_surname11 = findViewById(R.id.txt_surname11);
        txt_email11 = findViewById(R.id.txt_email11);
        txt_ID11 = findViewById(R.id.txt_ID11);
        txt_password11 = findViewById(R.id.txt_password11);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btnSignup = findViewById(R.id.btn_signup11);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(StudentSignUpActivity.this, StudentMyCoursesActivity.class);
                //startActivity(intent);

                final String email = txt_email11.getText().toString().trim();
                String password = txt_password11.getText().toString().trim();
                final String name = txt_name11.getText().toString();
                final String surname = txt_surname11.getText().toString();
                final String id    = txt_ID11.getText().toString();

                if(TextUtils.isEmpty(email)){
                    txt_email11.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    txt_password11.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    txt_password11.setError("Password Must be >= 6 Characters");
                    return;
                }


                // register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            // send verification link

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(StudentSignUpActivity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });
                            Toast.makeText(StudentSignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",name);
                            user.put("fSurname",surname);
                            user.put("email",email);
                            user.put("ID",id);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),StudentMyCoursesActivity.class));

                        }else {
                            Toast.makeText(StudentSignUpActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
