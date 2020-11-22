package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab12.Database.DBHelper;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "CHANNEL1";
    EditText userName,password;
    Button Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        userName = findViewById( R.id.Uname);
        password = findViewById(R.id.edPassword);
        Register = findViewById(R.id.register);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Register.class));
                finish();
            }
        });


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);


        }
    }

    public void Login(View view){

        DBHelper dbHelper = new DBHelper( this);

        List usernames = dbHelper.readAllInfo("user");
        List passwords = dbHelper.readAllInfo( "password");
        List types = dbHelper.readAllInfo("type");

        String User = userName.getText().toString();
        String pwd = password.getText().toString();

        if(usernames.indexOf(User) >=0){

            if(passwords.get(usernames.indexOf(User)).equals(pwd)){


                if(types.get(usernames.indexOf(User)).equals("Teacher")){


                    Intent intent = new Intent(getApplicationContext(), Teacher.class);
                    intent.putExtra("UserName",User);

                    startActivity(intent);

                }

                if(types.get(usernames.indexOf(User)).equals("Student")){



                    NotificationHandler();
                    Intent intent = new Intent(getApplicationContext(), Student.class);
                    intent.putExtra("UserName",User);
                    startActivity(intent);
                }


            }else{

                Toast.makeText( this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();
            }
        }

    }


    public void NotificationHandler(){

        DBHelper dbHelper = new DBHelper( this);

        List latestMsg = dbHelper.readAMessage("message");
        List latestSubject = dbHelper.readAMessage("subject");



        String message = (String) latestMsg.get(0);// get the latest message
        String subject = (String) latestSubject.get(0); //get latest subject

        Intent notificationIntent = new Intent(getApplicationContext(),Messages.class);
        notificationIntent.putExtra("Messages", "message" );
        notificationIntent.putExtra("Subject",subject);

        startActivity(notificationIntent);
//
        String notificationMessage = message;
//                 Log.i("ds",latestMsg);
        PendingIntent pendingIntent =  PendingIntent.getActivity(  LoginActivity.this,0,notificationIntent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("You have received a new message")
                .setContentText(notificationMessage)

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(LoginActivity.this);
        notificationManagerCompat.notify(0, builder.build());



    }
}