package com.daneshnaik.chatbot.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daneshnaik.chatbot.Activities.sems_selector;
import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.materials;

import java.util.ArrayList;

public class materialsAdapter extends RecyclerView.Adapter<materialsAdapter.ViewHolder> {
    Context context;
    ArrayList<materials> materialsArrayList;

    public materialsAdapter(Context context, ArrayList<materials> materialsArrayList) {
        this.context = context;
        this.materialsArrayList = materialsArrayList;
    }

    @NonNull
    @Override
    public materialsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pdf_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull materialsAdapter.ViewHolder holder, int position) {
        materials materialss=materialsArrayList.get(position);
     holder.pdfname.setText(materialss.getPdfname());
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent=new Intent(context, sems_selector.class);
            intent.putExtra("name",materialss.getPdfname());
            intent.putExtra("url",materialss.getPdfurl());
            context.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return  materialsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pdfname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pdfname=itemView.findViewById(R.id.pdfname);
        }
    }
}
