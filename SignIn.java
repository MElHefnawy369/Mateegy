package com.example.mohamedelhefnawy.socialnetwork;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLDataException;

import static com.example.mohamedelhefnawy.socialnetwork.R.id.email;

public class SignIn extends AppCompatActivity {
    private EditText username;
    private EditText passward;
    private Button signin,forgetpass,createnew;
    private TextView error;
    private String email_inp,passward_inp;
    private Connection Con;
	private Statement St;
	private ResultSet Rs;
    private String query;
    private String DBMail;
    private String DBPassword;

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
        try{
            Con = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6149201","sql6149201","KyR5SDXGgK");
            St = Con.createStatement();
        }
        catch(SQLException e){
			    // show here that we can't connect to Database
		}

        View.OnClickListener ourOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_inp = username.getText().toString();
                passward_inp = passward.getText().toString();
                query = "SELECT Mail, Password FROM Users WHERE Mail = '"+email_inp+"';";
                try{Rs = St.executeQuery(query);
                    DBMail = Rs.getString("Users.Mail");
                    DBPassword = Rs.getString("Users.Password");
                   }catch(SQLException e){
                    // show here that we can't connect to Database
                }
                    
               if (email_inp == DBMail && password_inp = DBPassword){
               
                    //move to homepage
                }else{
                    String error_mess= "Wrong Passward or wrong E-mail";
                    error.append(error_mess);
                }
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
