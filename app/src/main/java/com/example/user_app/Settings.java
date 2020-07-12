package com.example.user_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    Switch switchLocation;
    Switch switchCamera;
    Switch switchMic;
    Switch switchInternet;
    Switch switchRead;
    Switch switchWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.homeBackground));

        setContentView(R.layout.activity_settings);

        switchLocation = findViewById(R.id.switchLocation);
        switchCamera = findViewById(R.id.switchCamera);
        switchMic = findViewById(R.id.switchMic);
        switchRead = findViewById(R.id.switchRead);
        switchWrite = findViewById(R.id.switchWrite);
        switchInternet = findViewById(R.id.switchInternet);

        setCheckBoxes();

        changeAccess();
    }

    private void setCheckBoxes() {

        if (ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            switchLocation.setChecked(false);
        } else {
            switchLocation.setChecked(true);
        }

        if(ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            switchCamera.setChecked(false);
        } else {
            switchCamera.setChecked(true);
        }

        if(ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            switchMic.setChecked(false);
        } else {
            switchMic.setChecked(true);
        }

        if(ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            switchInternet.setChecked(false);
        } else {
            switchInternet.setChecked(true);
        }

        if(ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            switchRead.setChecked(false);
        } else {
            switchRead.setChecked(true);
        }

        if(ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            switchWrite.setChecked(false);
        } else {
            switchWrite.setChecked(true);
        }
    }

    private void changeAccess() {

        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                    buttonView.setChecked(true);
                }
            }
        });

        switchCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                    buttonView.setChecked(true);
                }
            }
        });

        switchInternet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE);
                    buttonView.setChecked(true);
                }
            }
        });

        switchRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
                    buttonView.setChecked(true);
                }
            }
        });

        switchWrite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                    buttonView.setChecked(true);
                }
            }
        });

        switchMic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
                    buttonView.setChecked(true);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}