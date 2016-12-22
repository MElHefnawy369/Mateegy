package com.example.mohamedelhefnawy.socialnetwork_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class SearchForFriend extends AppCompatActivity {

    private Button searchButton;
    private EditText searchFriend;
    private TextView searchResult;
    private SpannableString[] searchedFriend;
    private String[] chosenFriendID;
    private String chosenFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_for_friend);

        searchButton = (Button) findViewById(R.id.searchFriendButton);
        searchFriend = (EditText) findViewById(R.id.searchFriend);
        searchResult = (TextView) findViewById(R.id.searchFriendResult);
        searchButton.setText("");
        searchResult.setText("");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResult.setText("");
                ParseQuery<ParseObject> query = ParseQuery.getQuery("NormalUser");
                query.whereEqualTo("userName", searchFriend.getText().toString());
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> userList, ParseException e) {
                        if (e != null) {
                            String error = "query failure";
                            searchResult.setText("can't connect to Database\nplease try again later");
                    }else{  ClickableSpan[] clickableFriend = new ClickableSpan[userList.size()];
                            searchedFriend = new SpannableString[userList.size()];
                            chosenFriendID = new String[userList.size()];
                            int i = 0;
                            if (userList.size() == 0) {
                                searchResult.setText("No users with that name found\nPlease refine your search");
                            } else {
                                for (ParseObject userRow : userList) {
                                    chosenFriend = userRow.getString("userName");
                                    chosenFriendID[i] = userRow.getObjectId();
                                    searchedFriend[i] = new SpannableString(userRow.getString("userName"));
                                    final int index = i;
                                    clickableFriend[index] = new ClickableSpan() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(SearchForFriend.this, User.class);
                                            intent.putExtra("chosenFriendID", chosenFriendID[index]);
                                            startActivity(intent);
                                        }
                                        @Override // this is just to make the text blue
                                        public void updateDrawState(TextPaint ds) {
                                            super.updateDrawState(ds);
                                            ds.setUnderlineText(false);
                                            ds.setColor(Color.BLUE);
                                        }
                                    };
                                    searchedFriend[i].setSpan(clickableFriend[i],0,chosenFriend.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    searchResult.append(searchedFriend[i]);
                                    searchResult.append("\n\n");
                                    searchResult.setMovementMethod(LinkMovementMethod.getInstance());
                                    i++;
                                    searchResult.setHighlightColor(Color.GRAY);
                                }
                            }
                        }
                    }
                });
            }

        });
    }
}

