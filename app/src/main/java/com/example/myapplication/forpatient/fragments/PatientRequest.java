package com.example.myapplication.forpatient.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class PatientRequest extends Fragment {

    EditText pname, ppurpose;
    Button btndate, btntime, btnrequest;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patient_request, container, false);
        pname = v.findViewById(R.id.name);
        ppurpose = v.findViewById(R.id.purpose);
        btndate = v.findViewById(R.id.date);
        btntime = v.findViewById(R.id.time);
        btnrequest = v.findViewById(R.id.request);
        return v;


    }
}