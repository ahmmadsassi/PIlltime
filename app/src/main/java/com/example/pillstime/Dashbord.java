package com.example.pillstime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dashbord extends AppCompatActivity {

    private TextView scanbtn,aboutbtn,listbtn,addmedecinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        scanbtn = (TextView) findViewById(R.id.Scanbtn);
        aboutbtn = (TextView) findViewById(R.id.aboutbtn);
        listbtn = (TextView) findViewById(R.id.listbtn);
        addmedecinbtn= (TextView) findViewById(R.id.addmedecinbtn);


        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashbord.this,Scanner.class));
            }
        });
addmedecinbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(Dashbord.this,Addmedecin.class));
    }
});







    }
}