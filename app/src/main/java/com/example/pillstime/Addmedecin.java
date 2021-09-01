package com.example.pillstime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Addmedecin extends AppCompatActivity {
    private Button save ;
    private EditText name ;
    private EditText how ;
    private EditText pillsnumber  ;
    private EditText phonenumber ;
    private TextView from;
    private TextView to;
    private String fromdb;
    private String todb;
    DatabaseReference reff;
    Medecin medecin ;
    private ArrayList<String> notifs = new ArrayList<>();
    private RadioButton morning;
    private RadioButton lunchtime;
    private RadioButton midnight;
    private ArrayList<String>  times = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener mdatesetlistenerf;
    private DatePickerDialog.OnDateSetListener mdatesetlistenert;
    private  TextView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmedecin);
        getSupportActionBar().hide();
        save = (Button) findViewById(R.id.save);
        this.name = (EditText) findViewById(R.id.nameofdrag);
        how = (EditText) findViewById(R.id.how);
        pillsnumber = (EditText) findViewById(R.id.pills);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        from = (TextView) findViewById(R.id.From11);
        to = (TextView) findViewById(R.id.to);
        morning =(RadioButton) findViewById(R.id.morning);
        lunchtime = (RadioButton) findViewById(R.id.lunchtime);
        midnight = (RadioButton) findViewById(R.id.midnight);
        reff = FirebaseDatabase.getInstance().getReference().child("Medecins");
        medecin = new Medecin();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal;
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(Addmedecin.this, android.R.style.Theme_Holo_Dialog_MinWidth, mdatesetlistenerf, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal;
                cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(Addmedecin.this, android.R.style.Theme_Holo_Dialog_MinWidth, mdatesetlistenert, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();
            }
        });

        mdatesetlistenerf = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fromdb = year +"/"+ month +"/"+ dayOfMonth ;
            }
        };
        mdatesetlistenert = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                todb = year +"/"+ month +"/"+ dayOfMonth ;
            }
        };


    }


    public void add(View view) {

        String n = name.getText().toString();
        String h = how.getText().toString();
        String ph =phonenumber.getText().toString();
        int p = Integer.parseInt(pillsnumber.getText().toString());
        if(morning.isChecked() ){

            times.add("8Am");

        }
        if(lunchtime.isChecked() ){

            times.add("12Am");

        }
        if(midnight.isChecked() ){

            times.add("8Pm");

        }
        medecin.setName(n);
        medecin.setHow(h);
        medecin.setPhone_number(ph);
        medecin.setPills(p);
        medecin.setFrom(fromdb);
        medecin.setTo(todb);
        medecin.setTimes(times);
        String key = reff.push().getKey();
        reff.child(key).setValue(medecin);
        Toast.makeText(getApplicationContext(), "Data inserted !", Toast.LENGTH_LONG).show();
    }

}