package com.example.android.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// COMPLETED (1) extend the SQLiteOpenHelper class
public class WaitlistDbHelper extends SQLiteOpenHelper {
    // COMPLETED (2) Create a static final String called DATABASE_NAME and set it to "waitlist.db"
    private final static String DATABASE_NAME = "waitlist.db";

    // COMPLETED (3) Create a static final int called DATABASE_VERSION and set it to 1
    private final static int DATABASE_VERSION = 1;

    // COMPLETED (4) Create a Constructor that takes a context and calls the parent constructor
    public WaitlistDbHelper(Context context) {
        super( context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }// end Constructor WaitlistDbHelper

    // TODO (5) Override the onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table
        
        // TODO (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
    }

    // TODO (8) Override the onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO (9) Inside, execute a drop table query, and then call onCreate to re-create it
    }
}