    package com.example.android.stockmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

    public class Register extends AppCompatActivity {
            private static final String TAG = LoginPage.class.getSimpleName();

            private EditText email, password, confirmpass;
            private Button Signup;
            private TextView Redirect;

            private FirebaseAuth mAuth;


            private ProgressBar progressBar;

            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_register);

                mAuth= FirebaseAuth.getInstance();

                email =findViewById(R.id.et_email);
                password =(EditText) findViewById(R.id.et_pass);
                confirmpass =(EditText) findViewById(R.id.et_confirmPass);
                Signup =(Button) findViewById(R.id.signup);
                progressBar=(ProgressBar)findViewById(R.id.pb_2);
                Redirect = (TextView)findViewById(R.id.tv_redirect);

                mAuth=FirebaseAuth.getInstance();

                Signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String Email = email.getText().toString().trim();
                        final String Password = password.getText().toString().trim();
                        final String ConfirmPass = confirmpass.getText().toString().trim();

                        if (TextUtils.isEmpty(Email)){

                            Toast.makeText(Register.this,"Enter Email Address",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(!(Password.equals(ConfirmPass))){
                            Toast.makeText(Register.this, "Passwords Do not Match", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(Password.length()<6)
                        {
                            Toast.makeText(Register.this,"Password must be more than 6 letters",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        progressBar.setVisibility(View.VISIBLE);
                        //creating user

                        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(Register.this,"createUserWithEmailSuccessful:onComplete"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(Register.this,"Authentication Failed ."+task.getException(),Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    startActivity(new Intent(Register.this,LoginPage.class));
                                    finish();
                                }
                            }
                        });


                    }


                });


                Redirect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Register.this, LoginPage.class));
                    }


                });

            }

            @Override
            protected void onResume() {
                super.onResume();
                progressBar.setVisibility(View.GONE);
            }

    }
