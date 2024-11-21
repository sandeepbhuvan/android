package com.example.bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.Dashboard;
import com.example.bank.R;
import com.example.bank.Register;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.bank.R.layout.activity_main);

        EditText e1 = findViewById(R.id.e1); // Username input
        EditText e2 = findViewById(R.id.e2); // Password input
        Button login = findViewById(R.id.login); // Login button
        Button register = findViewById(R.id.reg); // Register button

        SharedPreferences preferences = getSharedPreferences("users", 0);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String savedUsername = preferences.getString("username", "");
                String savedPassword = preferences.getString("password", "");

                String enteredUsername = e1.getText().toString().trim();
                String enteredPassword = e2.getText().toString();

                // Check if user is registered
                if (savedUsername.isEmpty() || savedPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Register First!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate input fields
                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Username and Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check credentials
                if (enteredUsername.equals(savedUsername) && enteredPassword.equals(savedPassword)) {
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });
    }
}
