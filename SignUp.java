package com.example.mohamedelhefnawy.socialnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLDataException;

public class SignUp extends AppCompatActivity {

    private EditText userName;
    private EditText mail;
    private EditText password;
    private EditText confirmPassword;
    private Button createAccountButton;
    private TextView incorrectPassword;
    private Spinner questions;
    private TextView insertquestion;
    private TextView insertanswer;
    private EditText answerquestion;
    private String[] arraySpinner;
    private String userNameinput , mailinput , passwordinput , confirmPasswordinput , answerquestioninput , questioninput;
    private Integer questionnumber;
    private Connection Con;
	private Statement St1;
    private Statement St2;
	private ResultSet Rs;
    private String query1;
    private String query2;
    private String DBMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        
        
        userName = (EditText) findViewById(R.id.email) ;
        mail = (EditText) findViewById(R.id.editText4) ;
        password = (EditText) findViewById(R.id.password) ;
        //confirmPassword = (EditText) findViewById(R.id.editText2) ;
        createAccountButton = (Button) findViewById(R.id.signIn);
        incorrectPassword = (TextView) findViewById(R.id.error);
        insertquestion=(TextView) findViewById(R.id.insertquestion);
        insertanswer=(TextView) findViewById(R.id.insertanswer);
        answerquestion=(EditText) findViewById(R.id.answerquestion);
        try{
        Con = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6149201","sql6149201","KyR5SDXGgK");
        St1 = Con.createStatement();
        St2 = Con.createStatement();
        }
        catch(SQLException e){
			// show here that we can't connect to Database
		}

        this.arraySpinner = new String[] {
                "What's Your Pet name?", "What's Your Cousin name?", "What's Your favourite food?", "What's Your Uncle's name?", "What's Your flat number?","What's Your favourite color?"
        };
        questions = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        questions.setAdapter(adapter);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameinput = userName.getText().toString();
                mailinput = mail.getText().toString();
                passwordinput = password.getText().toString();
                answerquestioninput = answerquestion.getText().toString();
                questioninput = questions.getSelectedItem().toString();

                switch (questioninput) {
                    case "What's Your Pet name?":
                        questionnumber = 1;
                        break;
                    case "What's Your Cousin name?":
                        questionnumber = 2;
                        break;
                    case "What's Your favourite food?":
                        questionnumber = 3;
                        break;
                    case "What's Your Uncle's name?":
                        questionnumber = 4;
                        break;
                    case "What's Your flat number?":
                        questionnumber = 5;
                        break;
                    case "What's Your favourite color?":
                        questionnumber = 6;
                        break;
                } 
                query1 = "SELECT Mail FROM Users WHERE Mail = '"+ mailinput +"';";
                query2 = "INSERT INTO Users ('UserName','Mail','Password','Q" + answerquestioninput + "') values ('"+userNameinput+"','"+mailinput+"','"+passwordinput+"','"+answerquestioninput+"');";
                try{
                Rs = St1.executeQuery(query1);
                DBMail = Rs.getString("Users.Mail");
                }
                catch(SQLException e){
                                         // show here that we can't connect to Database
		        }
                confirmPasswordinput = confirmPassword.getText().toString();
                if((passwordinput.length())<=8){
                    incorrectPassword.setText("Password is too short (It should be equal or more than 8 characters");
                }
                //else if(!(passwordinput.equals(confirmPasswordinput))){
                //    incorrectPassword.setText("Password is NOT matched");
                }
                else if(mailinput == DBMail){
                    //Condition for same Username in database
                }
                else {try{St2.executeUpdate(query2);
                         }
                      catch(SQLException e){
			           	                   // show here that we can't connect to Database
		                }
                }
            }
        });

    }
}


