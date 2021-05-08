package com.example.pillstime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private EditText name ;
    private EditText memail;
    private EditText  mpassword ;
    private TextView signin ;
    private TextView signup;
    FirebaseAuth fau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        name = (EditText) findViewById(R.id.name);
        memail =(EditText) findViewById(R.id.email1);
        mpassword = (EditText) findViewById(R.id.password1);
        signin = (TextView) findViewById(R.id.signin1);
        signup =(TextView) findViewById(R.id.signup1);
        fau = FirebaseAuth.getInstance();


        if(fau.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),Signin.class));
            finish();
        }


signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email = memail.getText().toString().trim();
        String password = mpassword.getText().toString().trim();
        if(TextUtils.isEmpty((email))){

            memail.setError("Email is Required");
            return;

        }
        if(TextUtils.isEmpty((password))){

            memail.setError("password is Required");
            return;

        }
        if(password.length()<6){

            memail.setError("password must be  >= 6 characters");
            return;

        }

        fau.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Signup.this,"User created ",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signup.this,Dashbord.class));

                }else {

                    Toast.makeText(Signup.this,"Error "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();



                }


            }
        });


    }
});
signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(Signup.this,Signin.class));

    }
});
    }
}

