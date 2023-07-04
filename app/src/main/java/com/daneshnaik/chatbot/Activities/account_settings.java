package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Pattern;

public class account_settings extends AppCompatActivity {
TextInputEditText profile_email,profile_phoneNumber,profile_name;
ImageView profile_image,back_settings;
AppCompatButton update_btn;

ProgressBar profile_updates;

FirebaseAuth auth;
FirebaseStorage storage;
Uri selectimage;
TextView update_password,arrow_back;
    Notification notification;
private  static  final String NOTIFICATION_CHANNELId="my channel";
    private  static  final int NOTIFICATION_Id=100;
    private static  final int REQ_CODE=201;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        profile_email=findViewById(R.id.profile_email);
        profile_name=findViewById(R.id.profile_name);
        profile_phoneNumber=findViewById(R.id.profile_phone_number);
        profile_image=findViewById(R.id.profile_image);
        update_btn=findViewById(R.id.profile_update_btn);
        update_password=findViewById(R.id.update_password);
         profile_updates=findViewById(R.id.profile_updates);
         arrow_back=findViewById(R.id.profile_text);
         auth=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        Intent intent=getIntent();
        String email_setter=intent.getStringExtra("email");
        profile_email.setText(email_setter);

        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String  name=snapshot.child("name").getValue().toString();
                profile_name.setText(name);
               String phone_number=snapshot.child("phoneNumber").getValue().toString();
                profile_phoneNumber.setText(phone_number);
                String profile_photo=snapshot.child("profileImage").getValue().toString();
                Glide.with(getApplicationContext()).load(profile_photo).into(profile_image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



update_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String Name_data=profile_name.getEditableText().toString();
        String Phone_Number_data=profile_phoneNumber.getEditableText().toString();
        if (!Name_data.isEmpty()){
            FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("name").setValue(Name_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(account_settings.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(account_settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }else{
            profile_name.setError("Enter your Name");
        }

                if(!Phone_Number_data.isEmpty()){

                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("phoneNumber").setValue(Phone_Number_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(account_settings.this, "updated Phone Number", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(account_settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }











if(selectimage!=null) {
    StorageReference reference = storage.getReference().child("profiles").child(auth.getUid());
    reference.putFile(selectimage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    String image_changed = uri.toString();
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).child("profileImage").setValue(image_changed).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(account_settings.this, "updated Profile picture", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(account_settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    });
}
notificationprofile();
        }

});
back_settings=findViewById(R.id.back_settings_img);
back_settings.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),settings.class));
        finish();
    }
});

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,45);
            }
        });

arrow_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(account_settings.this);
                dialog.setTitle("Update Password").setIcon(R.drawable.baseline_lock_24).setMessage("Are you sure want to update password").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email_setter).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(account_settings.this, "Password Update email sent to your email", Toast.LENGTH_SHORT).show();
                                update_password.setText("Reset Email is Sent to Your Email");

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(account_settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"OK Fine",Toast.LENGTH_LONG).show();
                    }
                });
             dialog.show();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null)
        {
            if (data.getData()!=null){
                profile_image.setImageURI(data.getData());
                selectimage=data.getData();
            }
        }
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(getApplicationContext(),settings.class));
       finish();
    }
    private void notificationprofile() {
        //large image icon
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.clgbuddy_logo, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap ImageIcon = bitmapDrawable.getBitmap();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
//            NotificationManager manager = getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
//                .setContentTitle("Clg Buddies")
//                .setSmallIcon(R.drawable.clgbuddy_logo)
//                .setLargeIcon(ImageIcon)
//                .setAutoCancel(true)
//                .setContentText("You Data is Updated Successfully");
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        managerCompat.notify(999, builder.build());
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent inotify=new Intent(getApplicationContext(),account_settings.class);
        inotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pi=PendingIntent.getActivity(this,REQ_CODE,inotify,PendingIntent.FLAG_UPDATE_CURRENT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             notification=new Notification.Builder(account_settings.this)
                    .setSmallIcon(R.drawable.icon_notification)
                    .setContentText("Hello! Buddy Profile updated Successfully")
                     .setLargeIcon(ImageIcon)
                    .setSubText("Profile Management")
                    .setChannelId(NOTIFICATION_CHANNELId)
                    .build();
            manager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANNELId,"new channel",NotificationManager.IMPORTANCE_HIGH));
        }else{
             notification=new Notification.Builder(account_settings.this)
                    .setSmallIcon(R.drawable.icon_notification)
                    .setContentText("Hello! ")
                     .setLargeIcon(ImageIcon)
                    .setSubText("Profile Updated Successfully")
                    .build();
        }
        manager.notify(NOTIFICATION_Id,notification);
//    }
    }
}