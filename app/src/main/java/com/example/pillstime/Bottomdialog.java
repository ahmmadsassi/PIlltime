package com.example.pillstime;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bottomdialog extends BottomSheetDialogFragment {

    private TextView namemedecin , setalarme , pillsnumber,times;
private ImageView close ;
public String name,time,numberp;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_dialog,container,false);
        namemedecin = view.findViewById(R.id.text_title);
        setalarme = view.findViewById(R.id.textview11);
        pillsnumber = view.findViewById(R.id.visit);
        times = view.findViewById(R.id.times1);
        close = view.findViewById(R.id.close);

        namemedecin.setText(name);
        System.out.println(name);
   //     pillsnumber.setText(numberp);
     //   times.setText(time);

       setalarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "hello");
                startActivity(i);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });

        return view;
    }


    public void getdetails(String namefromscanner ){

         name = namefromscanner ;




    }
/*
       public void fetchurl11(String namesca,String numberpillssca , String timesca){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    name = namesca;
                    numberp = numberpillssca;
                    time = timesca;

                    
                }
            });

        }

 */
    }

