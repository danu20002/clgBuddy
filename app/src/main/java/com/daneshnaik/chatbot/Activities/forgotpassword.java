package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daneshnaik.chatbot.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

  TextInputEditText email_reset;
  AppCompatButton reset_btn;
  ProgressBar progress_reset;
  FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        email_reset=findViewById(R.id.email_forgot);
        reset_btn=findViewById(R.id.reset_btn);
        progress_reset=findViewById(R.id.progress_reset);
        auth= FirebaseAuth.getInstance();


        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_resetting=email_reset.getEditableText().toString().trim();
                 progress_reset.setVisibility(View.VISIBLE);
                if (!email_resetting.isEmpty()){
                    auth.sendPasswordResetEmail(email_resetting).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(forgotpassword.this, "See Your mailbox and reset Password", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(forgotpassword.this,Login_activity.class));
                            progress_reset.setVisibility(View.INVISIBLE);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(forgotpassword.this, "User Not Found"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            progress_reset.setVisibility(View.INVISIBLE);

                        }
                    });
                }else{
                    email_reset.setError("Email Can't be Empty");
                    progress_reset.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}