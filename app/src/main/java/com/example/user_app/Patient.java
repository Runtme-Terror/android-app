package com.example.user_app;

public class Patient {

    private String name;
    private String email;
    private int age;
    private double longitude;
    private double latitude;
    private String sex;

    public Patient(String name, String email, int age, double longitude, double latitude, String sex) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getSex() {
        return sex;
    }
}
