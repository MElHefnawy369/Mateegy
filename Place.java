package com.example.mohamedelhefnawy.socialnetwork_2;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Place extends AppCompatActivity {

    private String chosenPlaceID;
    private TextView placeNameView;
    private TextView placeDescriptionView;
    private float placeRating;
    private float userRating;
    private int numberOfUsers;
    private float newPlaceRating;
    private String placeName;
    private String placeDescription;
    private RatingBar ratingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        chosenPlaceID = bundle.getString("chosenPlaceID");
        setContentView(R.layout.place);

        placeNameView = (TextView) findViewById(R.id.placeName);
        placeDescriptionView = (TextView) findViewById(R.id.placeDescription);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        placeNameView.setText("");
        placeDescriptionView.setText("");
        ratingBar.setMax(5);

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Places");

        query1.getInBackground(chosenPlaceID, new GetCallback<ParseObject>() {
            public void done(final ParseObject placeRow, ParseException e) {
                if (e!=null){
                    String error = "query failure";
                    Toast.makeText(Place.this, error, Toast.LENGTH_LONG).show();
                }else{
                    placeName = placeRow.getString("Name");
                    placeDescription = placeRow.getString("Description");
                    numberOfUsers = placeRow.getInt("NumberOfUsers");
                    placeRating = (float) placeRow.getDouble("Rating");
                    placeNameView.setText(placeName);
                    placeDescriptionView.setText(placeDescription);
                    ratingBar.setRating(placeRating);

                    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                            Toast.makeText(Place.this, String.valueOf(rating), Toast.LENGTH_LONG).show();
                            userRating = rating;
                            newPlaceRating = ((placeRating*numberOfUsers) + userRating)/(numberOfUsers+1);
                            placeRow.put("Rating", newPlaceRating);
                            placeRow.put("NumberOfUsers", numberOfUsers + 1);
                            placeRow.saveInBackground();
                        }
                    });

                }
              }

            });
    }
}
