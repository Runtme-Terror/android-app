package com.example.user_app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectDoctorAdapter extends RecyclerView.Adapter<SelectDoctorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Doctor> doctors;

    public SelectDoctorAdapter(Context context, ArrayList<Doctor> doctors) {
        this.context = context;
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        return new SelectDoctorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Doctor currentDoctor = doctors.get(position);

        String doctorName = currentDoctor.getName();
        String doctorExp = currentDoctor.getExperience();
        double rating = currentDoctor.getRating();
        String doctorRating = rating + "/5";

        holder.tvDoctorName.setText(doctorName);
        holder.tvDoctorExp.setText(doctorExp);
        holder.tvRating.setText(doctorRating);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.selected) {
                    CreateAppointment.setSelectedDoctor(null);
                    holder.cardView.setCardBackgroundColor(Color.WHITE);
                    holder.tvDoctorName.setTextColor(Color.BLACK);
                    holder.tvDoctorExp.setTextColor(Color.BLACK);
                    holder.tvRating.setTextColor(Color.BLACK);
                    holder.selected = false;
                }
                else {
                    CreateAppointment.setSelectedDoctor(currentDoctor);
                    holder.cardView.setCardBackgroundColor(Color.BLACK);
                    holder.tvDoctorName.setTextColor(Color.WHITE);
                    holder.tvDoctorExp.setTextColor(Color.WHITE);
                    holder.tvRating.setTextColor(Color.WHITE);
                    holder.selected = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvDoctorName;
        public TextView tvDoctorExp;
        public TextView tvRating;
        public CardView cardView;
        public boolean selected = false;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvDoctorExp = itemView.findViewById(R.id.tvDoctorExp);
            tvRating = itemView.findViewById(R.id.tvDoctorRating);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
