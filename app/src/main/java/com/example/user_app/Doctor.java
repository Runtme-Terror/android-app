package com.example.user_app;

public class Doctor {

    private String name;
    private String experience;
    private double rating;
    private String registrationID;

    public Doctor(String name, String experience, double rating, String registrationID) {
        this.name = name;
        this.experience = experience;
        this.rating = rating;
        this.registrationID = registrationID;
    }

    public String getName() {
        return name;
    }

    public String getExperience() {
        return experience;
    }

    public double getRating() {
        return rating;
    }

    public String getRegistrationID() {
        return registrationID;
    }
}
