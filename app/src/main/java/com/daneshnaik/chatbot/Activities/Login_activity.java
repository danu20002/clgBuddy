package com.daneshnaik.chatbot.Activities;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daneshnaik.chatbot.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_activity extends AppCompatActivity {

TextInputEditText email_edit,password_edit;
AppCompatButton continue_btn,sign_up_below;
TextView new_account,forgot_password;
ProgressBar progressBar_log;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        email_edit=findViewById(R.id.email_editteext);
        password_edit=findViewById(R.id.password_editteext);
        continue_btn=findViewById(R.id.continue_btn);
        new_account=findViewById(R.id.new_account);
        forgot_password=findViewById(R.id.Forgot_password);
        progressBar_log=findViewById(R.id.progressbar_login);
        auth=FirebaseAuth.getInstance();



        if (auth.getCurrentUser()!=null && auth.getCurrentUser().isEmailVerified()){
            startActivity(new Intent(Login_activity.this,mainscreen.class));
              finish();
        }





        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar_log.setVisibility(View.VISIBLE);
            String email_log=email_edit.getEditableText().toString().trim();
            String  password_log=password_edit.getEditableText().toString().trim();

            if (!email_log.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email_log).matches()){
                if (!password_log.isEmpty()){

                        auth.signInWithEmailAndPassword(email_log,password_log).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (auth.getCurrentUser().isEmailVerified()){

                                    Toast.makeText(Login_activity.this, " Login SuccessFull", Toast.LENGTH_SHORT).show();
                                    progressBar_log.setVisibility(View.INVISIBLE);
                                   Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                   intent.putExtra("email",email_log);
                                   startActivity(intent);
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
                                    finish();
                                }else{
                                    Toast.makeText(Login_activity.this, "Email Not verified yet", Toast.LENGTH_SHORT).show();
                                    progressBar_log.setVisibility(View.INVISIBLE);
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login_activity.this, "Email And Password Not Matching", Toast.LENGTH_SHORT).show();
                                progressBar_log.setVisibility(View.INVISIBLE);
                            }
                        });







                }else {
                    password_edit.setError("Enter password");
                    progressBar_log.setVisibility(View.INVISIBLE);


                }
            }else {
                email_edit.setError("Can't Be Empty");
                progressBar_log.setVisibility(View.INVISIBLE);
            }

            }
        });
    new_account .setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),verify_activity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
            finish();
        }
    });


    forgot_password.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               startActivity(new Intent(Login_activity.this,forgotpassword.class));
             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
             Toast.makeText(Login_activity.this, "What is this Man???", Toast.LENGTH_SHORT).show();
        }
    });
    sign_up_below=findViewById(R.id.signup_btn_below);
    sign_up_below.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),verify_activity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
            finish();
        }
    });

    }
}