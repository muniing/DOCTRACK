package com.example.myapplication.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.forhealthprofessional.PatientRecords;
import com.example.myapplication.forhealthprofessional.adding_records.AddRecord;
import com.example.myapplication.models.PRModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.List;

public class PRAdapter extends RecyclerView.Adapter<PRAdapter.PRViewHolder> {
    PatientRecords activity;
    List<PRModel> prList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    public void deleteData(int position){
        PRModel item = prList.get(position);
        db.collection("Patients").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyRemoved(position);
                            Toast.makeText(activity, "Data Deleted", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(activity, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyRemoved(int position){
        prList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
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
