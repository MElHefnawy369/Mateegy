package com.example.mohamedelhefnawy.socialnetwork;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.mohamedelhefnawy.socialnetwork.R.id.email;

public class SignIn extends AppCompatActivity {
    private EditText username;
    private EditText passward;
    private Button signin,forgetpass,createnew;
    private TextView error;
    private String email_inp,passward_inp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        username= (EditText) findViewById(email);
        passward= (EditText) findViewById(R.id.pass);
        signin= (Button) findViewById(R.id.signIn);
        forgetpass= (Button) findViewById(R.id.forgetPass);
        createnew= (Button) findViewById(R.id.createNew);
        error= (TextView) findViewById(R.id.error);

        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_inp = username.getText().toString();
                passward_inp = passward.getText().toString();
               /* if (/*look for the email in database){

                    //move to homepage
                }else{
                    String error_mess= "Wrong Passward or wrong E-mail";
                    error.append(error_mess);
                }*/
                /*attributes of activated user should be more than what is written according to database*/
                /*NormalUser activatedUser = new NormalUser(email_inp,passward_inp);
                Intent i = new Intent(SignIn.this, HomePage.class);
                i.putExtra("sample Object", activatedUser);
                startActivity(i);*/
                startActivity(new Intent(SignIn.this, HomePage.class));
            }
        };
        signin.setOnClickListener(ourOnClickListener);
        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, ForgetPassword.class));
            }
        });
    }
}
