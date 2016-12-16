package com.example.mohamedelhefnawy.socialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by MohamedElHefnawy on 12/16/2016.
 */

public class HomePage extends AppCompatActivity {

    private Button logoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Intent i = getIntent();
        NormalUser activeUser = (NormalUser) i.getSerializableExtra("sample Object");

        logoutbutton = (Button) findViewById(R.id.logout);
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, SignIn.class));
            }
        });
    }
}
