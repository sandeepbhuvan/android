package com.example.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText e1 = findViewById(R.id.e1);
        Button save = findViewById(R.id.save);
        Button view = findViewById(R.id.view);

        SharedPreferences preferences = getSharedPreferences("username",0);

        String username = preferences.getString("username","");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = e1.getText().toString().trim();

                if(!username.isEmpty())
                {   //updating SharedPreferences file
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username",username);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Username saved!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Viewing saved username..", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
                finish();
                startActivity(intent2);
            }
        });
    }
}