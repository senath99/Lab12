package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab12.Database.DBHelper;
import com.example.lab12.Database.Message;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    TextView txt1;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student );
        txt1 = findViewById( R.id.mytxt);
        listView = findViewById( R.id.listView);

        Intent intent = getIntent();
        final String UserName = intent.getStringExtra("UserName");
        Log.i("uu",UserName);
        txt1.setText("Welcome " +UserName);





    }

    @Override
    protected void onStart() {
        super.onStart();
        viewAll();
    }


    private void viewAll() {

        DBHelper dbHelper = new DBHelper( this );

        final List messages = dbHelper.readAllMessages("message");
        final List subjects= dbHelper.readAllMessages("subject");


        ArrayAdapter<String> adapters = new ArrayAdapter(this,android.R.layout.simple_list_item_1,subjects);
        listView.setAdapter(adapters);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String Message = (String) messages.get(i);
                String Subject = (String) subjects.get(i);
                Intent intent = new Intent(getApplicationContext(),Messages.class);
                intent.putExtra("Subject",Subject);
                intent.putExtra("Messages",Message);
                startActivity(intent);

            }
        });



    }
}