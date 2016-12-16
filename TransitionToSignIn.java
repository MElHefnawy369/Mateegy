package com.example.mohamedelhefnawy.socialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by MohamedElHefnawy on 12/16/2016.
 */
public class TransitionToSignIn extends AppCompatActivity {

    private TextView succesfulSignUP;
    private Button signUpToSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_page_to_signin);

        signUpToSignInButton = (Button) findViewById(R.id.button_sign_in);
        succesfulSignUP = (TextView) findViewById(R.id.congratsignup);
        signUpToSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TransitionToSignIn.this, SignIn.class));
            }
        });

    }
}
