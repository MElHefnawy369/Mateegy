package com.example.mohamedelhefnawy.socialnetwork_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUp extends AppCompatActivity {

    private EditText userName;
    private EditText mail;
    private EditText password;
    private Button createAccountButton;
    private TextView incorrectPassword;
    private Spinner questions;
    private TextView insertquestion;
    private TextView insertanswer;
    private EditText answerquestion;
    private String[] arraySpinner;
    private String userNameinput , mailinput , passwordinput  , answerquestioninput , questioninput;
    private Integer questionnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        userName = (EditText) findViewById(R.id.email) ;
        mail = (EditText) findViewById(R.id.editText4) ;
        password = (EditText) findViewById(R.id.password) ;
        createAccountButton = (Button) findViewById(R.id.signIn);
        incorrectPassword = (TextView) findViewById(R.id.error);
        insertquestion=(TextView) findViewById(R.id.insertquestion);
        insertanswer=(TextView) findViewById(R.id.insertanswer);
        answerquestion=(EditText) findViewById(R.id.answerquestion);


        this.arraySpinner = new String[] {
                "What's Your Pet name?", "What's Your Cousin name?", "What's Your favourite food?", "What's Your Uncle's name?", "What's Your flat number?","What's Your favourite color?","1","2","2","2","2"
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
                ParseQuery<ParseObject> query5 = ParseQuery.getQuery("NormalUser");
                query5.whereEqualTo("mail", mailinput);
                query5.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject userFromDB, ParseException e) {
                        if (userFromDB != null) {
                            Toast.makeText(SignUp.this, "you are already registered", Toast.LENGTH_LONG).show();
                        } else {
                            if ((passwordinput.length()) <= 8) {
                                incorrectPassword.setText("Password is too short (It should be equal or more than 8 characters");
                            } else {
                                ParseObject newUser = new ParseObject("NormalUser");

                                newUser.put("userName", userNameinput);
                                newUser.put("mail", mailinput);
                                newUser.put("password", passwordinput);
                                newUser.put("questionNumber", questionnumber);
                                newUser.put("question", questioninput);
                                newUser.put("answerQuestion", answerquestioninput);

                                newUser.saveInBackground();
                                startActivity(new Intent(SignUp.this, TransitionToSignIn.class));
                            }
                        }
                    }});

                }


        });

    }
}


