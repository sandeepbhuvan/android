package com.example.alc;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Created!");
    }
        // User navigates to the activity, and the activity is started

        protected void onStart() {
            super.onStart();
            Log.d(TAG, "Started!");
        }

        // User returns to a paused activity
        protected void onResume() {
            super.onResume();
            Log.d(TAG, "Resumed!");
        }

        // User navigates away from the activity but it's still partially visible
        protected void onPause() {
            super.onPause();
            Log.d(TAG, "Paused!");
        }

        // User navigates away from the activity and it's no longer visible
        protected void onStop() {
            super.onStop();
            Log.d(TAG, "Stopped!");
        }

        // Activity is being restarted from stopped state, when user navigates back to it
        protected void onRestart() {
            super.onRestart();
            Log.d(TAG, "Restarted!");
        }

        // Activity is finished or destroyed by the system
        protected void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "Destroyed!");
        }
    }