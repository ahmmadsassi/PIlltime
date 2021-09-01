package com.example.pillstime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MedecineListe extends AppCompatActivity {


    DatabaseReference mgetReference;
    RecyclerView recyclerView;
    FirebaseAuth fau;
    String currentuser;
    ArrayList<Medecine> listm;
    MyAdaptermedecine adaptermedecine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecine_liste);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mgetReference = FirebaseDatabase.getInstance().getReference(currentuser);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MedecineListe.this));



        listm = new ArrayList<>();
        adaptermedecine = new MyAdaptermedecine(MedecineListe.this,listm,recyclerView);
        recyclerView.setAdapter(adaptermedecine);
        mgetReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Medecine medecine = dataSnapshot.getValue(Medecine.class);
                    listm.add(medecine);
                }
                adaptermedecine.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {


                Toast.makeText(MedecineListe.this,"Error",Toast.LENGTH_SHORT);

            }
        });




    }
}