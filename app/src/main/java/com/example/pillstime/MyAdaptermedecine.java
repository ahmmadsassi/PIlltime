package com.example.pillstime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdaptermedecine extends RecyclerView.Adapter<MyAdaptermedecine.MyViewHolder> {

Context context;
ArrayList<Medecine> list ;
RecyclerView recyclerView;


    public MyAdaptermedecine(Context context, ArrayList<Medecine> list,RecyclerView recyclerView) {
        this.context = context;
        this.list = list;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.medecine_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
Medecine medecine = list.get(position);
holder.medecinename.setText(medecine.getName());
holder.viewone.setText(medecine.getFirstDate());
holder.viewtwo.setText(medecine.getSeconddate());
holder.viewthree.setText(medecine.getThereaddate());
holder.viewdetails.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(context,Detailslayaout.class);
        i.putExtra("name",medecine.getName());
        i.putExtra("desc",medecine.getDescription());
        if(medecine.getFirstDate() != null){
            i.putExtra("fst",medecine.getFirstDate());

        }
        if(medecine.getSeconddate() != null){
            i.putExtra("sct",medecine.getSeconddate());

        }
        if(medecine.getThereaddate() != null){
            i.putExtra("tht",medecine.getThereaddate());

        }
        i.putExtra("datestart",medecine.getStartDate());
        i.putExtra("enddate",medecine.getEndDate());
        context.startActivity(i);
    }

});

holder.viewone.setOnClickListener(new View.OnClickListener() {
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE,medecine.getName());
        context.startActivity(i);
        holder.viewone.setTextColor(R.color.green);
    }
});
        holder.viewtwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE,medecine.getName());
                context.startActivity(i);
                holder.viewtwo.setTextColor(R.color.green);
            }
        });
        holder.viewthree.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE,medecine.getName());
                context.startActivity(i);
                holder.viewthree.setTextColor(R.color.green);
            }
        });
    }

    @Override
    public int getItemCount() {

        if (list.size() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
        return list.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{
      TextView medecinename, viewone,viewtwo,viewthree,viewfore;
      ImageView viewdetails;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            medecinename = itemView.findViewById(R.id.textViewMedicineName);
            viewone=itemView.findViewById(R.id.textViewOne);
            viewtwo= itemView.findViewById(R.id.textViewTwo);
            viewthree=itemView.findViewById(R.id.textViewThree);
            viewdetails =itemView.findViewById(R.id.imagedetails);


        }
    }




}
