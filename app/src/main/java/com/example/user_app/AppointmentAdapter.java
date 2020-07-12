package com.example.user_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter  extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> appointments;
    private Appointment appointmentToShow;

    public Appointment getAppointmentToShow() {
        return appointmentToShow;
    }

    public AppointmentAdapter(Context context, List<Appointment> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.old_appointment_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);

        String doctorName = appointment.getDoctorName();
        String date = appointment.getDate();
        String time = appointment.getTime();

        holder.tvDoctorName.setText(doctorName);
        holder.tvDate.setText(date);
        holder.tvTime.setText(time);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "show appointment", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ShowPrescription.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

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
