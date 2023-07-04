package com.daneshnaik.chatbot.Activities.Departments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.daneshnaik.chatbot.R;

public class Electrical extends AppCompatActivity {
    AppCompatButton fetch_btn;
    AutoCompleteTextView autoCompleteTextView_sem;
    AutoCompleteTextView autoCompleteTextView_units,autoCompleteTextView_couses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical);


        autoCompleteTextView_sem=findViewById(R.id.filled_sem);
        autoCompleteTextView_units=findViewById(R.id.filled_unit);
        autoCompleteTextView_couses=findViewById(R.id.filled_course);

        String[] semesters=new String[]{"Semester 1","Semester 2","Semester 3","Semester 4","Semester 5","Semester 6",};
        String[] courses1=new String[]{"Calculus and Linear Algebra", "Applied Physics", "Engineering Mechanics", "Basics of Mechanical Engg", "Engineering Graphics", "Applied Physics Lab", "Idea to Innovation Lab", "Communicative English" };
        String[] couses2=new String[]{"Differential Equations and Laplace Transforms", "Applied Chemistry", "Basics of Electrical and Electronics Engg", "Problem Solving using C",};
        String[] units=new String[]{"Unit 1","Unit 2","Unit 3","Unit 4","Unit 5"};
        String[] courses3=new String[]{};
        String[] courses4=new String[]{};
        String[] courses5=new String[]{};
        String[] courses6=new String[]{};
        String[] courses7=new String[]{};
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.single_spinner,semesters);
        ArrayAdapter<String> adapter_unit=new ArrayAdapter<>(this,R.layout.single_spinner,units);
        ArrayAdapter<String> adapter_course1=new ArrayAdapter<>(this,R.layout.single_spinner,courses1);
        ArrayAdapter<String> adapter_couse2=new ArrayAdapter<>(this,R.layout.single_spinner,couses2);
        ArrayAdapter<String> adapter_course3=new ArrayAdapter<>(this,R.layout.single_spinner,courses3);
        ArrayAdapter<String> adapter_course4=new ArrayAdapter<>(this,R.layout.single_spinner,courses4);
        ArrayAdapter<String> adapter_course5=new ArrayAdapter<>(this,R.layout.single_spinner,courses5);
        ArrayAdapter<String> adapter_course6=new ArrayAdapter<>(this,R.layout.single_spinner,courses6);
        ArrayAdapter<String> adapter_course7=new ArrayAdapter<>(this,R.layout.single_spinner,courses7);
        autoCompleteTextView_sem.setAdapter(adapter);
        autoCompleteTextView_sem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sems=autoCompleteTextView_sem.getText().toString();
                Toast.makeText(getApplicationContext(), autoCompleteTextView_sem.getText().toString(), Toast.LENGTH_SHORT).show();
                if(sems.matches("Semester 1")){
                    autoCompleteTextView_couses.setAdapter(adapter_course1);
                } else if (sems.matches("Semester 2")) {
                    autoCompleteTextView_couses.setAdapter(adapter_couse2);

                } else if (sems.matches("Semester 3")) {
                    autoCompleteTextView_couses.setAdapter(adapter_course3);
                } else if (sems.matches("Semester 4")) {
                    autoCompleteTextView_couses.setAdapter(adapter_course4);

                }else if (sems.matches("Semester 5")) {
                    autoCompleteTextView_couses.setAdapter(adapter_course5);

                }else if (sems.matches("Semester 6")) {
                    autoCompleteTextView_couses.setAdapter(adapter_course6);

                }else if (sems.matches("Semester 7")) {
                    autoCompleteTextView_couses.setAdapter(adapter_course7);

                }else{
                    autoCompleteTextView_couses.setError("select anyone");
                }
            }
        });



        autoCompleteTextView_units.setAdapter(adapter_unit);
        autoCompleteTextView_units.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), autoCompleteTextView_units.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        fetch_btn=findViewById(R.id.fetch_btn);
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchdata();
            }
        });


    }

    private void fetchdata() {
        String sem=autoCompleteTextView_sem.getText().toString();
        String units=autoCompleteTextView_units.getText().toString();
        if(!sem.isEmpty()){
            if(!units.isEmpty()){
                logic();

            }else{
                autoCompleteTextView_units.setError("please select one");
            }

        }else {
            autoCompleteTextView_sem.setError("please select one");
        }



    }

    private void logic() {
        String semi=autoCompleteTextView_sem.getText().toString();
        String unity=autoCompleteTextView_units.getText().toString();
        String coursee=autoCompleteTextView_couses.getText().toString();
        if(semi.matches("Semester 1") && coursee.matches("Calculus and Linear Algebra")){
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (semi.matches("Semester 1") && coursee.matches("Applied Physics")) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 1") && coursee.matches("Engineering Mechanics")) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 1") && coursee.matches("Basics of Mechanical Engg" )) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 1") && coursee.matches("Engineering Graphics")) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 1") && coursee.matches("Applied Physics Lab" )) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 1") && coursee.matches("Idea to Innovation Lab" )) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 1") && coursee.matches("Communicative English")) {

            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }   else if (semi.matches("Semester 2") && coursee.matches("Differential Equations and Laplace Transforms")) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 2") && coursee.matches("Applied Chemistry" )) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 2") && coursee.matches("Basics of Electrical and Electronics Engg" )) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 2") && coursee.matches("Problem Solving using C")) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "goodies", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updatin soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "okok", Toast.LENGTH_SHORT).show();
                    break;
            }

        }


    }
}