package com.example.lab12.Database;

import android.provider.BaseColumns;

public final class Message  {

    private Message(){}

    public static class message implements BaseColumns {



        public static final  String TABLE_NAME = "messages";

        public static final String COLUMN_NAME_SUBJECT = "subject";
        public static final String COLUMN_NAME_MESSAGE = "message";
        public static final String COLUMN_NAME_USERNAME = "username";

    }
}