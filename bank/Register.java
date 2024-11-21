package com.example.bank;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText e1,e2,e3,e4;
        e1 = findViewById(R.id.username);
        e2 = findViewById(R.id.password);
        e3 = findViewById(R.id.rpassword);
        e4 = findViewById(R.id.balance);

        RadioGroup acctype = findViewById(R.id.acctype);
        Button register = findViewById(R.id.reg);

        SharedPreferences preferences = getSharedPreferences("users",0);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = e1.getText().toString().trim();
                String password = e2.getText().toString();
                String rpassword = e3.getText().toString();
                String balanceStr = e4.getText().toString().trim();

                int selectedAccTypeId = acctype.getCheckedRadioButtonId();
                RadioButton selectedAccType = findViewById(selectedAccTypeId);
                String accType = selectedAccType.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(Register.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.length() < 8) {
                    Toast.makeText(Register.this, "Username must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty() || rpassword.isEmpty()) {
                    Toast.makeText(Register.this, "Password fields cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(rpassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.matches(".*[^a-zA-Z0-9].*") || password.contains(" ")) {
                    Toast.makeText(Register.this, "Password cannot contain special characters or spaces", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (balanceStr.isEmpty() || Double.parseDouble(balanceStr) <= 0) {
                    Toast.makeText(Register.this, "Balance must be a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                double balance = Double.parseDouble(balanceStr);

                // Save user data into SharedPreferences
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", username);
                editor.putString("password", password);
                editor.putString("acctype", accType);
                editor.putFloat("balance", (float) balance);
                editor.apply();

                Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}