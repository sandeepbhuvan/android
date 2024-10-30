package com.example.sp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sp.R;

public class MainActivity extends AppCompatActivity {

    EditText username;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        login = findViewById(R.id.login);

        // Get SharedPreferences to check if the user is already logged in
        SharedPreferences preferences = getSharedPreferences("credentials", MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");

        if (!savedUsername.isEmpty()) {
            // If a username is found, go directly to MainActivity2
            Intent intent = new Intent(this, com.example.sp.MainActivity2.class);
            startActivity(intent);
            finish();
            return;
        }

        // Handle login button click
        login.setOnClickListener(e -> {
            String user = username.getText().toString();
            if (user.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save username in SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", user);
            editor.apply();

            // Redirect to MainActivity2 (Home Screen)
            Intent intent = new Intent(this, com.example.sp.MainActivity2.class);
            startActivity(intent);
            finish();
        });
    }
}
