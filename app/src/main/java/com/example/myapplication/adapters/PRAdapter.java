package com.example.myapplication.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.forhealthprofessional.PatientRecords;
import com.example.myapplication.forhealthprofessional.adding_records.AddRecord;
import com.example.myapplication.models.PRModel;

import org.w3c.dom.Text;

import java.util.List;

public class PRAdapter extends RecyclerView.Adapter<PRAdapter.PRViewHolder> {
    PatientRecords activity;
    List<PRModel> prList;

    public PRAdapter(PatientRecords activity, List<PRModel> prList){
        this.activity = activity;
        this.prList = prList;
    }

    public void updateData(int position){
        PRModel item = prList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId", item.getId());
        bundle.putString("uName", item.getName());
        bundle.putString("uAge", item.getAge());
        bundle.putString("uAddress", item.getAddress());
        bundle.putString("uGender", item.getGender());
        bundle.putString("uDateOfBirth", item.getDateOfBirth());
        Intent intent = new Intent(activity, AddRecord.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    @NonNull
    @Override
    public PRViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.list_patient, parent, false);
        return new PRViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PRViewHolder holder, int position) {
        holder.name.setText(prList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return prList.size();
    }

    public static class PRViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public PRViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.patientNameName);


        }
    }
}
