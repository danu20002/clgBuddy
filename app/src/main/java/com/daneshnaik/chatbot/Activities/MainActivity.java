package com.daneshnaik.chatbot.Activities;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    ImageView profile_photo;
    TextInputEditText email_data,name_data,phoneNumber_data;
    ProgressBar progress_data;
    AppCompatButton submit_btn;
    FirebaseDatabase database;
    TextView editimage_txt;
    FirebaseAuth auth;
    FirebaseStorage storage;
    Uri selectimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile_photo=findViewById(R.id.profile_image);
        email_data=findViewById(R.id.Email_data);
        name_data=findViewById(R.id.Name_data);
        editimage_txt=findViewById(R.id.editimage_text);
        phoneNumber_data=findViewById(R.id.PhoneNumber_data);
        progress_data=findViewById(R.id.progress_data);
        auth=FirebaseAuth.getInstance();
        submit_btn=findViewById(R.id.submit_btn);
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();


      if (auth.getCurrentUser() !=null && auth.getCurrentUser().isEmailVerified() && database.getReference().getKey()!=null){
          startActivity(new Intent(MainActivity.this,mainscreen.class));
          finish();
      }


Intent intent=getIntent();
String Email_got=intent.getStringExtra("email");
email_data.setText(Email_got);


editimage_txt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,45);
    }
});



submit_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String Name=name_data.getEditableText().toString().trim();
        String Email=email_data.getEditableText().toString().trim();
        String Phone_no=phoneNumber_data.getEditableText().toString().trim();
        if (!Name.isEmpty()){
            if (!Email.isEmpty()){
                if(!Phone_no.isEmpty()){

                    if (selectimage != null) {
                         progress_data.setVisibility(View.VISIBLE);
                        StorageReference reference=storage.getReference().child("profiles").child(auth.getUid());
                        reference.putFile(selectimage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if (task.isSuccessful()){
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String imageurl=uri.toString();
                                            String uid=auth.getUid();
                                            String phonnumber=phoneNumber_data.getEditableText().toString().trim();;
                                            String fullname=name_data.getEditableText().toString();
                                            String Email=auth.getCurrentUser().getEmail();
                                            Users users=new Users(uid,fullname,phonnumber,imageurl,Email);
                                            database.getReference().child("users").child(uid).setValue(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Intent intent=new Intent(MainActivity.this,mainscreen.class);
                                                    progress_data.setVisibility(View.INVISIBLE);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });
                    }else{
                        editimage_txt.setError("please select Image");

                    }


                }else {
                    phoneNumber_data.setError("Phone Number is Required");
                    return;
                }
            }else{
                email_data.setError("Email is required");
                return;
            }
        }else {
            name_data.setError("Name is Required");
            return;
        }



    }
});


profile_photo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,45);
    }
});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null)
        {
            if (data.getData()!=null){
                profile_photo.setImageURI(data.getData());
                selectimage=data.getData();
            }
        }
    }
}