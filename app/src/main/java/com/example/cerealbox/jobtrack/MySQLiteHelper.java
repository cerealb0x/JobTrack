package com.example.cerealbox.jobtrack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cerealbox on 2015-08-05.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    public static final String TABLE_NAME = "applications";
    public static final String COMPANY = "Company";
    public static final String POSITION = "Position";
    public static final String DAY = "Day";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";
    public static final String RECEIVED_INTERVIEW = "Interview";
    public static final String RECEIVED_OFFER = "Offer";
    public static final String AID = "AID";

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    private static final String DATABASE_NAME = "applications.db";

    private static final String DATABASE_CREATE = "create table " + TABLE_NAME
                                                    + " (" + COMPANY + " TEXT, "
                                                    + POSITION + " TEXT, "
                                                    + MONTH + " INT, "
                                                    + DAY + " INT, "
                                                    + YEAR + " INT, "
                                                    + RECEIVED_INTERVIEW + " INT, "
                                                    + RECEIVED_OFFER + " INT, "
                                                    + AID +" TEXT);";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



}
