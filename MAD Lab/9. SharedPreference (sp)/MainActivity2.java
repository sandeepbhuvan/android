package com.example.sp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sp.R;

public class MainActivity2 extends AppCompatActivity {

    SharedPreferences preferences;
    TextView message;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        message = findViewById(R.id.message);
        logout = findViewById(R.id.logout);

        // Retrieve Shared Preferences
        preferences = getSharedPreferences("credentials", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        if (username.isEmpty()) {
            // If username is empty, redirect to MainActivity (Login Screen)
            Intent intent = new Intent(this, com.example.sp.MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Display the welcome message
        String msg = "Hello, " + username + "!";
        message.setText(msg);

        // Set up Logout button
        logout.setOnClickListener(e -> {
            // Clear the username in SharedPreferences
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", "");
            editor.apply();

            // Redirect to MainActivity (Login Screen)
            Intent intent = new Intent(this, com.example.sp.MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
