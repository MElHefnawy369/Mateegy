package com.example.mohamedelhefnawy.socialnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

        this.arraySpinner = new String[] {
                "What's Your Pet name?", "What's Your Cousin name?", "What's Your favourite food?", "What's Your Uncle's name?", "What's Your flat number?","What's Your favourite color?"
        };
        questions = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        questions.setAdapter(adapter);
        questioninput = questions.getSelectedItem().toString();
    createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameinput = userName.getText().toString();
                mailinput = mail.getText().toString();
                passwordinput = password.getText().toString();
                answerquestioninput = answerquestion.getText().toString();
                questioninput = questions.getSelectedItem().toString();
                //confirmPasswordinput = confirmPassword.getText().toString();
                if((passwordinput.length())<=8){
                    incorrectPassword.setText("Password is too short (It should be equal or more than 8 characters");
                }
                //else if(!(passwordinput.equals(confirmPasswordinput))){
                //    incorrectPassword.setText("Password is NOT matched");
                //}
                //else if( /*two usernames are the same*/ ){
                    //Condition for same Username in database
                //}
                else {
                    /*Save User data in data base*/
                    /*Transfer to transition page from signup to sign in*/
                            //startActivity(new Intent(SignUp.this, TransitionToSignIn.class));

                }
            }
        });

    }
}


