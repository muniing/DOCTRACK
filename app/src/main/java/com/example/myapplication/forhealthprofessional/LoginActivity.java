package com.example.myapplication.forhealthprofessional;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    EditText usernameText, passwordText;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.editTextTextEmailAddress);
        passwordText = findViewById(R.id.editTextTextPassword);
        btnLogin = findViewById(R.id.button);

       /* btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                if(username.length() == 0 || password.length() == 0)
                {
                    Toast.makeText(LoginActivity.this, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}