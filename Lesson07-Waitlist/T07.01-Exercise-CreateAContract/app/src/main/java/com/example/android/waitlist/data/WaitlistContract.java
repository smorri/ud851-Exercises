package com.example.android.waitlist.data;

import android.provider.BaseColumns;

/**
 * SQLite Waitlist : Contract Class
 *
 * @author Samone Morris
 * @date   04/09/18 (Re-completed as original file was lost due to motherboard failure)
 */

public class WaitlistContract {
    // COMPLETED (1) Create an inner class named WaitlistEntry class that implements the BaseColumns interface

    // To prevent users from creating an instance of this class
    private WaitlistContract(){}

    public final static class WaitlistEntry implements BaseColumns{
        // COMPLETED (2) Inside create a static final members for the table name and each of the db columns :
        public final static String TABLE_NAME = "waitlist",         // TABLE_NAME -> waitlist;
                                   COLUMN_GUEST_NAME = "guestName", // COLUMN_GUEST_NAME -> guestName
                                   COLUMN_PARTY_SIZE = "partySize", // COLUMN_PARTY_SIZE -> partySize
                                   COLUMN_TIMESTAMP = "timestamp";  // COLUMN_TIMESTAMP -> timestamp
    }// end inner class WaitlistEntry
}
