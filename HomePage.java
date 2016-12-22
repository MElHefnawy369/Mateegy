package com.example.mohamedelhefnawy.socialnetwork_2;

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
    private Button checkinrequest;
    private Button searchButton;
    private Button searchFriend;
    private String mailinput2;
    private int flag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        mailinput2= getIntent().getStringExtra("mailinput");
        logoutbutton = (Button) findViewById(R.id.logout);
        checkinrequest = (Button) findViewById(R.id.checkinrequest);
        searchButton = (Button) findViewById(R.id.toSearch);
        searchFriend = (Button) findViewById(R.id.tosearchfriend);

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag=1;
                Intent intent = new Intent(HomePage.this, SignIn.class);
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });
        checkinrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this, CheckIn.class);
                intent.putExtra("mailinput2",mailinput2);
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, SearchForPlace.class));
            }
        });

        searchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, SearchForFriend.class));
            }
        });
    }
}
