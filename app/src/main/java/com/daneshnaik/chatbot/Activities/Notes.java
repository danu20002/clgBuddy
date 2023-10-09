package com.daneshnaik.chatbot.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.daneshnaik.chatbot.Activities.Departments.Information;
import com.daneshnaik.chatbot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Notes extends AppCompatActivity {
BottomNavigationView bottom_nav;
ScrollView scrollView;
CardView aero_view,bsc_view,computer_view,civil_view,electrical_view,electronics_view,information_view,mechanical_view;
TextView want_add_notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
     bottom_nav=findViewById(R.id.bottomnav);
scrollView=findViewById(R.id.scrollview_notes);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rcy_animation);


  Animation animation1=AnimationUtils.makeInAnimation(getApplicationContext(),true);
        scrollView.setAnimation(animation1);


//        aero_view=findViewById(R.id.aptitude);
//        aero_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             startActivity(new Intent(getApplicationContext(),Aeronautical.class));
//             overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });
//
//        bsc_view=findViewById(R.id.os);
//        bsc_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Bsc_honers.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });
//
//
//
//        computer_view=findViewById(R.id.dsa);
//        computer_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), computer.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });
//
//
//        civil_view=findViewById(R.id.computer_networks);
//        civil_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), civil.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });


//        electrical_view=findViewById(R.id.eee);
//        electrical_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Electrical.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });
//
//
//        electronics_view=findViewById(R.id.ece);
//        electronics_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Electronics.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });
        information_view=findViewById(R.id.ise);
        information_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Information.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

//        mechanical_view=findViewById(R.id.dsa);
//        mechanical_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Mechanical.class));
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        });


want_add_notes=findViewById(R.id.want_toadd);
want_add_notes.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(Notes.this,settings.class));
        finish();
    }
});









       bottom_nav.setSelectedItemId(R.id.Notes);
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
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
                        return true;
                    case R.id.updates:
                        startActivity(new Intent(getApplicationContext(),Updates.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                         finish();
                        return  true;
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
    public void onBackPressed(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(Notes.this).setIcon(R.drawable.baseline_logout_24).setTitle("Exit the app").setMessage("Are you sure! want to exit app?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      Notes.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Notes.this, "❤️❤️❤️❤️❤️", Toast.LENGTH_SHORT).show();
                    }
                }).setCancelable(true);
        alertDialog.show();


    }
}