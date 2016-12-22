package com.example.mohamedelhefnawy.socialnetwork_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static com.example.mohamedelhefnawy.socialnetwork_2.R.id.searchButton;
import static com.example.mohamedelhefnawy.socialnetwork_2.R.id.searchResult;

/**
 * Created by MohamedElHefnawy on 12/18/2016.
 */

public class CheckIn extends AppCompatActivity {

    private TextView locationCoordinates;
    private Button getRecommendations;
    private Button checkin;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Button getcoord;
    private double longitude;
    private double latitude;
    private TextView testingWindow;
    private String[] chosenPlaceID;
    private String chosenPlace;
    private SpannableString[] searchedPlace;
    private String mailinput;
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mailinput = getIntent().getStringExtra("mailinput2");
        setContentView(R.layout.checkin);

        locationCoordinates = (TextView) findViewById(R.id.locationCoordinates);
        getRecommendations = (Button) findViewById(R.id.getRecommendations);
        checkin = (Button) findViewById(R.id.checkin1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getcoord = (Button) findViewById(R.id.getcoord);
        testingWindow = (TextView) findViewById(R.id.test3);
        locationCoordinates.setMovementMethod(new ScrollingMovementMethod());
        testingWindow.setText("");

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                locationCoordinates.setText("Your Location now is \n" + latitude + " " + longitude + "\n");
                getRecommendations.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Places");
                        query.whereLessThanOrEqualTo("latitude", latitude + 0.1);
                        query.whereGreaterThanOrEqualTo("latitude", latitude - 0.1);
                        query.whereLessThanOrEqualTo("longitude", longitude + 0.3);
                        query.whereGreaterThanOrEqualTo("longitude", longitude - 0.3);

                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> placeList, ParseException e) {
                                if (e != null) {
                                    String error = "query failure";
                                    Toast.makeText(CheckIn.this, error, Toast.LENGTH_LONG).show();
                                } else {
                                    ClickableSpan[] clickablePlace = new ClickableSpan[placeList.size()];
                                    searchedPlace = new SpannableString[placeList.size()];
                                    chosenPlaceID = new String[placeList.size()];
                                    i = 0;
                                    if (placeList.size() == 0) {
                                        testingWindow.setText("No place found\nPlease refine your search");
                                    } else {

                                        for (ParseObject placeRow : placeList) {
                                            chosenPlace = placeRow.getString("Name");
                                            chosenPlaceID[i] = placeRow.getObjectId();
                                            searchedPlace[i] = new SpannableString(placeRow.getString("Name"));
                                            final int index = i;
                                            clickablePlace[index] = new ClickableSpan() {
                                                @Override
                                                public void onClick(View view) {
                                                    ParseObject newUser2 = new ParseObject("Checkin");
                                                    newUser2.put("mail", mailinput);
                                                    newUser2.put("placeName", "" + searchedPlace[index]);
                                                    newUser2.saveInBackground();
                                                    Toast.makeText(CheckIn.this,"place chosen successfully" , Toast.LENGTH_LONG).show();

                                                }


                                                @Override
                                                public void updateDrawState(TextPaint ds) {
                                                    super.updateDrawState(ds);
                                                    ds.setUnderlineText(true);
                                                    ds.setColor(Color.BLUE);
                                                }
                                            };
                                            searchedPlace[i].setSpan(clickablePlace[i], 0, chosenPlace.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                                            testingWindow.append(searchedPlace[i]);
                                            testingWindow.append("\n");
                                            testingWindow.setMovementMethod(LinkMovementMethod.getInstance());
                                            i++;
                                            testingWindow.setHighlightColor(Color.TRANSPARENT);
                                        }
                                    }
                                }
                            }

                        });
                    }
                });

                checkin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(CheckIn.this, HomePage.class));
                    }
                });
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
        } else {
            getcoord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, locationListener);
                }
            });

        }
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckIn.this, HomePage.class));
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        getcoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 5, locationListener);

        }
    });

    }
}
