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

import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.daneshnaik.chatbot.Adapter.materialsAdapter;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.materials;

import java.util.ArrayList;

public class Information extends AppCompatActivity {
    AppCompatButton fetch_btn;
    AutoCompleteTextView autoCompleteTextView_sem;
    AutoCompleteTextView autoCompleteTextView_units,autoCompleteTextView_couses;
    RecyclerView recyclerView;
    materialsAdapter materialsAdapter_info;
    ArrayList<materials> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        autoCompleteTextView_sem=findViewById(R.id.filled_sem);
        autoCompleteTextView_units=findViewById(R.id.filled_unit);
        autoCompleteTextView_couses=findViewById(R.id.filled_course);
        arrayList=new ArrayList<>();
        materialsAdapter_info=new materialsAdapter(this,arrayList);



        recyclerView=findViewById(R.id.recycler_view_info);



        String[] semesters=new String[]{"Semester 1","Semester 2","Semester 3","Semester 4","Semester 5","Semester 6"};
        String[] courses1=new String[]{"Calculus and Linear Algebra", "Applied Physics", "Engineering Mechanics", "Basics of Mechanical Engg", "Engineering Graphics", "Applied Physics Lab", "Idea to Innovation Lab", "Communicative English" };
        String[] couses2=new String[]{"Differential Equations and Laplace Transforms", "Applied Chemistry", "Basics of Electrical and Electronics Engg", "Problem Solving using C",};



        String[] units=new String[]{"Unit 1","Unit 2","Unit 3","Unit 4","Unit 5"};
        String[] courses3=new String[]{"wait Updating all notes before IA1"};
        String[] courses4=new String[]{"DAA","DBMS","Maths","Operating System","Python","Software Engineering"};
        String[] courses5=new String[]{"Computer Networks","FLAT","IOT","OOMD"};
        String[] courses6=new String[]{"artificial Intelligence","Data Science ","DCS","RPA"};
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
                recyclerView.setAdapter(materialsAdapter_info);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
                    arrayList.clear();
                      arrayList.add(new materials("artist"," https://drive.google.com/file/d/1Vn9DIFOQ9kZ0YzrpKAaQhdOE3Lr84NYw/view?usp=share_link"));
                    break;
                case "Unit 2":
                    Toast.makeText(this, "goody", Toast.LENGTH_SHORT).show();
                    arrayList.clear();
                    arrayList.add(new materials("badu"," https://drive.google.com/file/d/1Vn9DIFOQ9kZ0YzrpKAaQhdOE3Lr84NYw/view?usp=share_link"));
                    arrayList.add(new materials("compu1p","https://drive.google.com/file/d/17JtBQJ2wSBN5g2YkoQcfUccZNL8qKUHI/view?usp=share_link"));

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
                    Toast.makeText(this, "updating soon ", Toast.LENGTH_SHORT).show();
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

        }else if (semi.matches("Semester 3") && coursee.matches("wait Updating all notes before IA1")) {
            switch (unity){
                case "Unit 1":
                    Toast.makeText(this, " Sorry updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    Toast.makeText(this, "updating soon ", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    Toast.makeText(this, "Updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    Toast.makeText(this, "updating soon", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    Toast.makeText(this, "sorry updating soon", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 4") && coursee.matches("DAA")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("Unit 1","https://drive.google.com/file/d/1ykexNS8F44WnxV-Gi5ORO-6bwxCbrZRe/view?usp=share_link"));
                    arrayList.add(new materials("Uni 1 (printed)","https://drive.google.com/file/d/1L8st3_yjBrFXP8Oz8ypZJJR9AIgxXXdp/view?usp=share_link"));
                    break;
                case "Unit 2":
                   arrayList.clear();
                   arrayList.add(new materials("clustering ","https://drive.google.com/file/d/15c7_H0mvIT2lOOx5WLT-7VHi4aB_mDYu/view?usp=share_link"));
                   arrayList.add(new materials("Horner's Rule","https://drive.google.com/file/d/1nmCgqfDRohMsYfD7YMLETCDg-BEW4zwb/view?usp=share_link"));
                   arrayList.add(new materials("Unit 2_part 1","https://drive.google.com/file/d/1MVvA71tx9qfDCdDfTGTJKo9Hz8bOqnKx/view?usp=share_link"));
                    arrayList.add(new materials("Unit 2_part 2","https://drive.google.com/file/d/13WY2ZsqBT5DJOgBAS97K5n8TFtVgwZ2r/view?usp=share_link"));
                    arrayList.add(new materials("Unit 2_part 3","https://drive.google.com/file/d/1n0Sc5JI0saFUog_ULM-RBSNylTPzpNGI/view?usp=share_link"));
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("Greedy Statergy ","https://drive.google.com/file/d/1AwYXm-UB1biYT0DWewONTxG9qPtoseib/view?usp=share_link"));
                    arrayList.add(new materials("Kruskal's algorithm","https://drive.google.com/file/d/1gzJkVymI9KLpnrqw441TZykmixyxVnbq/view?usp=share_link"));
                    arrayList.add(new materials("Prim's alogorithm","https://drive.google.com/file/d/1WlJ__TgS-DU0wkywpHSccOAo7ThpFIfL/view?usp=share_link"));
                    arrayList.add(new materials("Unit 3_part 1","https://drive.google.com/file/d/1asUt2UxeA0sUPGW5t6dBiaGuYNKJcxVk/view?usp=share_link"));
                    arrayList.add(new materials("Unit 3_part 2","https://drive.google.com/file/d/1nauIIWJeLZ2QAfEnj1Vpji-CXHUVmItM/view?usp=share_link"));

                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("Unit 4 ","https://drive.google.com/file/d/1R_oGOQRJhEIFVOmCZ9UTHHWTE_u-lZHe/view?usp=share_link"));
                    arrayList.add(new materials("Full Textbook","https://drive.google.com/file/d/13cx1Krg4MxyHmGol8OkNw2rgQuZVyOsV/view?usp=share_link"));
                    break;
                case "Unit 5":
                    arrayList.clear();
                    arrayList.add(new materials("Whole  Textbook","https://drive.google.com/file/d/13cx1Krg4MxyHmGol8OkNw2rgQuZVyOsV/view?usp=share_link"));
                    break;
            }

        }else if (semi.matches("Semester 4") && coursee.matches("DBMS")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("Attributes_Relationship ","https://drive.google.com/file/d/1Ts4O0kq7pu91Wy3bZ2MoACQtc991rdlH/view?usp=share_link"));
                    arrayList.add(new materials("DBMS-01","https://drive.google.com/file/d/1XM_mYj9t_5ATIs8r2ixN9SAK3vlS4qUb/view?usp=share_link"));
                    arrayList.add(new materials("ER to Relationship","https://drive.google.com/file/d/1j17_4ef4E66x_uAv7E_vfrBSCdCORLvp/view?usp=share_link"));
                    arrayList.add(new materials("ER_Diagram","https://drive.google.com/file/d/194-koChIKAm0SYfL7yOpI8cV3GMVrGH0/view?usp=share_link"));
                    arrayList.add(new materials("Unit-1","https://drive.google.com/file/d/1YfeX0FWbSfS_xszWI8O8kC50zfvQhdJD/view?usp=share_link"));
                    break;
                case "Unit 2":
                  arrayList.clear();
                  arrayList.add(new materials("Unit 2(main)","https://drive.google.com/file/d/1SgZegXKFRpJs37SygvMd2yuYxJlYTN_X/view?usp=share_link"));
                  arrayList.add(new materials("Unit 2_02","https://drive.google.com/file/d/1bxhZlAHfVk5BVTUewUOMtxJOR1rB-2xW/view?usp=share_link"));
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("ENCH01 ","https://drive.google.com/file/d/1BXyrnIPcEELQKVYK5QUY93f4nZdkuMoO/view?usp=share_link"));
                    arrayList.add(new materials("ENCH17","https://drive.google.com/file/d/12t7blUteL0mvzKaMlUyhMcfyw5d1SaXK/view?usp=share_link"));
                    arrayList.add(new materials("Unit 3 Merge","https://drive.google.com/file/d/1d4358v3gbcneRmSELX2xICSLBHartyHk/view?usp=share_link"));
                    arrayList.add(new materials("Unit 3","https://drive.google.com/file/d/106QUqdN9RTVeqvwmnt2LBW0C4Tw-YTOM/view?usp=share_link"));
                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("Commands ","https://drive.google.com/file/d/1ysAqHGqOFsjIKj5m8cxJ0zFwIJBIYdhd/view?usp=share_link"));
                    arrayList.add(new materials("DBMS-Architecture","https://drive.google.com/file/d/1EhZT8rhXBV_vK1o455jZB9gUdknLKv4d/view?usp=share_link"));
                    arrayList.add(new materials("Unit 4","https://drive.google.com/file/d/18BfFMd7MwdW_z7lnry5opOz7t5GwyrYz/view?usp=share_link"));
                    arrayList.add(new materials("Dbms_unit 1(written)","https://drive.google.com/file/d/16VEK4_cuPXODIwyay9mY4LVeBipfE7_8/view?usp=share_link"));
                    arrayList.add(new materials("sql","https://drive.google.com/file/d/1p7X5kK8bzoPYfJsIf6q1ryR3MY-Zuhmj/view?usp=share_link"));
                    break;
                case "Unit 5":
                    arrayList.clear();
                    arrayList.add(new materials("Plsql 2 ","https://drive.google.com/file/d/14LlHzgPJtwRAg3bpbDwC6BZlPUTTocw-/view?usp=share_link"));
                    arrayList.add(new materials("plsql notes","https://drive.google.com/file/d/11EfxjaZalya-75gOvmUSTjBjQPmx1963/view?usp=share_link"));
                    arrayList.add(new materials("Text_book","https://drive.google.com/file/d/1bHMBieKSX-7q7dC9pso1QyNECjQQRZJS/view?usp=share_link"));
                    break;
            }

        }else if (semi.matches("Semester 4") && coursee.matches("Maths")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("Unit 1","https://drive.google.com/file/d/1GyjHeBaT3_xPVBEjZbelxmkls7bDxAsU/view?usp=share_link"));
                    arrayList.add(new materials("Uni 1_notes","https://drive.google.com/file/d/15LocIESDjAsmMXovgR2aHBFXqrA_icY9/view?usp=share_link"));
                    break;
                case "Unit 2":
                   arrayList.clear();
                   arrayList.add(new materials("Unit 2 Relationship","https://drive.google.com/file/d/1L74nfAJNoeU7uSpLMmlZmUgFSRVsgCsR/view?usp=share_link"));
                    arrayList.add(new materials("Unit 2 (read)","https://drive.google.com/file/d/1QtJsivBdbxghbIg1JlvmZgv9OyWrzE77/view?usp=share_link"));
                    arrayList.add(new materials("Unit 2(extra)","https://drive.google.com/file/d/1gNgRKaMgnQS_typBXpRlCtkROlRuQbuT/view?usp=share_link"));
                    arrayList.add(new materials("Unit 2","https://drive.google.com/file/d/1CTy4e4u3ZTcp675e9fXuGaDh0fzYvVJh/view?usp=share_link"));

                    break;
                case "Unit 3":
                    arrayList.clear();
                   arrayList.add(new materials("3rd and 4th","https://drive.google.com/file/d/1_ANk1uWuSZqGXf1q-Md-Em2aKy2hfFjB/view?usp=share_link"));
                    arrayList.add(new materials("maths uniy 3","https://drive.google.com/file/d/1XLOGzHLN3EPPLc-WTUMv1rF2FpiyTTTu/view?usp=share_link"));
                    arrayList.add(new materials("unit 3","https://drive.google.com/file/d/1p17jPv61binjsHcv3Q1swRKtB_7FUwnj/view?usp=share_link"));
                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("3rd and 4th","https://drive.google.com/file/d/1_ANk1uWuSZqGXf1q-Md-Em2aKy2hfFjB/view?usp=share_link"));
                    arrayList.add(new materials("susanna epp ...","https://drive.google.com/file/d/1u8N7x-OSGV3zbwFGzQeGSk1pUaYfcf2_/view?usp=share_link"));
                    break;
                case "Unit 5":
                    Toast.makeText(this, "Notes Not found ", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 4") && coursee.matches("Operating System")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                   arrayList.add(new materials("Unit 1 xerox","https://drive.google.com/file/d/1fcQBe8eiVv6zFR_cDnPqL2JPVxZf4ShK/view?usp=share_link"));
                   arrayList.add(new materials("unit 1 case study","https://drive.google.com/file/d/1TIMPUJAzF3KDsSWq2ZagplZ68QrEm2Wk/view?usp=share_link"));
                   arrayList.add(new materials("Unit 1 b","https://drive.google.com/file/d/1rWR-jqdZ7vDYn9jrc3EVdSvFn0zL2x2S/view?usp=share_link"));
                    break;
                case "Unit 2":
                    arrayList.clear();
                    arrayList.add(new materials("unit 2 cpu scheduling","https://drive.google.com/file/d/1umwn-wpSEtMcSEpG2oQwO9GIlXhSZqAu/view?usp=share_link"));
                    arrayList.add(new materials("Unit 2 ","https://drive.google.com/file/d/1C2_Ws-HWOm1pohZrhZzikA7OGas7p4ob/view?usp=share_link"));
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("Unit 3 xerox","https://drive.google.com/file/d/1RWcnWItofY0WQDnIZVKF8vE_tcP2Qo6A/view?usp=share_link"));
                    arrayList.add(new materials("unit 3.1","https://drive.google.com/file/d/1cbP-2qU5dxlbSi6Pp11J77WgddICusW8/view?usp=share_link"));
                    arrayList.add(new materials("Unit 3.2","https://drive.google.com/file/d/19jcxdr0QAul_RWCh0RJR6AoqBObWKCAR/view?usp=share_link"));
                    arrayList.add(new materials("process synchronization","https://drive.google.com/file/d/1TfAGbgJIjUru2DSScL69olyHnxz-S0YF/view?usp=share_link"));
                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("Unit 4 xerox","https://drive.google.com/file/d/1LByeay5vna_uES5_R2lph9tF93hccvzA/view?usp=share_link"));
                    arrayList.add(new materials("Unit 4","https://drive.google.com/file/d/1xmT3XiUMCKyENl7B5KdLql3oG05hjuqH/view?usp=share_link"));
                    break;
                case "Unit 5":
                    arrayList.clear();

                    Toast.makeText(this, "Currently Not Available", Toast.LENGTH_SHORT).show();
                    break;
            }

        }  else if (semi.matches("Semester 4") && coursee.matches("Python")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("python Textbook","https://drive.google.com/file/d/1k59o8qv697EaTt0EF8teSfqy90YtgT27/view?usp=share_link"));
                    arrayList.add(new materials("pdf python","https://drive.google.com/file/d/1RIzqvmmCcvyuNN7YUdmj_9Sp6QKRWikp/view?usp=share_link"));
                    break;
                case "Unit 2":
                    arrayList.clear();
                    Toast.makeText(this, "refer unit 1", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    arrayList.clear();
                    Toast.makeText(this, "refer unit 1 ", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    arrayList.clear();
                    Toast.makeText(this, "refer unit 1 notes", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    arrayList.clear();
                    Toast.makeText(this, "refer unit 1 notes ", Toast.LENGTH_SHORT).show();
                    break;
            }

        } else if (semi.matches("Semester 4") && coursee.matches("Software Engineering")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    Toast.makeText(this, "Error 404 ", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 2":
                    arrayList.clear();
                    Toast.makeText(this, "sorry 404", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    arrayList.clear();
                    Toast.makeText(this, "sorry unavailable", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 4":
                    arrayList.clear();
                    Toast.makeText(this, "code 404", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    arrayList.clear();
                    Toast.makeText(this, "Error 404", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 5") && coursee.matches("Computer Networks")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("unit 1 written","https://drive.google.com/file/d/1objx9ro7UvLSV0CLNv75bcGtRIheW5kL/view?usp=share_link"));
                    arrayList.add(new materials("unit 1 ppt","https://drive.google.com/file/d/17JtBQJ2wSBN5g2YkoQcfUccZNL8qKUHI/view?usp=share_link"));
                    arrayList.add(new materials("unit 1 pdf","https://drive.google.com/file/d/1-BElJPOzLMemYaTnV7HgThtV1mawbUfd/view?usp=share_link"));
                    break;
                case "Unit 2":
                    arrayList.clear();
                    arrayList.add(new materials("unit 2 ap layer","https://drive.google.com/file/d/1UPjqRnIZ77nBt8GB4C96rjHVV7ab3hNZ/view?usp=share_link"));
                    arrayList.add(new materials("unit 2 written","https://drive.google.com/file/d/1qpDBTSo-H0WzTbKR3NMnK3WtzrcYZibb/view?usp=share_link"));
                    arrayList.add(new materials("unit 2 pdf","https://drive.google.com/file/d/1V5dX2FlzRSqQgy-nMZPzuQgVIPXjHoFP/view?usp=share_link"));
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("unit 3 ppt","https://drive.google.com/file/d/1siElBphLPMTtAjfyWKBoJIjLMfsg4NmY/view?usp=share_link"));
                    arrayList.add(new materials("unit 3 pdf","https://drive.google.com/file/d/16qy02sgxXeTpvYGvzS-4_sPc4VVPxizy/view?usp=share_link"));
                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("unit 4 ppt","https://drive.google.com/file/d/1AYzB7EBZ-ozbW6AWR-xuXJ56biGNxWHl/view?usp=share_link"));
                    arrayList.add(new materials("unit 4 pdf","https://drive.google.com/file/d/1OSspntnAhtsKCyKTwhA16HkcLwVW5Wuf/view?usp=share_link"));
                    break;
                case "Unit 5":
                    arrayList.clear();
                    arrayList.add(new materials("unit 1 Notes","https://drive.google.com/file/d/1LH-2_hSG8W9OxkodfjHWhWgEAkW7NLE5/view?usp=share_link"));
                    arrayList.add(new materials("unit 1 ppt","https://drive.google.com/file/d/1MBFUOPzYculcptmqdEgjfVZ6t4FOCDuy/view?usp=share_link"));
                    arrayList.add(new materials("unit 1 pdf","https://drive.google.com/file/d/1QCaTWdJ6--Bv4ZcynYvPFh2aWWwGVexV/view?usp=share_link"));
                    break;
            }

        }else if (semi.matches("Semester 5") && coursee.matches("FLAT")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("unit 1","https://drive.google.com/file/d/13sFkNOZnJf-AOLJVNZOBQUrzCniOO8w8/view?usp=share_link"));
                    break;
                case "Unit 2":
                    arrayList.clear();
                    arrayList.add(new materials("unit 2","https://drive.google.com/file/d/1CPVQvRQJOd5qWXBW59jFANeLdGrdFTRW/view?usp=share_link"));
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("unit 3","https://drive.google.com/file/d/1BsCgOxFIeNUopisIfxFSL8v7OyGMX1IL/view?usp=share_link"));
                    break;
                case "Unit 4":
                    arrayList.clear();
                    Toast.makeText(this, "Currently unavailable", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 5":
                    arrayList.clear();
                    Toast.makeText(this, "sorry Unavailable ", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else if (semi.matches("Semester 5") && coursee.matches("IOT")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("unit 1","https://drive.google.com/file/d/1gPNJgCCgGcjGj9BQZZmw9AKRQ5LSc8Ng/view?usp=share_link"));
                    arrayList.add(new materials("unit Q and A","https://drive.google.com/file/d/1Gr_eV0-VX70ezPyTdIxKBe7K5mQHOVOJ/view?usp=share_link"));
                    arrayList.add(new materials("unit 1(1)","https://drive.google.com/file/d/1rXerAdo8eP4euNGe4x0txsHIBDctutJA/view?usp=share_link"));
                    arrayList.add(new materials("unit 1(2)","https://drive.google.com/file/d/1YcQhfcNxnzsO6IB_JW4ZqZMRhWBHuW-6/view?usp=share_link"));
                    arrayList.add(new materials("unit 1 Emb_computing","https://drive.google.com/file/d/1QgsFZ1abgwVe0ar-2G6cZ10rp10HIHDv/view?usp=share_link"));
                    break;
                case "Unit 2":
                    arrayList.clear();

                   arrayList.add(new materials("unit 2","https://drive.google.com/file/d/1dN1zpsvSrLgbr5l3ow-ONY_fxCz5D0Yb/view?usp=share_link"));
                   arrayList.add(new materials("unit 2 ppt","https://drive.google.com/file/d/1ERz9OJRk9q-gi4y6tC5-kL4Trok6UzpW/view?usp=share_link"));
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("sensors and ....","https://drive.google.com/file/d/1OulpTpbFksFZtm0aI867Vl61iI4O8-JQ/view?usp=share_link"));
                    arrayList.add(new materials("unit 3 IOT ","https://drive.google.com/file/d/1doQ70QWAYJJ5Oc_kSBQKY4W-DOlM4MR3/view?usp=share_link"));
                    arrayList.add(new materials("Domain specifications ","https://drive.google.com/file/d/1IC3Qf6IPoecP8JR0Ea77TsAR62mndV2q/view?usp=share_link"));
                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("unit 4","https://drive.google.com/file/d/1s7nlTeYoTXgAvlgsXzHN4WzmSiSmT4on/view?usp=share_link"));
                    arrayList.add(new materials("unit 4 (2)","https://drive.google.com/file/d/1v6DpsBWjT91skS2mgqHEDCGv_ax5P7Xg/view?usp=share_link"));
                    break;
                case "Unit 5":
                    arrayList.clear();
                    arrayList.add(new materials("unit 5","https://drive.google.com/file/d/1JeJsEJ3AyQsB1tlMH7XbRTINryLjC6P4/view?usp=share_link"));
                    arrayList.add(new materials("unit 5 written","https://drive.google.com/file/d/1ZHBpNlgoCRmXHJi_dzxFLHc2mPOmdLpa/view?usp=share_link"));
                    arrayList.add(new materials("unit 5 device","https://drive.google.com/file/d/1kUiCrJay8R_5fK5ZFULgbRbvCghndyPl/view?usp=share_link"));
                    break;
            }

        }else if (semi.matches("Semester 5") && coursee.matches("OOMD")) {
            switch (unity){
                case "Unit 1":
                    arrayList.clear();
                    arrayList.add(new materials("unit 1 ","https://drive.google.com/file/d/1u3qHLp9-_14Q6S4-GaXhBY1KdVmRiJHC/view?usp=share_link"));
                    arrayList.add(new materials("unit (2)","https://drive.google.com/file/d/1wfRriFmCaDHyL8YXu9DtNr60AkI8LS4B/view?usp=share_link"));
                    break;
                case "Unit 2":
                    arrayList.clear();
                    Toast.makeText(this, "Not Available", Toast.LENGTH_SHORT).show();
                    break;
                case "Unit 3":
                    arrayList.clear();
                    arrayList.add(new materials("unit 3 ","https://drive.google.com/file/d/1kg5_a8yGd1sxj3AtqXipO7zIUJqtZGZH/view?usp=share_link"));

                    break;
                case "Unit 4":
                    arrayList.clear();
                    arrayList.add(new materials("unit 4","https://drive.google.com/file/d/1KReL-sLhWOSXqUQKVxs4eMEiqTl5kCs9/view?usp=share_link"));
                    break;
                case "Unit 5":
                    arrayList.clear();
                   arrayList.add(new materials("unit 5","https://drive.google.com/file/d/1li_1EF10QDZLsGzISWxFFzO3KD2crocQ/view?usp=share_link"));
                    break;
            }

        }




    }
}
