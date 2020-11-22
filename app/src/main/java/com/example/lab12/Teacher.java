package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab12.Database.DBHelper;

public class Teacher extends AppCompatActivity {
    EditText etSubject, etMessage;
    TextView text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_teacher );

        etSubject = findViewById( R.id.subject );
        etMessage = findViewById( R.id.message );
        text2 = findViewById( R.id.TXT2);

        Intent intent = getIntent();
        final String UserName = intent.getStringExtra("UserName");

        text2.setText("Welcome " +UserName);

    }

    public void addData(View view) {
        DBHelper dbHelper = new DBHelper( this );

        Intent intent = getIntent();
        final String UserNames = intent.getStringExtra("UserName");

        long val = dbHelper.addMessage( etSubject.getText().toString(),etMessage.getText().toString(),UserNames);
        Log.i("uu",UserNames);


        if (val > 0) {
            Toast.makeText( this, "Data successfully inserted", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( this, "Data not inserted", Toast.LENGTH_SHORT ).show();
        }

    }

}