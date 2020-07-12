package com.example.user_app;

import android.location.Location;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationAPI {

    @GET("api")
    Call<ArrayList<LocationResponse>> getLocations();
}
