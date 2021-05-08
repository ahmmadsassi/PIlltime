package com.example.pillstime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {


    private EditText memail ;
    private EditText mpassword ;
    private TextView singin ;
    private TextView singup;
    FirebaseAuth fau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_login);


        memail = (EditText) findViewById(R.id.email);
        mpassword= (EditText) findViewById(R.id.password);
        singin = (TextView) findViewById(R.id.signin);
        singup =(TextView) findViewById(R.id.signip);
        fau = FirebaseAuth.getInstance();



        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if (TextUtils.isEmpty((email))) {

                    memail.setError("Email is Required");
                    return;

                }
                if (TextUtils.isEmpty((password))) {

                    memail.setError("password is Required");
                    return;

                }
                if (password.length() < 6) {

                    memail.setError("password must be  >= 6 characters");
                    return;

                }


                fau.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signin.this,"User is correct ",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signin.this,Dashbord.class));
                        }else {
                            Toast.makeText(Signin.this,"Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this,Signup.class));
            }
        });
    }




}