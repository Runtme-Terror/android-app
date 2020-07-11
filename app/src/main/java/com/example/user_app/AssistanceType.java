package com.example.user_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AssistanceType extends AppCompatActivity {

    private String type;
    private static String assistanceType;

    public static String getAssistanceType() {
        return assistanceType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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