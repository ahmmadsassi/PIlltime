package com.example.pillstime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConfirmeMedecineDetails extends AppCompatActivity {
    DatabaseReference mgetReference;
    DatabaseReference mpostReference;
    TextView textViewMedName;
    TextView textViewMedDescription;
    TextView textViewMedInterval;
    TextView textViewMedStartDate;
    TextView textViewMedEndDate;
    TextView firstime;
    TextView secondtime1;
    TextView thereadtime;
    Button buttonEditMedicine;
    Button buttonSaveMedicine;
    String name ;
    String description ;
    String startDate ;
    String  endDate;
    Medecine medecine;
    FirebaseAuth fau;
    String currentuser;
    String firsttime;
    String secondtime;
    String theredtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirme_medecine_details);
        getSupportActionBar().hide();

        textViewMedName = (TextView) findViewById( R.id.textViewMedName);
        textViewMedDescription = (TextView) findViewById( R.id.textViewMedDescription);
       firstime=(TextView) findViewById(R.id.firsttime);
       secondtime1=(TextView)findViewById(R.id.second_time);
       thereadtime=(TextView) findViewById(R.id.thereadtime);
        textViewMedStartDate = (TextView) findViewById( R.id.textViewMedStartDate);
        textViewMedEndDate = (TextView) findViewById(R.id.textViewMedEndDate);
        buttonEditMedicine = ( Button ) findViewById(R.id.buttonEditMedicine);
        buttonSaveMedicine = (Button)  findViewById(R.id.buttonSaveMedicine);
         currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


        buttonSaveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);

            }
        });

        Intent intent = getIntent();
        String id = intent.getStringExtra("valeurdid");


        mpostReference = FirebaseDatabase.getInstance().getReference().child(currentuser);
        mgetReference = FirebaseDatabase.getInstance().getReference("Medecins");
        mgetReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Medecin  medecine = new Medecin();
                medecine = snapshot.child(id).getValue(Medecin.class);

                 name = String.valueOf(medecine.getName());
                firsttime = String.valueOf(medecine.getFirsttime());
                secondtime = String.valueOf(medecine.getSecondtime());
                theredtime = String.valueOf(medecine.getThereadtime());
                 description = String.valueOf(medecine.getHow());
                startDate = String.valueOf(medecine.getFrom());
                endDate = String.valueOf(medecine.getTo());
                textViewMedName.setText(name);
                textViewMedDescription.setText(description);
               if(firstime != null){

                   firstime.setText(firsttime);
               }
                if(secondtime != null){

                    secondtime1.setText(secondtime);
                }
                if(thereadtime != null){

                    thereadtime.setText(theredtime);
                }

                textViewMedStartDate.setText(startDate);
                textViewMedEndDate.setText(endDate);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ConfirmeMedecineDetails.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void save(View view) {

       Medecine medecine = new Medecine();
        medecine.setName(name);
        if(firstime != null){

            medecine.setFirstDate(firsttime);
        }
        if(secondtime != null){

            medecine.setSeconddate(secondtime);
        }
        if(thereadtime != null){

            medecine.setThereaddate(theredtime);
        }

        medecine.setDescription(description);
        medecine.setStartDate(startDate);
        medecine.setEndDate(endDate);
        String key = mpostReference.push().getKey();
        mpostReference.child(key).setValue(medecine);
        Intent intent = new Intent(ConfirmeMedecineDetails.this,medecine_success.class);
        intent.putExtra("name",name);
        intent.putExtra("des",description);
        if(firstime != null){

            intent.putExtra("fs",firsttime);
        }
        if(secondtime != null){

            intent.putExtra("sc",secondtime);
        }
        if(thereadtime != null){

            intent.putExtra("th",theredtime);
        }

        intent.putExtra("startd",startDate);
        intent.putExtra("endd",endDate);
        startActivity(intent);

    }
}