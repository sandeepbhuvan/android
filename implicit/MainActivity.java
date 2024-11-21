package com.example.implicit_intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button web, dial, mail, text;
    EditText textInput, urlInput, phoneInput, email, subject, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        web = findViewById(R.id.web);
        dial = findViewById(R.id.dial);
        mail = findViewById(R.id.mail);
        text = findViewById(R.id.text);

        //EditTexts
        textInput = findViewById(R.id.textInput);
        urlInput = findViewById(R.id.urlInput);
        phoneInput = findViewById(R.id.phoneInput);
        email = findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        content = findViewById(R.id.content);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlInput.getText().toString().trim();

                if (!url.isEmpty()) {
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://" + url;
                    }

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            }
        });

        //Dial phone number
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneInput.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent);
                }
            }
        });

        //Send email
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = email.getText().toString().trim();
                String emailSubject = subject.getText().toString().trim();
                String emailContent = content.getText().toString().trim();

                if (!emailAddress.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{emailAddress});
                    intent.putExtra(Intent.EXTRA_SUBJECT,emailSubject);
                    intent.putExtra(Intent.EXTRA_TEXT,emailContent);
                    startActivity(intent);
                }
            }
        });


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToShare = textInput.getText().toString().trim();
                if (!textToShare.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, textToShare);
                    startActivity(Intent.createChooser(intent, "Share with"));
                }
            }
        });
    }
}
