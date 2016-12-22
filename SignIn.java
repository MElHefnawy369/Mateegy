package com.example.mohamedelhefnawy.socialnetwork_2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static com.example.mohamedelhefnawy.socialnetwork_2.R.id.email;

public class SignIn extends AppCompatActivity {
    private EditText username;
    private EditText passward;
    private Button signin,forgetpass,createnew;
    private TextView error;
    private String email_inp,passward_inp,pp;
    private int flag;
    static public UserData currentUserData;
    private String currentUserName;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        flag = getIntent().getIntExtra("flag",0);
        if(flag!=1){
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("3QYuv1xDPcx8jjLoJE01E3f0RTJVlhgjyHX5O94c")
                .clientKey("U37tmIlz8iQorU0mkR8KHz3id6jR081cAeeTF62p")
                .server("https://parseapi.back4app.com/").build()
        );}

        username= (EditText) findViewById(email);
        passward= (EditText) findViewById(R.id.pass);
        signin= (Button) findViewById(R.id.signIn);
        forgetpass= (Button) findViewById(R.id.forgetPass);
        createnew= (Button) findViewById(R.id.createNew);
        error= (TextView) findViewById(R.id.error);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_inp = username.getText().toString();
                passward_inp = passward.getText().toString();
                ParseQuery<ParseObject> query = ParseQuery.getQuery("NormalUser");
                query.whereEqualTo("mail", email_inp);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> userlist, ParseException e) {
                        if (e != null) {
                            String ee = ("Login Fail Please Verify Your Email first");
                            error.append(ee);
                        }else if (userlist.size()==0){
                            error.append("This mail is not registered");}
                        else {
                            for (ParseObject x : userlist) {
                                pp = x.getString("password");
                                currentUserName = x.getString("userName");
                                currentUserID = x.getObjectId();
                                if (pp.equals(passward_inp)){
                                    currentUserData = new UserData(currentUserName, email_inp, currentUserID);
                                    Intent intent = new Intent(SignIn.this, HomePage.class);
                                    intent.putExtra("mailinput",email_inp);
                                    startActivity(intent);
                                }else{
                                    String ee = ("Wrong Passward");
                                    error.append(ee);
                                }
                            }

                        }
                    }
                });


            }
        });

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
