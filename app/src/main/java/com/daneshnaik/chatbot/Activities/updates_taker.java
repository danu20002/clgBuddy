package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import com.daneshnaik.chatbot.Adapter.updatesAdapter;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Messges;
import com.daneshnaik.chatbot.Tables.updates_tables;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class updates_taker extends AppCompatActivity {
    TextInputLayout Title,description,links,personal_email,another_link;

    AppCompatButton send_update_btn;
int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates_taker);
        Title=findViewById(R.id.title_updates);
        description=findViewById(R.id.description_updates);
        links=findViewById(R.id.links_updates);
        another_link=findViewById(R.id.anotherlink_updates);
        personal_email=findViewById(R.id.email_updates);
        send_update_btn= findViewById(R.id.send_update_btn);

        final String senderId= FirebaseAuth.getInstance().getCurrentUser().getEmail();



send_update_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email_data=personal_email.getEditText().getText().toString().trim();
        if(!email_data.isEmpty() && email_data.equals(senderId)){
            String title_data=Title.getEditText().getText().toString().trim();
            if (!title_data.isEmpty()){
                String description_data=description.getEditText().getText().toString().trim();
                if(!description_data.isEmpty()){
                    String links_data=links.getEditText().getText().toString().trim();
                    String another_links=another_link.getEditText().getText().toString().trim();

                    final updates_tables updates_model=new updates_tables(senderId,title_data,description_data,links_data,another_links,email_data);


                    FirebaseDatabase.getInstance().getReference().child("Updates").push().setValue(updates_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(updates_taker.this, "Data stored successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(updates_taker.this,Updates.class));
                     //       notificationtaker();
                            String[] emails=new String[]{"2gi20is011@students.git.edu","iamdaneshnaik@gmail.com"};

                            Intent intent=new Intent(Intent.ACTION_SEND);
                            intent.putExtra(Intent.EXTRA_EMAIL,emails);
                            intent.putExtra(Intent.EXTRA_SUBJECT,title_data);
                            intent.putExtra(Intent.EXTRA_TEXT,description_data+"\n"+links_data+"\n"+another_links);
                            intent.setType("message/rfc822");
                            startActivity(Intent.createChooser(intent,"choose to send "));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(updates_taker.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    description.setError("Enter something");
                }

            }else{
                Title.setError("Enter Title please");
            }

        }else{
            personal_email.setError("Email is Invalid");
        }

    }
});


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);




    }
    public  void notificationtaker(){
        String username="collagebuddy111@gmail.com";
        String password="rvorsglrrmacwhck";
        Properties props=new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.SSL","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.SSL","465");
        props.put("mail.smtp.port","587");
        Session session=Session.getInstance(props,new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
        try {
            String email_tosend="naikdanesh2@gmail.com";
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email_tosend));
            message.setSubject("New Update from    "+FirebaseAuth.getInstance().getCurrentUser().getEmail());
            message.setText(Title.getEditText().toString());
            Transport.send(message);

        }catch (MessagingException e){
            throw new RuntimeException(e);
        }

    }

}