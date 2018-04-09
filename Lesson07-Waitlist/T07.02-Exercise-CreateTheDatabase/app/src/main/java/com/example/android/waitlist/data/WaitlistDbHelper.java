package com.example.android.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.waitlist.data.WaitlistContract.*;

/**
 * SQLite Database Helper
 *
 * @author Samone Morris
 * @date   04/09/18 (re-completed due to lost file during motherboard failure)
 */

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

    // COMPLETED (5) Override the onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {
        // COMPLETED (6) Inside, create an String query called SQL_CREATE_WAITLIST_TABLE that will create the table
        String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + WaitlistEntry.TABLE_NAME + "( " +
                                                WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                WaitlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                                                WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
                                                WaitlistEntry.COLUMN_TIMESTAMP + "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                                            ");";

        // COMPLETED (7) Execute the query by calling execSQL on sqLiteDatabase and pass the string query SQL_CREATE_WAITLIST_TABLE
        db.execSQL( SQL_CREATE_WAITLIST_TABLE );
    }// end onCreate(...)

    // COMPLETED (8) Override the onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // COMPLETED (9) Inside, execute a drop table query, and then call onCreate to re-create it
        if( newVersion > oldVersion ){
            String SQL_DROP_WAITLIST_TABLE = "DROP TABLE IF EXISTS " + WaitlistEntry.TABLE_NAME + ";";

            db.execSQL( SQL_DROP_WAITLIST_TABLE );
            onCreate( db );
        }// end if
    }// end onUpgrade
}