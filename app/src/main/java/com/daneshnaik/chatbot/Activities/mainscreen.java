package com.daneshnaik.chatbot.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Adapter.UserAdapter;
import com.daneshnaik.chatbot.Tables.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class mainscreen extends AppCompatActivity {
RecyclerView recyclerView;
BottomNavigationView bottom_nav;
FirebaseAuth auth;
FirebaseDatabase database;
    FirebaseRemoteConfig remoteConfig;

    UserAdapter adapter;
    ArrayList<Users> users;
    ProgressBar progress_mainscreen;

    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mainscreen);
        recyclerView=findViewById(R.id.recyclerview_main);
        progress_mainscreen=findViewById(R.id.progress_mainscreen);


        searchView=findViewById(R.id.serach_bar_user);
             searchView.clearFocus();




        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        users=new ArrayList<>();
        adapter=new UserAdapter(this,users);


        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rcy_animation);
        recyclerView.startAnimation(animation);
        recyclerView.setAdapter(adapter);
//         recyclerView.setVerticalScrollBarEnabled(true);
        adapter.notifyDataSetChanged();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

      progress_mainscreen.setVisibility(View.VISIBLE);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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


















         database.getReference().child("users").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 users.clear();
                 for (DataSnapshot snapshot1:snapshot.getChildren()){
                     Users user=snapshot1.getValue(Users.class);

                     if (!user.getId().equals(FirebaseAuth.getInstance().getUid())) {
                         users.add(user);
                         progress_mainscreen.setVisibility(View.INVISIBLE);
                     }
                 }
                 adapter.notifyDataSetChanged();

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

        //updates checking
        int currentVersionCode;
        currentVersionCode=getCurrentVersioncode();

        remoteConfig=FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings=new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(5)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);

        remoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()){
                    String new_version_Code=remoteConfig.getString("new_version_code");

                    if(Integer.parseInt(new_version_Code) > getCurrentVersioncode()){
                        showUpdateDialog();
                    }

                }
            }
        });




       bottom_nav=findViewById(R.id.bottom_navigation);
       bottom_nav.setSelectedItemId(R.id.chats);
       bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id=item.getItemId();
               switch (id){
                   case R.id.chats:
                       return true;
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
                       startActivity(new Intent(getApplicationContext(), settings.class));
                       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                       finish();
                       return true;
               }
               return false;
           }
       });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_up,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.settings){
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mainscreen.this);
            alertDialog.setTitle("Are you sure want to Logout").setMessage("Your all chats will get cleared").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(mainscreen.this,Login_activity.class));
                    finish();

                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(mainscreen.this, "your are Re-Directed Back", Toast.LENGTH_SHORT).show();
                }
            }).show();



        }
        return super.onOptionsItemSelected(item);

    }



    private void showUpdateDialog() {
        final AlertDialog dialog=new AlertDialog.Builder(mainscreen.this).setTitle("Update App").setMessage("New App version Available size:1.7MB").setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.daneshnaik.chatbot")));
                }catch (Exception e){
                    Toast.makeText(mainscreen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"ok new features discarded",Toast.LENGTH_LONG).show();
            }
        }).show();
        dialog.setCancelable(true);
    }

    private int getCurrentVersioncode(){
        PackageInfo packageInfo=null;
        try{
            packageInfo= getPackageManager().getPackageInfo(getPackageName(),0);

        }catch (Exception e){
            Toast.makeText(this, "Not working", Toast.LENGTH_SHORT).show();
        }
        return packageInfo.versionCode;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(mainscreen.this).setIcon(R.drawable.baseline_logout_24).setTitle("Exit the app").setMessage("Are you sure! want to exit app?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainscreen.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mainscreen.this, "❤️❤️❤️❤️❤️", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(true);
        alertDialog.show();


    }
    private  void filterList(String text){
        ArrayList<Users> filterlist=new ArrayList<>();
        for(Users users1: users){
            if(users1.getName().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(users1);
            } else if (users1.getEmail().toLowerCase().contains(text.toLowerCase())) {
                filterlist.add(users1);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(getApplicationContext(),"NO USER FOUND",Toast.LENGTH_SHORT).show();
        }else{
               adapter.setfilterdlist(filterlist);
        }

    }
}