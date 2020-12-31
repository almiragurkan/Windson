package com.example.almira.windson;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TeacherSignUpActivity extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText txt_name22,txt_surname22, txt_email22, txt_ID22, txt_password22;
    Button btnSignup2;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_signup_activity);

        txt_name22 = findViewById(R.id.txt_name22);
        txt_surname22 = findViewById(R.id.txt_surname22);
        txt_email22 = findViewById(R.id.txt_email22);
        txt_ID22 = findViewById(R.id.txt_ID22);
        txt_password22 = findViewById(R.id.txt_password22);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();



        btnSignup2 = findViewById(R.id.btn_signup22);

        btnSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(TeacherSignUpActivity.this, TeacherMyCoursesActivity.class);
                //startActivity(intent);

                final String email = txt_email22.getText().toString().trim();
                String password = txt_password22.getText().toString().trim();
                final String name = txt_name22.getText().toString();
                final String surname = txt_surname22.getText().toString();
                final String id    = txt_ID22.getText().toString();

                if(TextUtils.isEmpty(email)){
                    txt_email22.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    txt_password22.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    txt_password22.setError("Password Must be >= 6 Characters");
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
                                    Toast.makeText(TeacherSignUpActivity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });
                            Toast.makeText(TeacherSignUpActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
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
                            startActivity(new Intent(getApplicationContext(),TeacherMyCoursesActivity.class));

                        }else {
                            Toast.makeText(TeacherSignUpActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}