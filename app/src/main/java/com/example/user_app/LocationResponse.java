package com.example.user_app;

public class LocationResponse {

    public String _id;
    public String name;
    public double latitude;
    public double longitude;

    public LocationResponse(String _id, String name, double latitude, double longitude) {
        this._id = _id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
