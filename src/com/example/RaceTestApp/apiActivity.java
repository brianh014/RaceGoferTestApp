package com.example.RaceTestApp;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Brian on 10/14/2014.
 */
public class apiActivity extends Activity implements LocationListener{
    private LocationManager locationManager;
    private String provider;
    private TextView latField;
    private TextView lonField;
    private EditText username;
    private TextView responseField;
    private double latitude;
    private double longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);
        latField = (TextView) findViewById(R.id.latTextView);
        lonField = (TextView) findViewById(R.id.lonTextView);
        username = (EditText) findViewById(R.id.usernameField);
        responseField = (TextView) findViewById(R.id.responseField);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latField.setText("Location not available");
            lonField.setText("Location not available");
        }
    }

    public void sendCords(View view) {
        if(!username.getText().toString().equals("")) {
            String response = "Hey " + username.getText().toString() + ", the API call is not implemented yet!";
            responseField.setText(response);
        }
        else {
            responseField.setText("Put your name in!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        latField.setText(String.valueOf(latitude));
        lonField.setText(String.valueOf(longitude));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}