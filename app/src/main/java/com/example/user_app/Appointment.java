package com.example.user_app;

import java.util.Calendar;

public class Appointment {

    private String patientName;
    private String doctorName;
    private String time;
    private String date;

    public Appointment(String patientName, String doctorName, String time, String date) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.time = time;
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
