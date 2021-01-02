package com.example.almira.windson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.MediaController;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class TeacherMobileAppDevActivity extends AppCompatActivity {

    Button btn_upload1, btn_video;
    Button btn_upload2, btn_quiz;
    Button btn_upload3, btn_answer;
    Button btn_upload4, btn_module;
    EditText txtdata ;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference, storageReference2, storageReference3, storageReference4;
    DatabaseReference databaseReference, databaseReference2, databaseReference3, databaseReference4;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    VideoView videoView;
    private Uri videoUri;
    MediaController mediaController;
    private static final int PICK_VIDEO = 1;
    videomember member;
    UploadTask uploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_mobileappdev_activity);

        member = new videomember();
        storageReference = FirebaseStorage.getInstance().getReference("Videos");
        databaseReference = FirebaseDatabase.getInstance().getReference("Videos");
        btn_video = (Button)findViewById(R.id.btn_video);
        btn_upload1= (Button)findViewById(R.id.btn_upload1);
        txtdata = (EditText)findViewById(R.id.txtdata);
        imgview = (ImageView)findViewById(R.id.image_view);
        videoView = (VideoView)findViewById(R.id.videoView2);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();
        progressDialog = new ProgressDialog(TeacherMobileAppDevActivity.this);// context name as per your project name

        storageReference2 = FirebaseStorage.getInstance().getReference("Quizzes");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Quizzes");
        btn_quiz = (Button)findViewById(R.id.btn_quiz);
        btn_upload2= (Button)findViewById(R.id.btn_upload2);

        storageReference3 = FirebaseStorage.getInstance().getReference("Answers");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Answers");
        btn_answer = (Button)findViewById(R.id.btn_answer);
        btn_upload3= (Button)findViewById(R.id.btn_upload3);

        storageReference4 = FirebaseStorage.getInstance().getReference("Modules");
        databaseReference4 = FirebaseDatabase.getInstance().getReference("Modules");
        btn_module = (Button)findViewById(R.id.btn_module);
        btn_upload4= (Button)findViewById(R.id.btn_upload4);


        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_VIDEO);

            }
        });
        btn_upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadVideo();

            }
        });


        Button btn_feedback = findViewById(R.id.btn_feedback);
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherMobileAppDevActivity.this, TeacherFeedbackActivity.class);
                startActivity(intent);

            }
        });


        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });

        btn_upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImage(storageReference2,databaseReference2);

            }
        });


        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });

        btn_upload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImage(storageReference3,databaseReference3);

            }
        });


        btn_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);
            }
        });

        btn_upload4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UploadImage(storageReference4,databaseReference4);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imgview.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
        else if(requestCode == PICK_VIDEO && resultCode == RESULT_OK && data != null && data.getData() != null) {

            videoUri = data.getData();
            videoView.setVideoURI(videoUri);
        }

    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    private void UploadVideo(){
        final String videoName = txtdata.getText().toString();
        final String  search = txtdata.getText().toString().toLowerCase();
        if (videoUri != null || !TextUtils.isEmpty(videoName)){
            progressDialog.setTitle("Video is Uploading...");
            progressDialog.show();

            final  StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(videoUri));
            uploadTask = reference.putFile(videoUri);

            Task<Uri> urltask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {

                            if (task.isSuccessful()) {
                                Uri downloadUrl = task.getResult();
                                progressDialog.dismiss();
                                Toast.makeText(TeacherMobileAppDevActivity.this, "Data saved", Toast.LENGTH_SHORT).show();

                                member.setName(videoName);
                                member.setVideourl(downloadUrl.toString());
                                member.setSearch(search);

                                String i = databaseReference.push().getKey();
                                databaseReference.child(i).setValue(member);
                            }else {
                                Toast.makeText(TeacherMobileAppDevActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else {
            Toast.makeText(this, "All Fields are required", Toast.LENGTH_SHORT).show();
        }
    }



    public void UploadImage(StorageReference sR, final DatabaseReference dR) {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = sR.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String TempImageName = txtdata.getText().toString().trim();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            @SuppressWarnings("VisibleForTests")
                            uploadinfo imageUploadInfo = new uploadinfo(TempImageName, taskSnapshot.getUploadSessionUri().toString());
                            String ImageUploadId = dR.push().getKey();
                            dR.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(TeacherMobileAppDevActivity.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
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
                Intent intent = new Intent(TeacherMobileAppDevActivity.this, StudentProfile.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }}

}