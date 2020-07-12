package com.example.user_app;

public class Medicine {

    private String name;
    private String strength;
    private String duration;
    private String dosage;

    public Medicine(String name, String strength, String duration, String dosage) {
        this.name = name;
        this.strength = strength;
        this.duration = duration;
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public String getStrength() {
        return strength;
    }

    public String getDuration() {
        return duration;
    }

    public String getDosage() {
        return dosage;
    }
}
