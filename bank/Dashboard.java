package com.example.bank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView t1=findViewById(R.id.t2);
        EditText e1 = findViewById(R.id.e1);
        Button b1 = findViewById(R.id.withdraw);
        Button b2 = findViewById(R.id.deposit);
        SharedPreferences preferences = getSharedPreferences("users",0);
        String username = preferences.getString("username","");
        if(username.isEmpty())
        {
            Intent intent = new Intent(Dashboard.this,MainActivity.class);
            startActivity(intent);

        }
        t1.setText("Welcome "+username+"!" + "\n Balance: "+preferences.getFloat("balance",0)+);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double balance = preferences.getFloat("balance",0);
                double amount = Double.parseDouble(e1.getText().toString());
                if(amount > balance)
                {
                    Toast.makeText(Dashboard.this, "Amount exceeded balance!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    balance = balance - amount;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putFloat("balance", (float) balance);
                    editor.apply();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double balance = preferences.getFloat("balance",0);
                double amount = Double.parseDouble(e1.getText().toString());
                if(amount > 0)
                {
                    Toast.makeText(Dashboard.this, "Amount cannot be negative!", Toast.LENGTH_SHORT).show();
                }
                balance = balance + amount;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putFloat("balance", (float) balance);
                editor.apply();
            }
        });
    }
}