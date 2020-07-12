package com.example.user_app.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user_app.Appointment;
import com.example.user_app.HomePage;
import com.example.user_app.HomePageAppointmentsAdapter;
import com.example.user_app.LiveAppointmentAdapter;
import com.example.user_app.LocationAPI;
import com.example.user_app.LocationResponse;
import com.example.user_app.R;
import com.example.user_app.Registration;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private static final int REQUEST_CODE = 1;
    private HomeViewModel homeViewModel;
    private MapView mapView;
    private RecyclerView rvApp;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    public List<Appointment> appointments;
    private static final String TAG = "HomeFragment";
    private Location location;
    public ArrayList<LocationResponse> locationResponses;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        location = getLocation();

        mapView = root.findViewById(R.id.mvDiseaseMap);
        rvApp = root.findViewById(R.id.rvUpcomingApp);

        initMapView(savedInstanceState);

        initRecyclerView();

        return root;
    }

    private Location getLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    private void initRecyclerView() {
        appointments = new ArrayList<>();
        appointments.add(new Appointment("ME", "Dr. Murli Prasad Sharma", "16:00", "13/07/2020"));

        LiveAppointmentAdapter liveAppointmentAdapter = new LiveAppointmentAdapter(getActivity(), appointments);
        rvApp.setAdapter(liveAppointmentAdapter);
        rvApp.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initMapView(Bundle savedInstanceState) {

        Bundle mapViewBundle = null;

        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }

        mapView.onResume();
        map.setMyLocationEnabled(true);

        getPeople(map);
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.d(TAG, "onRequestPermissionsResult: " + location.toString());
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getActivity(), "Please Enable GPS!!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void getPeople( GoogleMap map) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://runtime--terror.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationAPI locationAPI = retrofit.create(LocationAPI.class);

        Call<ArrayList<LocationResponse>> call = locationAPI.getLocations();

        call.enqueue(new Callback<ArrayList<LocationResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<LocationResponse>> call, Response<ArrayList<LocationResponse>> response) {
                locationResponses = response.body();

                for(int i = 0; i< locationResponses.size(); i++) {
                    String name = locationResponses.get(i).getName();
                    double lat = locationResponses.get(i).getLatitude();
                    double lng = locationResponses.get(i).getLongitude();
                    LatLng location = new LatLng(lat, lng);
                    map.addMarker(new MarkerOptions()
                            .position(location)
                            .title(name));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<LocationResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: Retrofit Failure");
                t.printStackTrace();
            }
        });
    }
}