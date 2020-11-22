package com.example.lab12.Database;

import android.provider.BaseColumns;

public final class UserMaster {

    private UserMaster(){}

    public static class Users implements BaseColumns{

        public static final  String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_TYPE = "type";

        public static String getColumnNameName() {
            return COLUMN_NAME_NAME;
        }

        public static String getColumnNamePassword() {
            return COLUMN_NAME_PASSWORD;
        }

        public static String getColumnNameType() {
            return COLUMN_NAME_TYPE;
        }



    }

}



//    Primary Key
//Name - String
//        Password - String
//        Type - String