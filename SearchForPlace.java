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
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SearchForPlace extends AppCompatActivity {

    private EditText userSearch;
    private TextView searchResult;
    private Button searchButton;
    private SpannableString[] searchedPlace;
    private Double searchedPlaceRating;
    private String chosenPlace;
    private String[] chosenPlaceID;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_for_place);
        userSearch = (EditText) findViewById(R.id.searchPlace);
        searchResult = (TextView) findViewById(R.id.searchResult);
        searchButton = (Button) findViewById(R.id.searchButton);
        userSearch.setText("");
        searchResult.setText("");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResult.setText("");
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Places");
                query.whereEqualTo("Name", userSearch.getText().toString());
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> placeList, ParseException e) {
                        if (e != null) {
                            String error = "query failure";
                            Toast.makeText(SearchForPlace.this, error, Toast.LENGTH_LONG).show();
                        } else {
                            ClickableSpan[] clickablePlace = new ClickableSpan[placeList.size()];
                            searchedPlace = new SpannableString[placeList.size()];
                            chosenPlaceID = new String[placeList.size()];
                            i = 0;
                            if (placeList.size() == 0) {
                                searchResult.setText("No place found\nPlease refine your search");
                            } else {
                                for (ParseObject placeRow : placeList) {
                                    searchedPlaceRating = placeRow.getDouble("Rating");
                                    chosenPlace = placeRow.getString("Name");
                                    chosenPlaceID[i] = placeRow.getObjectId();
                                    searchedPlace[i] = new SpannableString(placeRow.getString("Name"));
                                    final int index = i;
                                    clickablePlace[index] = new ClickableSpan() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent = new Intent(SearchForPlace.this, Place.class);
                                            intent.putExtra("chosenPlaceID", chosenPlaceID[index]);
                                            startActivity(intent);

                                        }

                                        @Override
                                        public void updateDrawState(TextPaint ds) {
                                            super.updateDrawState(ds);
                                            ds.setUnderlineText(true);
                                            ds.setColor(Color.BLUE);
                                        }
                                    };
                                    searchedPlace[i].setSpan(clickablePlace[i], 0, chosenPlace.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                    searchResult.append(searchedPlace[i]);
                                    searchResult.append("\n");
                                    searchResult.append("      Rating = " + searchedPlaceRating + "\n\n");
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

