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
import com.daneshnaik.chatbot.Tables.pdfclass;

import java.util.ArrayList;

public class pdfAdapter extends RecyclerView.Adapter<pdfAdapter.ViewHolder>{
    Context context;
    ArrayList<pdfclass> pdfclassArrayList;
    public pdfAdapter(Context context,ArrayList<pdfclass> pdfclassArrayList){
        this.context=context;
        this.pdfclassArrayList=pdfclassArrayList;
    }
    @NonNull
    @Override
    public pdfAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.pdf_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pdfAdapter.ViewHolder holder, int position) {
   pdfclass pdfclasses=pdfclassArrayList.get(position);
   holder.pdf_nam.setText(pdfclasses.getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, sems_selector.class);
            intent.putExtra("name",pdfclasses.getName());
            intent.putExtra("url",pdfclasses.getUrl());
            context.startActivity(intent);

        }
    });
    }

    @Override
    public int getItemCount() {
        return pdfclassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pdf_nam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pdf_nam=itemView.findViewById(R.id.pdfname);


        }
    }
}