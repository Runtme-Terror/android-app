//Shown after user presses tvRegLink on Login Activity

package com.example.user_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private ArrayList<String> sex;
    private CheckBox cbLocation;
    private String name;
    private String email;
    private String password;
    private int age;
    private String patientSex;
    private Location patientLocation;
    private double latitude;
    private double longitude;
    private static final String TAG = "Registration";

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(Registration.this, "Please Enable GPS!!!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

//        Initialize variables
        TextView tvLoginLink = findViewById(R.id.tvLoginLink);
        final EditText etName = findViewById(R.id.etName);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPass = findViewById(R.id.etPass);
        final EditText etConfirmPass = findViewById(R.id.etConfirmPass);
        final EditText etAge = findViewById(R.id.etAge);
        cbLocation = findViewById(R.id.cbLocation);
        Button btnRegister = findViewById(R.id.btnRegister);
        final Spinner spinnerSex = findViewById(R.id.spinnerSex);

//        If tvLoginLink is pressed go to Login Activity
        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

//        Adding data to spinnerSex
        sex = new ArrayList<>();
        sex.add("Sex");
        sex.add("Male");
        sex.add("Female");
        sex.add("Other");

//        Setting Adapter to spinnerSex
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, sex);
        spinnerSex.setAdapter(stateAdapter);

//        When user presses register make a post request to backend API
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!etPass.getText().toString().equals(etConfirmPass.getText().toString())) {
                    Snackbar.make(etPass, "Password and Confirm Password must have same value", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                patientLocation = getLocation();
                if(patientLocation == null){
                    Toast.makeText(Registration.this, "Location null", Toast.LENGTH_SHORT).show();
                    return;
                }

                name = etName.getText().toString();
                email = etEmail.getText().toString();
                password = etPass.getText().toString();
                age = Integer.parseInt(etAge.getText().toString());
                patientSex = spinnerSex.getSelectedItem().toString();
                latitude = patientLocation.getLatitude();
                longitude = patientLocation.getLongitude();

//                If user is registered
                if (registerUser()) {
                    Toast.makeText(Registration.this, "Congrats! you're now registered. Please Login to continue.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Registration.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean registerUser() {
        return true;
    }

    private Location getLocation() {
        if(!cbLocation.isChecked()) {
            Toast.makeText(Registration.this, "Please provide location access to continue!!", Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(Registration.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Registration.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // here to request the missing permissions, and then overriding
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
}