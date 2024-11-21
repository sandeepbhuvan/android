package com.example.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences preferences = getSharedPreferences("username",0);
        TextView t1 = findViewById(R.id.textView);
        Button b1 = findViewById(R.id.button);

        String username = preferences.getString("username","");
        if(username.isEmpty())
        {
            Toast.makeText(this, "No username saved previously...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent);
        }

        else {
            String message = "Welcome, " + username + " ! ";
            t1.setText(message);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    Intent back = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(back);
                }
            });
        }
    }
}