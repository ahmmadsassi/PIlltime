package com.example.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detailslayaout extends AppCompatActivity {

    TextView name , firsttime , secondtime , thereadtime , description ,datestart,enddate;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailslayaout);
        getSupportActionBar().hide();

        name = (TextView) findViewById(R.id.medecinenameD);
        firsttime = (TextView) findViewById(R.id.OneD);
        secondtime = (TextView) findViewById(R.id.TwoD);
        thereadtime = (TextView) findViewById(R.id.ThreeD);
        description = (TextView) findViewById(R.id.discD);
        datestart = (TextView) findViewById(R.id.startD);
        enddate = (TextView) findViewById(R.id.EndD);


        Intent i = getIntent();


        name.setText(i.getStringExtra("name"));
        if(i.getStringExtra("fst") != null){

            firsttime.setText(i.getStringExtra("fst"));
        }
        if(i.getStringExtra("sct") != null){

            secondtime.setText(i.getStringExtra("sct"));
        }
        if(i.getStringExtra("tht") != null){

            thereadtime.setText(i.getStringExtra("tht"));
        }
        description.setText(i.getStringExtra("desc"));
        datestart.setText(i.getStringExtra("datestart"));
        enddate.setText(i.getStringExtra("enddate"));









    }
}