package com.example.user_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowPrescription extends AppCompatActivity {

    private Patient patient;
    private Doctor doctor;
    private ArrayList<Medicine> medicines;
    private String dateOfIssue;
    private Prescription prescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_prescription);

        getPrescriptionDetails();

        setTextViewValues();

        initRecyclerView();
    }

    private void getPrescriptionDetails() {
        patient = new Patient("John Doe", "johndoe@gmail.com", 22, 192.2, 195.25, "Male");
        doctor = new Doctor("Dr. Murli Prasad Sharma", "5 years", 3.5, "123456789");

        medicines = new ArrayList<>();
        medicines.add(new Medicine("med 1", "x mg", "2 weeks", "3 times a day"));
        medicines.add(new Medicine("med 2", "x mg", "2 weeks", "3 times a day"));
        medicines.add(new Medicine("med 3", "x mg", "2 weeks", "3 times a day"));
        medicines.add(new Medicine("med 4", "x mg", "2 weeks", "3 times a day"));

        dateOfIssue = "12/07/2020";

        prescription = new Prescription(patient, doctor, medicines, dateOfIssue);
    }

    private void setTextViewValues() {

        TextView tvDocInfo = findViewById(R.id.tvDocInfo);
        TextView tvDocRegNo = findViewById(R.id.tvDocRegNo);
        TextView tvPatientName = findViewById(R.id.tvPatientName);
        TextView tvPatientAge = findViewById(R.id.tvPatientAge);
        TextView tvPatientSex = findViewById(R.id.tvPatientSex);
        TextView tvDateOfIssue = findViewById(R.id.tvDateOfIssue);

        String docInfo = prescription.getDoctor().getName();

        tvDocInfo.setText(docInfo);
        tvDocRegNo.setText(prescription.getDoctor().getRegistrationID());
        tvPatientName.setText(prescription.getPatient().getName());
        tvPatientAge.setText(String.valueOf(prescription.getPatient().getAge()));
        tvPatientSex.setText(prescription.getPatient().getSex());
        tvDateOfIssue.setText(prescription.getDateOfIssue());
    }

    private void initRecyclerView() {

        RecyclerView rvMedicines = findViewById(R.id.rvMedicines);

        MedicineAdapter medicineAdapter = new MedicineAdapter(ShowPrescription.this, medicines);
        rvMedicines.setAdapter(medicineAdapter);
        rvMedicines.setLayoutManager(new LinearLayoutManager(this));
    }
}