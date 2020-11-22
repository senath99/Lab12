package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Messages extends AppCompatActivity {

    TextView message,subjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_messages );
        message = findViewById(R.id.message);
        subjects = findViewById(R.id.subject);

        Intent intent = getIntent();
        String MyMessage = intent.getStringExtra("Messages");
        String MySubject = intent.getStringExtra("Subject");

        message.setText(MyMessage);
        subjects.setText(MySubject);
    }
}