package com.example.user_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateAppointment extends AppCompatActivity {

    private int stage = 0;
    private static Doctor selectedDoctor;
    private Doctor doctorSelected;
    private String assistanceType = AssistanceType.getAssistanceType();

    public Doctor getDoctorSelected() {
        return doctorSelected;
    }

    public static void setSelectedDoctor(Doctor selectedDoctor) {
        CreateAppointment.selectedDoctor = selectedDoctor;
    }

    private ArrayList<Doctor> doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getDoctors();

        selectDoctor();
    }

    private void execute() {
        switch (stage) {
            case 0:
                break;
            case 1:
                selectDoctor();
                break;
            case 2:
                setTime();
                break;
        }
    }

    private void selectDoctor() {
        setContentView(R.layout.activity_create_appointment);

        RecyclerView rvDoctors = findViewById(R.id.rvDoctors);

        final SelectDoctorAdapter selectDoctorAdapter = new SelectDoctorAdapter( CreateAppointment.this,doctors);
        rvDoctors.setAdapter(selectDoctorAdapter);
        rvDoctors.setLayoutManager(new LinearLayoutManager(CreateAppointment.this));

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stage++;

                if(selectedDoctor == null) {
                    Toast.makeText(CreateAppointment.this, "Please select a doctor!", Toast.LENGTH_SHORT).show();
                    return;
                }

                doctorSelected = selectedDoctor;

                setTime();
            }
        });
    }

    private void getDoctors() {
        doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Murli Prasad Sharma", "10 years", 5.0, "circuit"));
        doctors.add(new Doctor("Dr. J.C. Asthana", "25 years", 2.5, "Hahahahahaha"));
        doctors.add(new Doctor("Dr. Rustam Pavri", "5 years", 4.5, "Rani toh pappani"));
        doctors.add(new Doctor("Dr. Suman Asthana", "5 years", 4.0, "chinki"));
        doctors.add(new Doctor("Dr. Octopus", "20 years", 1.0, "2manyhands"));
        doctors.add(new Doctor("Dr. Doom", "15 years", 2.0, "doom"));
        doctors.add(new Doctor("Dr. Ross Geller", "10 years", 3.0, "Paleontologist"));
        doctors.add(new Doctor("Dr. Murli Prasad Sharma", "10 years", 5.0, "circuit"));
        doctors.add(new Doctor("Dr. Murli Prasad Sharma", "10 years", 5.0, "circuit"));
        doctors.add(new Doctor("Dr. Murli Prasad Sharma", "10 years", 5.0, "circuit"));
    }

    private void setTime() {
        setContentView(R.layout.set_appointment_time);

        final Spinner spinnerSlots = findViewById(R.id.spinnerSlots);

//        Adding data to spinnerSex
        List<String> slots = new ArrayList<>();
        slots.add("Select Slot");
        slots.add("Morning");
        slots.add("Noon");
        slots.add("Afternoon");
        slots.add("Evening");

//        Setting Adapter to spinnerSex
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, slots);
        spinnerSlots.setAdapter(stateAdapter);

        Button btnSchedule = findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerSlots.getSelectedItem().toString().equals("Select Slot")) {
                    Toast.makeText(CreateAppointment.this, "Please select a valid slot!", Toast.LENGTH_SHORT).show();
                    return;
                }
                schedule();
            }
        });
    }

    private void schedule() {
        Toast.makeText(CreateAppointment.this, "Appointment scheduled", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CreateAppointment.this, HomePage.class);
        startActivity(intent);
        finish();
    }

}