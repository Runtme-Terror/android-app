package com.example.user_app.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user_app.Appointment;
import com.example.user_app.AppointmentAdapter;
import com.example.user_app.HomePageAppointmentsAdapter;
import com.example.user_app.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView rvAllApp;
    private RecyclerView rvOldApp;
    public static ArrayList<Appointment> appointments = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        rvAllApp = root.findViewById(R.id.rvUpcomingApp);
        rvOldApp = root.findViewById(R.id.rvOldApp);

        initRecyclerViews();

        return root;
    }

    private void initRecyclerViews() {

        HomePageAppointmentsAdapter homePageAppointmentsAdapter = new HomePageAppointmentsAdapter(getActivity(), appointments);
        rvAllApp.setAdapter(homePageAppointmentsAdapter);
        rvAllApp.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Appointment> oldAppointments = new ArrayList<>();
        oldAppointments.add(new Appointment("ME", "Doctor Octopus", "16:30", "12/06/2020"));

        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(getActivity(), oldAppointments);
        rvOldApp.setAdapter(appointmentAdapter);
        rvOldApp.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}