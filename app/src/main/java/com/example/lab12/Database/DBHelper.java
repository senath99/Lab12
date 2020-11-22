package com.example.lab12.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MessageApp";

    public DBHelper(Context context) {
        super( context, DATABASE_NAME, null, 1 );
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + UserMaster.Users.TABLE_NAME + "(" +
                        UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                        UserMaster.Users.COLUMN_NAME_NAME + " TEXT," +
                        UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                        UserMaster.Users.COLUMN_NAME_TYPE + " TEXT)";

        sqLiteDatabase.execSQL( SQL_CREATE_ENTRIES );

        String SQL_CREATE_Message =
                "CREATE TABLE " + Message.message.TABLE_NAME + "(" +
                        Message.message._ID + " INTEGER PRIMARY KEY," +

                        Message.message.COLUMN_NAME_SUBJECT + " TEXT," +
                        Message.message.COLUMN_NAME_MESSAGE + " TEXT," +
                        Message.message.COLUMN_NAME_USERNAME + " TEXT)";

        sqLiteDatabase.execSQL( SQL_CREATE_Message );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public long Register(String userName, String password, String Type) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( UserMaster.Users.COLUMN_NAME_NAME, userName );
        values.put( UserMaster.Users.COLUMN_NAME_PASSWORD, password );
        values.put( UserMaster.Users.COLUMN_NAME_TYPE, Type );

        long newRowId = db.insert( UserMaster.Users.TABLE_NAME, null, values );
        return newRowId;
    }


    public List readAllInfo(String req) {


        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_NAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD,
                UserMaster.Users.COLUMN_NAME_TYPE

        };

        String sortOrder = UserMaster.Users.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();
        List types = new ArrayList<>();

        while (cursor.moveToNext()) {

            String username = cursor.getString( cursor.getColumnIndexOrThrow( UserMaster.Users.COLUMN_NAME_NAME ) );
            String password = cursor.getString( cursor.getColumnIndexOrThrow( UserMaster.Users.COLUMN_NAME_PASSWORD ) );
            String type = cursor.getString( cursor.getColumnIndexOrThrow( UserMaster.Users.COLUMN_NAME_TYPE ) );
            userNames.add( username );
            passwords.add( password );
            types.add( type );

        }
        cursor.close();
        if (req == "user") {
            return userNames;
        } else if (req == "password") {
            return passwords;
        } else if (req == "type") {
            return types;
        } else {
            return null;
        }


    }

    //inserting messages
    public long addMessage(String subject, String message, String user) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put( Message.message.COLUMN_NAME_SUBJECT, subject );
        values.put( Message.message.COLUMN_NAME_MESSAGE, message );
        values.put( Message.message.COLUMN_NAME_USERNAME, user );

        long newRowId = db.insert( Message.message.TABLE_NAME, null, values );
        return newRowId;
    }


    public List readAllMessages(String req) {


        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                Message.message._ID,
                Message.message.COLUMN_NAME_MESSAGE,
                Message.message.COLUMN_NAME_SUBJECT,
                Message.message.COLUMN_NAME_USERNAME,

        };

        String sortOrder = Message.message._ID+ " DESC";

        Cursor cursor = db.query(
                Message.message.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List messages = new ArrayList<>();
        List subjects = new ArrayList<>();


        while (cursor.moveToNext()) {

            String subject = cursor.getString( cursor.getColumnIndexOrThrow( Message.message.COLUMN_NAME_SUBJECT) );
            String message = cursor.getString( cursor.getColumnIndexOrThrow( Message.message.COLUMN_NAME_MESSAGE) );

            messages.add(message);
            subjects.add(subject);


        }
        cursor.close();
        if (req == "message") {
            return messages;
        } else if (req == "subject") {
            return subjects;
        }else {
            return null;
        }


    }

    public List readAMessage(String req) {


        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {

                Message.message._ID,
                Message.message.COLUMN_NAME_MESSAGE,
                Message.message.COLUMN_NAME_SUBJECT,
                Message.message.COLUMN_NAME_USERNAME,

        };

        String sortOrder = Message.message._ID + " DESC  ";

        Cursor cursor = db.query(
                Message.message.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,

                sortOrder,
                "1"
        );

        List messages = new ArrayList<>();
        List subjects = new ArrayList<>();

        while (cursor.moveToNext()) {


            String message = cursor.getString( cursor.getColumnIndexOrThrow( Message.message.COLUMN_NAME_MESSAGE) );
            String subject = cursor.getString( cursor.getColumnIndexOrThrow( Message.message.COLUMN_NAME_SUBJECT) );

            messages.add(message);
            subjects.add(subject);


        }
        cursor.close();
        if (req == "message") {
            return messages;
        } else if (req == "subject") {
            return subjects;
        }else {
            return null;
        }




    }
}