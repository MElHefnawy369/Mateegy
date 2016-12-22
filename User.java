package com.example.mohamedelhefnawy.socialnetwork_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 12/22/2016.
 */

public class User extends AppCompatActivity {

    private String chosenFriendID;
    private Button addFriend;
    private Button deleteFriend;
    private TextView friendName;
    private TextView friendMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        chosenFriendID = bundle.getString("chosenFriendID");
        setContentView(R.layout.user);

        addFriend = (Button) findViewById(R.id.addFriend);
        deleteFriend = (Button) findViewById(R.id.deleteFriend);
        friendName = (TextView) findViewById(R.id.userName);
        friendMail = (TextView) findViewById(R.id.friendmail);
        friendName.setText("");
        friendMail.setText("");

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("NormalUser");
        query2.getInBackground(chosenFriendID, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject user, ParseException e) {
                if (e!=null){
                    String error = "Database query failure";
                    friendName.setText(error+" please try again later");
                }else{
                    friendName.setText(user.getString("userName"));
                    friendMail.setText(user.getString("mail"));
                }
            }
        });

        addFriend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> firstUser = ParseQuery.getQuery("FriendShip");
                firstUser.whereEqualTo("FirstUser",SignIn.currentUserData.getID());
                firstUser.whereEqualTo("SecondUser",chosenFriendID);

                ParseQuery<ParseObject> secondUser = ParseQuery.getQuery("FriendShip");
                secondUser.whereEqualTo("FirstUser",chosenFriendID);
                secondUser.whereEqualTo("SecondUser",SignIn.currentUserData.getID());

                List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
                queries.add(firstUser);
                queries.add(secondUser);
                ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);

                mainQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject friendShip, ParseException e) {
                        if (friendShip != null) {
                            Toast.makeText(User.this, "you are already friends", Toast.LENGTH_LONG).show();
                        } else {


                            ParseObject newFriendship = new ParseObject("FriendShip");
                            newFriendship.put("FirstUser", SignIn.currentUserData.getID());
                            newFriendship.put("SecondUser", chosenFriendID);
                            newFriendship.saveInBackground();
                            Toast.makeText(User.this, "Friend added successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        deleteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> firstUser = ParseQuery.getQuery("FriendShip");
                firstUser.whereEqualTo("FirstUser",SignIn.currentUserData.getID());
                firstUser.whereEqualTo("SecondUser",chosenFriendID);

                ParseQuery<ParseObject> secondUser = ParseQuery.getQuery("FriendShip");
                secondUser.whereEqualTo("FirstUser",chosenFriendID);
                secondUser.whereEqualTo("SecondUser",SignIn.currentUserData.getID());

                List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
                queries.add(firstUser);
                queries.add(secondUser);
                ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);

                mainQuery.getFirstInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject friendShip, ParseException e) {
                        if (friendShip!=null) {
                            friendShip.deleteInBackground(new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {
                                    Toast.makeText(User.this, "Friend deleted successfully", Toast.LENGTH_LONG).show();
                                }
                            });
                        }else {
                            Toast.makeText(User.this, "you are not friends", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
