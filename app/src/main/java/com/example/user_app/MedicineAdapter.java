package com.example.user_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    private Context context;
    private List<Medicine> medicines;

    public MedicineAdapter(Context context, List<Medicine> medicines) {
        this.context = context;
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_card, parent, false);
        return new MedicineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medicine currentMedicine = medicines.get(position);

        holder.tvMedicineName.setText(currentMedicine.getName());
        holder.tvStrength.setText(currentMedicine.getStrength());
        holder.tvDuration.setText(currentMedicine.getDuration());
        holder.tvDosage.setText(currentMedicine.getDosage());
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvMedicineName;
        public TextView tvStrength;
        public TextView tvDuration;
        public TextView tvDosage;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvMedicineName = itemView.findViewById(R.id.tvMedicineName);
            tvStrength = itemView.findViewById(R.id.tvStrength);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvDosage = itemView.findViewById(R.id.tvDosage);
        }
    }
}
