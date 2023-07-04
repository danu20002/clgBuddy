package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daneshnaik.chatbot.Adapter.updatesAdapter;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Users;
import com.daneshnaik.chatbot.Tables.updates_tables;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

public class Updates extends AppCompatActivity {
    BottomNavigationView bottomnav;
    updatesAdapter adapter;
    RecyclerView recyclerview_updates;
    FloatingActionButton floating_add_btn;
    ProgressBar progress_updates;
    SearchView serachview_updates;
    SearchView searchView_updates;
    int id =0;
     ArrayList<updates_tables> UpdatesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates);
        FirebaseMessaging.getInstance().subscribeToTopic("notification");
        progress_updates = findViewById(R.id.progress_updates);
         searchView_updates=findViewById(R.id.serach_bar_updates);

        final String senderId = FirebaseAuth.getInstance().getUid();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
         UpdatesArrayList = new ArrayList<>();
        adapter = new updatesAdapter(this, UpdatesArrayList);


        progress_updates.setVisibility(View.VISIBLE);


        recyclerview_updates = findViewById(R.id.recycler_updates);

        recyclerview_updates.setAdapter(adapter);
        recyclerview_updates.smoothScrollToPosition(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerview_updates.setLayoutManager(layoutManager);

        searchView_updates.clearFocus();
        searchView_updates.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
           filterList(newText);
                return true;
            }
        });


        floating_add_btn = findViewById(R.id.floating_add_btn);
        floating_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser().getUid().matches("Az5rWDajdnhCsXduJta6689qBPh1")) {
                    startActivity(new Intent(Updates.this, updates_taker.class));
                } else {
                    Toast.makeText(Updates.this, "Your Are not allowed to add updates", Toast.LENGTH_SHORT).show();
                }

            }
        });


        database.getReference().child("Updates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UpdatesArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    updates_tables tables = dataSnapshot.getValue(updates_tables.class);
                    UpdatesArrayList.add(tables);
                    progress_updates.setVisibility(View.INVISIBLE);
                    if(snapshot.exists()){
                        id=(int) snapshot.getChildrenCount();
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.getReference().child("Updates").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                if(id==0){
//
//                }else{
//                    notificationUpdates();
//                }

                }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        bottomnav = findViewById(R.id.bottomnav_updates);
        bottomnav.setSelectedItemId(R.id.updates);
        bottomnav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.chats:
                        startActivity(new Intent(getApplicationContext(), mainscreen.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                        return true;
                    case R.id.Notes:
                        startActivity(new Intent(getApplicationContext(), Notes.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                        return true;
                    case R.id.updates:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), settings.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    private void notificationUpdates() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentTitle("Clg Buddies")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentText("New update in updates section");
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        managerCompat.notify(999, builder.build());
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Updates.this)
                .setIcon(R.drawable.baseline_logout_24)
                .setTitle("Exit the app").setMessage("Are you sure! want to exit app?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Updates.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Updates.this, "❤️❤️❤️❤️❤️", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(true);
        alertDialog.show();


    }
    private  void filterList(String text){
        ArrayList<updates_tables> filterlist=new ArrayList<>();
        for(updates_tables users1: UpdatesArrayList){
            if(users1.getTitle().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(users1);
            } else if (users1.getDescription().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(getApplicationContext(),"NO USER FOUND",Toast.LENGTH_SHORT).show();
        }else{
            adapter.setfilterdupdatelist(filterlist);
        }

    }


    }



