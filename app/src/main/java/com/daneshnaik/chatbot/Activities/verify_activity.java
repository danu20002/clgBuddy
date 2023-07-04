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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


public class verify_activity extends AppCompatActivity {

   TextInputEditText email_register,password_register;
   AppCompatButton register_btn,sign_in_btn;
   TextView already_user;
   FirebaseAuth auth;
   ProgressBar progress_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verify);

        email_register=findViewById(R.id.email_register);
        password_register=findViewById(R.id.password_register);
        register_btn=findViewById(R.id.register_btn);
        already_user=findViewById(R.id.already_account);
        progress_reg=findViewById(R.id.progressbar_register);
        auth=FirebaseAuth.getInstance();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_reg=email_register.getEditableText().toString().trim();
                String password_reg=password_register.getEditableText().toString().trim();


                if(!email_reg.isEmpty()  && Pattern.matches("2gi20is011@students\\.git\\.edu",email_reg)){
                    if(!password_reg.isEmpty() ){
                        progress_reg.setVisibility(View.VISIBLE);
                         auth.createUserWithEmailAndPassword(email_reg,password_reg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if(task.isComplete()){
                                     auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             Toast.makeText(verify_activity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                                             progress_reg.setVisibility(View.INVISIBLE);
                                             startActivity(new Intent(getApplicationContext(),Login_activity.class));
                                             Toast.makeText(verify_activity.this, "Verification Email Sent ", Toast.LENGTH_SHORT).show();
                                             finish();
                                         }
                                     }).addOnFailureListener(new OnFailureListener() {
                                         @Override
                                         public void onFailure(@NonNull Exception e) {
                                             Toast.makeText(verify_activity.this, "Verification Email not sent"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                         }
                                     });


                                 }
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 progress_reg.setVisibility(View.INVISIBLE);
                                 Toast.makeText(verify_activity.this, "Something went Wrong"+e.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         });




                    }else {
                        password_register.setError("please Enter password");
                        Toast.makeText(getApplicationContext(),"password must have 8 letters",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    email_register.setError("Email can't be Empty or change the Email");
                    Toast.makeText(verify_activity.this, "Email is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });


already_user.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),Login_activity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
        finish();
    }
});


 sign_in_btn=findViewById(R.id.sign_in_btn_below);
 sign_in_btn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         startActivity(new Intent(getApplicationContext(),Login_activity.class));
         overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out );
         finish();
     }
 });
    }

}