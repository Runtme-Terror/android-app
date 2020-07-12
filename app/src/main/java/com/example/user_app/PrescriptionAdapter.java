package com.example.user_app;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrescriptionAdapter {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDoctorName;
        public TextView tvTime;
        public TextView tvDate;
        public Button btnEdit;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            btnEdit = itemView.findViewById(R.id.btnEditAppointment);
        }
    }
}
