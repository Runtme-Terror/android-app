package com.example.user_app;

public class Doctor {

    private String name;
    private String experience;
    private double rating;
    private String registrationID;
    private String education;
    private String specialisation;

    public Doctor(String name, String experience, double rating, String registrationID, String education, String specialisation) {
        this.name = name;
        this.experience = experience;
        this.rating = rating;
        this.registrationID = registrationID;
        this.education = education;
        this.specialisation = specialisation;
    }

    public Doctor(String name, String experience, double rating, String registrationID) {
        this.name = name;
        this.experience = experience;
        this.rating = rating;
        this.registrationID = registrationID;
        this.education = "MBBS, MD";
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getEducation() {
        return education;
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
