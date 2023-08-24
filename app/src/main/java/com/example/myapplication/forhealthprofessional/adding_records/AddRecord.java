package com.example.myapplication.forhealthprofessional.adding_records;

import static java.util.UUID.randomUUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.forhealthprofessional.PatientRecords;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class AddRecord extends AppCompatActivity {

    EditText pName, pAge, pGender, pAddress, pDateBirth;
    Button btnadd;
    FirebaseFirestore db;
    String uId, uName, uAge, uGender, uAddress, uDateOfBirth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        pName = findViewById(R.id.name);
        pAge = findViewById(R.id.age);
        pGender = findViewById(R.id.gender);
        pAddress = findViewById(R.id.address);
        pDateBirth = findViewById(R.id.dateofbirth);
        btnadd = findViewById(R.id.add);

        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            btnadd.setText("Update");
            uId = bundle.getString("uId");
            uName = bundle.getString("uName");
            uAge = bundle.getString("uAge");
            uGender = bundle.getString("uGender");
            uAddress = bundle.getString("uAddress");
            uDateOfBirth = bundle.getString("uDateOfBirth");
            pName.setText(uName);
            pAge.setText(uAge);
            pGender.setText(uGender);
            pAddress.setText(uAddress);
            pDateBirth.setText(uDateOfBirth);
        }
        else{
            btnadd.setText("Add");
        }
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = pName.getText().toString();
                String age = pAge.getText().toString();
                String gender = pGender.getText().toString();
                String address = pAddress.getText().toString();
                String dateOfBirth = pDateBirth.getText().toString();

                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null){
                    String id = uId;
                    updateToFirestore(id, name, age, gender, address, dateOfBirth);
                }
                else{
                    String id = randomUUID().toString();

                    saveToFirestore(id, name, age, gender, address, dateOfBirth);
                }
            }
        });
    }
    private void updateToFirestore(String id, String name, String age, String gender, String address, String dateOfBirth){
        db.collection("Patients").document(id).update("name", name, "age", age, "gender", gender, "address", address, "dateOfBirth", dateOfBirth)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddRecord.this, "Updated!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddRecord.this,"Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddRecord.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void saveToFirestore(String id, String name, String age, String gender, String address, String dateOfBirth){
        if(!name.isEmpty() && !age.isEmpty() && !gender.isEmpty() && !address.isEmpty() && !dateOfBirth.isEmpty()){
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("age", age);
            map.put("gender", gender);
            map.put("address", address);
            map.put("dateOfBirth", dateOfBirth);

            db.collection("Patients").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddRecord.this, "Added!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRecord.this,"Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        else{
            Toast.makeText(this, "Invalid.",Toast.LENGTH_SHORT).show();
        }
    }
}