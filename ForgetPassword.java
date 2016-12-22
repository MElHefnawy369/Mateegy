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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by MohamedElHefnawy on 12/16/2016.
 */



public class ForgetPassword extends AppCompatActivity {

    private EditText mail;
    private TextView insertquestionbefore;
    private TextView insertanswerbefore;
    private EditText answerbefore;
    private String[] arraySpinner;
    private Spinner questions;
    private Button signinbutton;
    private String mailinput, answerquestioninput, questioninput;
    private Integer questionnumber;
    private String pp;
    private Integer num;
    private TextView error_k;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);

        mail = (EditText) findViewById(R.id.mail2);
        insertquestionbefore = (TextView) findViewById(R.id.insertquestionbefore);
        insertanswerbefore = (TextView) findViewById(R.id.insertanswerbefore);
        answerbefore = (EditText) findViewById(R.id.answerquestionbefore);
        signinbutton = (Button) findViewById(R.id.signIn2);
        error_k=(TextView) findViewById(R.id.errorof);
        this.arraySpinner = new String[]{
                "What's Your Pet name?", "What's Your Cousin name?", "What's Your favourite food?", "What's Your Uncle's name?", "What's Your flat number?", "What's Your favourite color?"
        };
        questions = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        questions.setAdapter(adapter);
        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mailinput = mail.getText().toString();
                answerquestioninput = answerbefore.getText().toString();
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
                ParseQuery<ParseObject> query = ParseQuery.getQuery("NormalUser");
                query.whereEqualTo("mail", mailinput);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> userlist, ParseException e) {
                        if (e != null) {
                            String ee = ("Failure");
                            error_k.append(ee);
                        } else {
                            for (ParseObject x : userlist) {
                                num = x.getInt("questionNumber");
                                pp = x.getString("answerQuestion");
                                if (num != questionnumber) {
                                    error_k.append("Wrong Question or Wrong Answer");
                                } else if (pp.equals(answerquestioninput)) {
                                    startActivity(new Intent(ForgetPassword.this, HomePage.class));
                                } else {
                                    error_k.append("Wrong Question or Wrong Answer");
                                }
                            }

                        }
                    }
                });
                // if(answerquestioninput==answer in database && mailinput= mail in database)
                //startActivity(new Intent(ForgetPassword.this, HomePage.class));

            }

        });

    }
}