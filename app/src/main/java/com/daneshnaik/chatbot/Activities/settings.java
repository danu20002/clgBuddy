package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Notes_req;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//mail
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class settings extends AppCompatActivity {
    CardView account_details,logout_account,delete_account,privacy_policy,help_card,addnotes_card;
BottomNavigationView bottomnav;
ProgressBar settings_progressbar;
ImageView imageNinganna;
Notes_req notes_req;
    String message_used;
    String email_used;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        account_details=findViewById(R.id.account_details);

        settings_progressbar=findViewById(R.id.settings_progressbar);

        imageNinganna=findViewById(R.id.profile_image_contributor);
        Glide.with(this).load(Uri.parse("https://firebasestorage.googleapis.com/v0/b/chatbot-50f0c.appspot.com/o/profiles%2FY2fIwzTcJgRvyvCauSe9ItuWZI52?alt=media&token=ac45c693-67b4-4d7a-81a0-013a16312990")).into(imageNinganna);

        account_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),account_settings.class);
                intent.putExtra("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                startActivity(intent);

            }
        });

logout_account=findViewById(R.id.logout_account);
logout_account
        .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialog=new AlertDialog.Builder(settings.this);
                alertdialog.setIcon(R.drawable.baseline_logout_24).setTitle("Logout Account").setMessage("Are you sure want logout ?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),Login_activity.class));
                        finish();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(settings.this, "Welcome Back Buddy", Toast.LENGTH_SHORT).show();
                    }
                });
                alertdialog.show();

            }
        });


delete_account=findViewById(R.id.delete_account);
delete_account.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        settings_progressbar.setVisibility(View.VISIBLE);

        AlertDialog.Builder dialog=new AlertDialog.Builder(settings.this).setTitle("Delete Account").setMessage("Are you sure Want to delete Account permanently?")
                .setIcon(R.drawable.baseline_delete_forever_24)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseStorage.getInstance().getReference().child("profiles").child(FirebaseAuth.getInstance().getUid()).delete();
                                FirebaseDatabase.getInstance().getReference().child("users").child("chats").child(FirebaseAuth.getInstance().getUid()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        if(task.isSuccessful()){
                                            FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    startActivity(new Intent(getApplicationContext(),Login_activity.class));
                                                    finish();
                                                    settings_progressbar.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(settings.this, "BYe Bye", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            Toast.makeText(settings.this, "Deleted SuccessFully", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                settings_progressbar.setVisibility(View.INVISIBLE);

                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(settings.this, "ok your Data is stored Back", Toast.LENGTH_SHORT).show();
                        settings_progressbar.setVisibility(View.INVISIBLE);
                    }
                });
        dialog.show();

    }
});











help_card=findViewById(R.id.help_card);
help_card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),Help.class));
       
    }
});















privacy_policy=findViewById(R.id.privacy_policy);
privacy_policy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(), com.daneshnaik.chatbot.Activities.privacy_policy.class));
    }
});









        bottomnav=findViewById(R.id.bottomnav_settings);
        bottomnav.setSelectedItemId(R.id.settings);
        bottomnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id){
                    case R.id.chats:
                        startActivity(new Intent(getApplicationContext(),mainscreen.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                        return  true;

                    case R.id.Notes:
                        startActivity(new Intent(getApplicationContext(),Notes.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                        return true;
                    case R.id.updates:
                        startActivity(new Intent(getApplicationContext(),Updates.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                        return true;
                    case R.id.settings:
                        return true;
                }
                return false;
            }
        });
addnotes_card=findViewById(R.id.add_notes_card);
addnotes_card.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dialog dialog=new Dialog(settings.this);
        dialog.setContentView(R.layout.single_image_viewer);
        TextInputEditText email_req=dialog.findViewById(R.id.email_notes_req);
        TextInputEditText message_req=dialog.findViewById(R.id.email_message_req);
        AppCompatButton send_req=dialog.findViewById(R.id.send_Request);
       email_req.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        send_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings_progressbar.setVisibility(View.VISIBLE);
                email_used=email_req.getEditableText().toString();
                 message_used=message_req.getEditableText().toString();
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                notes_req=new Notes_req(id,email_used,message_used);
                if(!message_used.isEmpty()){

                    FirebaseDatabase.getInstance().getReference().child("Notes Requests")
                            .child(FirebaseAuth.getInstance().getUid())
                            .push()
                            .setValue(notes_req)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                           Toast toast=new Toast(settings.this);
                           toast.setGravity(Gravity.BOTTOM|Gravity.FILL_HORIZONTAL,0,0);
                           toast.setDuration(Toast.LENGTH_LONG);
                           toast.setText("Thank You for Requesting Your Request Under Review");
                           toast.show();

                           sending_notification();
                           settings_progressbar.setVisibility(View.INVISIBLE);
                            message_req.setText("");
                            dialog.dismiss();
                            addnotes_card.setEnabled(false);
                           addnotes_card.setBackgroundColor(R.color.black);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    addnotes_card.setEnabled(true);
                                    addnotes_card.setBackgroundColor(R.color.white);
                                }
                            },50000);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                }else{
                    message_req.setError("Enter something");
                }
            }

        });
        dialog.show();
    }

});
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(settings.this).setIcon(R.drawable.baseline_logout_24).setTitle("Exit the app").setMessage(" Are you sure! want to exit app?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        settings.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(settings.this, "❤️❤️❤️❤️❤️", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(true);
        alertDialog.show();


    }

    public  void sending_notification(){
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
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email_used));
            message.setSubject("Notes Adding Request From  "+email_used);
            message.setText(message_used+"\n"+"ok\nThank You for your Kindness❤️"+"\n"+"https://drive.google.com/drive/folders/12yXJJN1wXHZTBveKW_YJntFtHVeIAaHF?usp=share_link"+"\n"+"Add your Note to this drive link we will verify them");

            Transport.send(message);

        }catch (MessagingException e){
            throw new RuntimeException(e);
        }

    }
}