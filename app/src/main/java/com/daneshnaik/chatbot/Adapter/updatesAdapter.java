package com.daneshnaik.chatbot.Adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.daneshnaik.chatbot.R;
import com.daneshnaik.chatbot.Tables.Users;
import com.daneshnaik.chatbot.Tables.updates_tables;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;


public class updatesAdapter extends RecyclerView.Adapter<updatesAdapter.ViewHolder> {

Context context;
ArrayList<updates_tables> tablesArrayList;
public updatesAdapter(Context context,ArrayList<updates_tables> tablesArrayList){
    this.context=context;
    this.tablesArrayList=tablesArrayList;
}
    public  void setfilterdupdatelist(ArrayList<updates_tables> filterlist) {
        this.tablesArrayList = filterlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public updatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(context).inflate(R.layout.updates_single_element,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull updatesAdapter.ViewHolder holder, int position) {
    updates_tables updatesTables=tablesArrayList.get(position);
    holder.updates_title.setText(updatesTables.getTitle());
        holder.updates_descript.setText(updatesTables.getDescription());
        if (holder.updates_links.equals("")){
            holder.updates_links.setText("No Link");

        }else{
            holder.updates_links.setVisibility(View.VISIBLE);
            holder.updates_links.setText(updatesTables.getLinks());
        }
       if (holder.another_link.equals("")){
           holder.another_link.setText("no Link");

       }else{
           holder.another_link.setVisibility(View.VISIBLE);
           holder.another_link.setText(updatesTables.getAnotherlink());
       }


        holder.updates_date.setText(updatesTables.getDate());

        int lastPosition=-1;
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return tablesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView updates_date,updates_title,updates_descript,updates_links,another_link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            updates_date=itemView.findViewById(R.id.text_updates_date);
            updates_title=itemView.findViewById(R.id.text_updates_title);
            updates_descript=itemView.findViewById(R.id.text_updates_description);
            updates_links=itemView.findViewById(R.id.text_updates_links);
            another_link=itemView.findViewById(R.id.text_updates_anotherlinks);

        }

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }
}
