package com.example.cerealbox.jobtrack;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cerealbox on 2015-08-05.
 */
public class ApplicationsDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    public ApplicationsDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public long insertCompany(String company, String position, String date){

        ContentValues values = new ContentValues();
        String[] dateParts = date.split("/");

        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        String aid = generateApplicationID();

        values.put(dbHelper.COMPANY, company);
        values.put(dbHelper.POSITION, position);
        values.put(dbHelper.MONTH, month + 1);
        values.put(dbHelper.DAY, day);
        values.put(dbHelper.YEAR, year);
        values.put(dbHelper.AID, aid);



        long rowID = database.insert(dbHelper.TABLE_NAME, null, values);

        return rowID;
    }

    public long editCompany(String company, String position, String date, String saved_aid){

        ContentValues values = new ContentValues();
        String[] dateParts = date.split("/");

        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        values.put(dbHelper.COMPANY, company);
        values.put(dbHelper.POSITION, position);
        values.put(dbHelper.MONTH, month);
        values.put(dbHelper.DAY, day);
        values.put(dbHelper.YEAR, year);



        long rowID = database.update(dbHelper.TABLE_NAME, values, dbHelper.AID + " = '" + saved_aid + "'", null);

        return rowID;
    }


    public void deleteAll(){
        database.delete(MySQLiteHelper.TABLE_NAME, null, null);
    }


    public void deleteApplication(String aid){
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.AID + " = '" + aid + "'", null);
    }

    public int parseDateFie(String date, int field){
        int index = 0;
        int dashCount = 0;
        StringBuilder sb = new StringBuilder();

        while(dashCount < 1){
            if(date.charAt(index) == '/'){
                dashCount++;
            }else{
                sb.append(date.charAt(index));
                if(index == date.length()-1){
                    dashCount++;
                }

            }
            index++;
        }


        int value = Integer.parseInt(sb.toString());

        return value;
    }

    public List<Applications> displayApplications(){
        String[] columns = {MySQLiteHelper.COMPANY, MySQLiteHelper.POSITION, MySQLiteHelper.MONTH, MySQLiteHelper.DAY, MySQLiteHelper.YEAR};
        List<Applications> applications = new ArrayList<Applications>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, columns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Applications application = new Applications();

            application.setCompany(cursor.getString(0));
            application.setPosition(cursor.getString(1));
            application.setMonth(cursor.getInt(2));
            application.setDay(cursor.getInt(3));
            application.setYear(cursor.getInt(4));


            applications.add(application);
            cursor.moveToNext();

        }

        cursor.close();
        return applications;

    }

    public List<Applications> displayApplicationsForMonth(int month){
        String[] columns = {MySQLiteHelper.COMPANY, MySQLiteHelper.POSITION, MySQLiteHelper.MONTH, MySQLiteHelper.DAY, MySQLiteHelper.YEAR, MySQLiteHelper.AID};
        List<Applications> applications = new ArrayList<Applications>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, columns, "Month is " + String.valueOf(month), null, null, null, MySQLiteHelper.DAY);

        // Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME, columns, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Applications application = new Applications();

            application.setCompany(cursor.getString(0));
            application.setPosition(cursor.getString(1));
            application.setMonth(cursor.getInt(2));
            application.setDay(cursor.getInt(3));
            application.setYear(cursor.getInt(4));
            application.setAid(cursor.getString(5));



            applications.add(application);
            cursor.moveToNext();

        }

        cursor.close();
        return applications;

    }


    public String generateApplicationID(){

        String characters = "abcdefghijklmnopqarstuvqxyz1234567890";
        char[] aid = new char[6];

        Random randomizer = new Random();
        int index;
        for(int i = 0; i < 6; i++){
            index = randomizer.nextInt(37);
            aid[i] = characters.charAt(index);
        }

        String aid_string = new String(aid);
        return aid_string;



    }


}