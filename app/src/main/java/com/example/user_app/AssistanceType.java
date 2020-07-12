package com.example.user_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AssistanceType extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private String type;
    private static String assistanceType;

    public static String getAssistanceType() {
        return assistanceType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.homeBackground));

        setContentView(R.layout.activity_assistance_type);

        final Spinner spinnerType = findViewById(R.id.spinnerType);

        List<String> types = new ArrayList<>();
        types.add("Psychiatrist");
        types.add("Physician");
        types.add("Pediatrician");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, types);
        spinnerType.setAdapter(typeAdapter);

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = spinnerType.getSelectedItem().toString();
                assistanceType = type;

                Intent intent = new Intent(AssistanceType.this, CreateAppointment.class);
                startActivity(intent);
            }
        });
    }
}