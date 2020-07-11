//Shown after user presses tvRegLink on Login Activity

package com.example.user_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    private ArrayList<String> states, cities;
    String name;
    String email;
    String Password;
    String DOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        Initialize variables
        TextView tvLoginLink = findViewById(R.id.tvLoginLink);
        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPass = findViewById(R.id.etPass);
        EditText etConfirmPass = findViewById(R.id.etConfirmPass);
        Button btnRegister = findViewById(R.id.btnRegister);

//        If tvLoginLink is pressed go to Login Activity
        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

//        Adding data to spinners

        states = new ArrayList<>();
        states.add("Detect my location");
        states.add("Delhi");
        states.add("Haryana");
        states.add("Rajasthan");
        states.add("West Bengal");

        states = new ArrayList<>();
        states.add("Select a State first");
        states.add("New Delhi");
        states.add("Hisar");
        states.add("Jaipur");
        states.add("Kolkata");

//        Setting Adapter to spinnerState
//        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, states);
//        spinnerState.setAdapter(stateAdapter);
////        Setting adapter to spinnerCity
//        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, states);
//        spinnerState.setAdapter(cityAdapter);
//        spinnerCity.setEnabled(false);
//        spinnerState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
////                Detect location using GPS
//                if(selectedItem.equals("Detect my location")) {
//                    Toast.makeText(Registration.this, "We'll get your location", Toast.LENGTH_SHORT).show();
//                }
////                If state is selected add the state's cities to spinnerCity
//                else {
//                    spinnerCity.setEnabled(true);
//                }
//            }
//        });

//        When user presses register make a post request to backend API
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                If user is registered
                if(registerUser()) {
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
}