package com.example.myapplication.forhealthprofessional;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapters.PRAdapter;
import com.example.myapplication.forhealthprofessional.adding_records.AddRecord;
import com.example.myapplication.models.PRModel;
import com.example.myapplication.models.TouchHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class
PatientRecords extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    PRAdapter adapter;
    List<PRModel> prList;
    public FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_records);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientRecords.this, AddRecord.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        prList = new ArrayList<>();
        adapter = new PRAdapter(this, prList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new TouchHelper(adapter));
        touchHelper.attachToRecyclerView(recyclerView);

        showData();
    }

    private void showData(){
        db.collection("Patients").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        prList.clear();
                        for (DocumentSnapshot snapshot: task.getResult()){
                            PRModel prModel = new PRModel(snapshot.getString("id"), snapshot.getString("name"), snapshot.getString("age"), snapshot.getString("gender"), snapshot.getString("address"), snapshot.getString("dateOfBirth"));
                            prList.add(prModel);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PatientRecords.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}