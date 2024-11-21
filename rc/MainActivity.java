package com.example.rc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RadioGroup department;
        LinearLayout events;
        Button register;

        department = (RadioGroup) findViewById(R.id.department);
        events = (LinearLayout) findViewById(R.id.events);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected department from the radio group
                int dept_id = department.getCheckedRadioButtonId();
                //returns -1 if no button is selected
                RadioButton dept = (RadioButton) findViewById(dept_id);

                // Get the selected events from the check boxes
                StringBuilder events_selected = new StringBuilder();
                for (int i = 0; i < events.getChildCount(); i++) {
//                    getChildCount is number of child views in the linear layout
                    CheckBox c = (CheckBox) events.getChildAt(i);
                    if (c.isChecked())
                        events_selected.append(c.getText().toString()).append(", ");
                }

                // Display a toast message with the selected department and events
                Toast.makeText(getApplicationContext(), "Department: " + dept.getText() + "\nEvents: " + events_selected.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}