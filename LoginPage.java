package com.example.android.stockmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    final String Tag="EmailLogin Successful";

    EditText usrname,usrpass;
    Button login ,signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login=(Button)findViewById(R.id.btn_login);
        signup=(Button)findViewById(R.id.btn_signup);


        //firebase instance
        mAuth =FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(LoginPage.this,MainActivity.class));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrname=(EditText)findViewById(R.id.editText_username);
                usrpass=(EditText)findViewById(R.id.editText_pass);

                String Email=usrname.getText().toString();
                final String Pass=usrpass.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //authentcate user
                mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful())
                        {
                            Toast.makeText(LoginPage.this,"Incorrect Email Id or Password",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            startActivity(new Intent(LoginPage.this,MainActivity.class));
                        }
                    }
                });
            }
        });

    }


}
