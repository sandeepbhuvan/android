package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView display;
    String first = null;
    String second = null;
    String op = null;
    Button add, subtract, multiply, divide, equals, clear, back, dot;
    Button[] digits = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);

        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.subtract);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        Button[] operators = {add, subtract, multiply, divide};

        equals = (Button) findViewById(R.id.equals);
        clear = (Button) findViewById(R.id.clear);
        back = (Button) findViewById(R.id.back);
        dot = (Button) findViewById(R.id.dot);

        for (int i = 0; i < 10; i++) {
            digits[i] = (Button) findViewById(getResources().getIdentifier("b" + i, "id", getPackageName()));
        }

        // Handle operator clicks
        for (Button sign: operators) {
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // If no entry in the display, do nothing. Otherwise
                    if (first != null || second != null) {
                        String operator = sign.getText().toString();
                        if (first != null && second != null) {
                            // Both numbers are set, hence perform the previously stored operation
                            first = calculate(first, second, op);
                            second = null;
                            display.setText(first);
                        } else if (first == null) {
                            // If the second number is set, but not the first number
                            first = second;
                            second = null;
                        }
                        // If the first number is the only number set, store the operator
                        op = operator;
                    }
                }
            });
        }

        // Handle equals button
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first == null || second == null) return;
                first = calculate(first, second, op);
                if (first == null) {
                    display.setText("ERROR");
                } else {
                    display.setText(first);
                }
                second = null;
                op = null;
            }
        });

        // Handle backspace
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first != null && second == null) {
                    // If the first number is currently on display
                    first = first.substring(0, first.length() - 1);
                    // If the number is fully deleted
                    if (first.isEmpty()) {
                        first = null;
                        display.setText("0");
                        return;
                    }
                    display.setText(first);
                } else if (first != null) {
                    // If the second number is currently on display
                    second = second.substring(0, second.length() - 1);
                    if (second.isEmpty()) {
                        second = null;
                        display.setText("0");
                        return;
                    }
                    display.setText(second);
                }
            }
        });

        // Handle click on AC
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first = null;
                second = null;
                op = null;
                display.setText("0");
            }
        });

        // Handle dot
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If the number is empty, set it to '0.'
                if (first == null && second == null) {
                    first = "0.";
                    display.setText(first);
                }
                // Check if a dot is already in the current number
                if (display.getText().toString().indexOf('.') != -1) {
                    // If the first number is entered and and an operator is pressed, enter a 0
                    if (first != null && op != null) {
                        second = "0.";
                        display.setText(second);
                    }
                    return;
                }
                if (first != null && second == null) {
                    // If the first number is currently on display
                    first += ".";
                    display.setText(first);
                } else if (first != null) {
                    // If the second number is currently on display
                    second += ".";
                    display.setText(second);
                }
            }
        });

        // For each digit button
        for (Button b: digits) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the digit from the button
                    String digit = ((Button) view).getText().toString();
                    String curr = "0";
                    // Append the digit to the current number in the display
                    if (first == null) {
                        first = digit;
                        curr = first;
                    } else if (second == null && op == null) {
                        first += digit;
                        curr = first;
                    } else if (second == null) {
                        second = digit;
                        curr = second;
                    } else {
                        second += digit;
                        curr = second;
                    }
                    display.setText(curr);
                }
            });
        }
    }

    // Perform the calculation
    public String calculate(String first, String second, String op) {
        double f = Double.parseDouble(first);
        double s = Double.parseDouble(second);

        double ans = 0;
        switch (op) {
            case "+":
                ans = f + s;
                break;
            case "-":
                ans = f - s;
                break;
            case "×": // (U+00D7)
                ans = f * s;
                break;
            case "÷": // (U+00F7)
                if (s == 0) return null;
                ans = f / s;
                break;
        }
        // Format the answer to 9 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.#########");
        decimalFormat.setDecimalSeparatorAlwaysShown(false); // Don't show decimal point if it's 0
        return decimalFormat.format(ans);
    }
}