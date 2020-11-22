package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab12.Database.DBHelper;

import java.util.List;

public class Register extends AppCompatActivity {

    EditText etUsername,etPassword;
    CheckBox etTeacher,etStudent;
    String Type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        etUsername =(EditText) findViewById(R.id.edName);
        etPassword = (EditText) findViewById(R.id.edPassword);
        etTeacher =(CheckBox)  findViewById(R.id.Teacher);
        etStudent =(CheckBox) findViewById(R.id.Student);


    }

    public void addData(View view){

        if(etTeacher.isChecked() && !etStudent.isChecked()){
            Type = "Teacher";


            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.Register(etUsername.getText().toString(),etPassword.getText().toString(),Type);

            if(val > 0 ){
                Toast.makeText( this, "Data successfully inserted", Toast.LENGTH_SHORT ).show();
            }else{
                Toast.makeText( this, "Data not inserted", Toast.LENGTH_SHORT ).show();
            }
        }
        else if(etStudent.isChecked() && !etTeacher.isChecked() ){
            Type = "Student";


            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.Register(etUsername.getText().toString(),etPassword.getText().toString(),Type);

            if(val > 0 ){
                Toast.makeText( this, "Data successfully inserted", Toast.LENGTH_SHORT ).show();
            }else{
                Toast.makeText( this, "Data not inserted", Toast.LENGTH_SHORT ).show();
            }
        }else{

            Toast.makeText( this, "Both boxes cannot be checked", Toast.LENGTH_SHORT ).show();

        }


    }

    public void viewAll(View view) {

        DBHelper dbHelper = new DBHelper( this );

        List unames = dbHelper.readAllInfo( "type" );

        Toast.makeText( this, unames.toString(), Toast.LENGTH_SHORT ).show();
    }


}