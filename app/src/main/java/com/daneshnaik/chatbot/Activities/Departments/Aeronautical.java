package com.daneshnaik.chatbot.Activities.Departments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.daneshnaik.chatbot.Adapter.materialsAdapter;
import com.daneshnaik.chatbot.Adapter.pdfAdapter;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.materials;
import com.daneshnaik.chatbot.Tables.pdfclass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Aeronautical extends AppCompatActivity {

    FloatingActionButton btn_apti;
    RecyclerView apti_recycler;
    ArrayList<pdfclass> pdfclassArrayList;
    int id =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeronautical);
        btn_apti=findViewById(R.id.add_btn_apti);
        apti_recycler=findViewById(R.id.recycler_apti);
       pdfclassArrayList=new ArrayList<>();
        pdfAdapter pdfadapter=new pdfAdapter(this,pdfclassArrayList);
         apti_recycler.setAdapter(pdfadapter);
        apti_recycler.smoothScrollToPosition(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        apti_recycler.setLayoutManager(layoutManager);
        btn_apti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Mechanical.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        StorageReference reference= FirebaseStorage.getInstance().getReference("fileuploads");
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        database.getReference().child("uploads").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pdfclassArrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    pdfclass pdfclsses=dataSnapshot.getValue(pdfclass.class
                    );
                    pdfclassArrayList.add(pdfclsses);
                    if(snapshot.exists()){
                        id=(int) snapshot.getChildrenCount();
                    }
                }
                pdfadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}