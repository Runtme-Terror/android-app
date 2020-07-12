package com.example.user_app;

import java.util.ArrayList;

public class Prescription {

    private Patient patient;
    private Doctor doctor;
    private ArrayList<Medicine> medicines;
    private String dateOfIssue;

    public Prescription(Patient patient, Doctor doctor, ArrayList<Medicine> medicines, String dateOfIssue) {
        this.patient = patient;
        this.doctor = doctor;
        this.medicines = medicines;
        this.dateOfIssue = dateOfIssue;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }
}
