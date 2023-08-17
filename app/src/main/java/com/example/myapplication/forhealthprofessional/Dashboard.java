package com.example.myapplication.forhealthprofessional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.Device;
import com.example.myapplication.R;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView appointment = findViewById(R.id.cardAppointments);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Appointments.class));
            }
        });

        CardView device = findViewById(R.id.cardDevice);
        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Device.class));
            }
        });

        CardView patientRecords = findViewById(R.id.cardPatientRecords);
        patientRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, PatientRecords.class));
            }
        });

        CardView report = findViewById(R.id.cardReport);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Report.class));
            }
        });
    }
}