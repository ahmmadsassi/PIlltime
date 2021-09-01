package com.example.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class medecine_success extends AppCompatActivity {
    TextView textViewMedName;
    TextView textViewMedDescription;
    TextView fst,sct,tht;
    TextView textViewMedStartDate;
    TextView textViewMedEndDate;
    Button buttonAddMedicine;
    Button buttonHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_success);
        getSupportActionBar().hide();

        textViewMedName = (TextView) findViewById( R.id.textViewMedicineName);
        textViewMedDescription = (TextView) findViewById( R.id.textViewMedicineDescription);
       fst= (TextView) findViewById(R.id.fst);
       sct = (TextView) findViewById(R.id.st);
       tht = (TextView) findViewById(R.id.thtime);
        textViewMedStartDate = (TextView) findViewById(R.id.textViewMedicineStartDate);
        textViewMedEndDate = (TextView) findViewById(R.id.textViewMedicineEndDate);
        buttonAddMedicine = ( Button ) findViewById(R.id.buttonAddMedicine);
        buttonHomepage = (Button)  findViewById(R.id.buttonHomePage);

        Intent intent = getIntent();
        textViewMedName.setText(intent.getStringExtra("name"));
        textViewMedDescription.setText(intent.getStringExtra("des"));
        fst.setText(intent.getStringExtra("fs"));
        sct.setText(intent.getStringExtra("sc"));
        tht.setText(intent.getStringExtra("th"));

        textViewMedStartDate.setText( intent.getStringExtra("startd"));
        textViewMedEndDate.setText(intent.getStringExtra("endd"));

        buttonAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecine_success.this,Scanner.class));
            }
        });
        buttonHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medecine_success.this,MedecineListe.class));
            }
        });








    }
}