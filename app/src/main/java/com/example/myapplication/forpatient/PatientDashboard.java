package com.example.myapplication.forpatient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Device;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.forhealthprofessional.Appointments;
import com.example.myapplication.forhealthprofessional.Dashboard;
import com.example.myapplication.forhealthprofessional.PatientRecords;
import com.example.myapplication.forhealthprofessional.Report;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PatientDashboard extends AppCompatActivity {

    Button btnsignout;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_dashboard);

        CardView appointment = findViewById(R.id.cardAppointments);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDashboard.this, PatientAppointments.class));
            }
        });

        CardView device = findViewById(R.id.cardDevice);
        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDashboard.this, Device.class));
            }
        });

        CardView medication = findViewById(R.id.cardMedication);
        medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDashboard.this, PatientMedication.class));
            }
        });

        CardView record = findViewById(R.id.cardRecord);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientDashboard.this, PatientRecord.class));
            }
        });

        auth = FirebaseAuth.getInstance();
        btnsignout = findViewById(R.id.signout);
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        btnsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}