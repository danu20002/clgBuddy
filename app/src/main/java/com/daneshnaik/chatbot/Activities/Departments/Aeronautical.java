package com.daneshnaik.chatbot.Activities.Departments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.daneshnaik.chatbot.Adapter.materialsAdapter;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.materials;

import java.util.ArrayList;

public class Aeronautical extends AppCompatActivity {
AppCompatButton fetch_btn;
    AutoCompleteTextView autoCompleteTextView_sem;
    ArrayList<materials> arrayList;
    materialsAdapter materialsAdapter_info;
    RecyclerView recyclerview_apti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aeronautical);
        autoCompleteTextView_sem=findViewById(R.id.filled_sem);
        arrayList=new ArrayList<>();
        materialsAdapter_info=new materialsAdapter(this,arrayList);
         recyclerview_apti=findViewById(R.id.recycler_view_apti);

      String[] semesters=new String[]{"Number System","Probability","Semester 3","Semester 4","Semester 5","Semester 6",};


        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.single_spinner,semesters);
        autoCompleteTextView_sem.setAdapter(adapter);






        fetch_btn=findViewById(R.id.fetch_btn);
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata();
                recyclerview_apti.setAdapter(materialsAdapter_info);
                recyclerview_apti.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        });


    }

    private void fetchdata() {
        String sem=autoCompleteTextView_sem.getText().toString();

        if(!sem.isEmpty()){

               logic();



        }else {
            autoCompleteTextView_sem.setError("please select one");
        }



    }

    private void logic() {
        String semi=autoCompleteTextView_sem.getText().toString();

        switch (semi){
            case "Number System":
                arrayList.add(new materials("number system","https://www.tutorialspoint.com/operating_system/os_types.htm"));
                break;
            case "Probability":
                arrayList.add(new materials("number system","https://www.tutorialspoint.com/operating_system/os_types.htm"));
                break;
        }


    }
}