package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Messges;
import com.daneshnaik.chatbot.Tables.updates_tables;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class Help extends AppCompatActivity {
TextInputEditText title,describe;
  AppCompatButton submit_btn;
  updates_tables  updatesTables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        title=findViewById(R.id.title_help);
        describe=findViewById(R.id.describe_help);
        submit_btn=findViewById(R.id.submit_help_btn);


        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title_data=title.getEditableText().toString();
                String describe_data=describe.getEditableText().toString();
                String send_id=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                if(!title_data.isEmpty()){
                    if(!describe_data.isEmpty()){


                    updatesTables=new updates_tables(send_id,title_data,describe_data);
                    FirebaseDatabase.getInstance().getReference().child("help").child(FirebaseAuth.getInstance().getUid()).push().setValue(updatesTables).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Help.this, "Sent to Developer for Review ", Toast.LENGTH_SHORT).show();
                            describe.setText("");
                            title.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Help.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                        describe.setError("Describe Your Issue");
                    }
                }else {
                    title.setError("Enter something Here");
                }
                }
            });





    }
}