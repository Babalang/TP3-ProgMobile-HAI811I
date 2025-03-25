package com.example.tp3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSet extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "inscription.db";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Table Name
    public static final String TABLE_USER_DATA = "user_data";
    public static final String TABLE_PLANNING = "planning";
    public static final String TABLE_NEWTABLE = "newtable";

    // Column Names
    public static final String COLUMN_ID = "_id"; // Primary key
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_BIRTH_DATE = "birth_date";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_INTERESTS = "interests";
    public static final String COLUMN_TIME_SLOT = "time_slot";
    public static final String COLUMN_ACTIVITY = "activity";

    // SQL statement to create the table
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_USER_DATA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_LOGIN + " TEXT NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_SURNAME + " TEXT, " +
                    COLUMN_BIRTH_DATE + " TEXT, " +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_INTERESTS + " TEXT);";

    private static final String DATABASE_CREATE_PLANNING =
            "CREATE TABLE " + TABLE_PLANNING + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TIME_SLOT + " TEXT, " + COLUMN_ACTIVITY + " TEXT);";

    private static final String DATABASE_CREATE_NEWTABLE =
            "CREATE TABLE " + TABLE_NEWTABLE + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public DataSet(Context context, String nom, SQLiteDatabase.CursorFactory cursorfactory) {
        super(context, nom, cursorfactory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE_PLANNING);
        // New table:
        db.execSQL(DATABASE_CREATE_NEWTABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANNING);
        onCreate(db);
    }

    public void insertData(String login, String password, String name, String surname, String birthDate, String phoneNumber, String email, String interests) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + TABLE_USER_DATA + " (" + COLUMN_LOGIN + ", " + COLUMN_PASSWORD + ", " + COLUMN_NAME + ", " + COLUMN_SURNAME + ", " + COLUMN_BIRTH_DATE + ", " + COLUMN_PHONE_NUMBER + ", " + COLUMN_EMAIL + ", " + COLUMN_INTERESTS + ") ";
        query += "VALUES ('" + login + "', '" + password + "', '" + name + "', '" + surname + "', '" + birthDate + "', '" + phoneNumber + "', '" + email + "', '" + interests + "');";
        db.execSQL(query);
    }

    public void deleteData(String login) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_USER_DATA + " WHERE " + COLUMN_LOGIN + " = '" + login + "';";
        db.execSQL(query);
    }

    public void updateData(String login, String password, String name, String surname, String birthDate, String phoneNumber, String email, String interests) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_USER_DATA + " SET " + COLUMN_PASSWORD + " = '" + password + "', " + COLUMN_NAME + " = '" + name + "', " + COLUMN_SURNAME + " = '" + surname + "', " + COLUMN_BIRTH_DATE + " = '" + birthDate + "', " + COLUMN_PHONE_NUMBER + " = '" + phoneNumber + "', " + COLUMN_EMAIL + " = '" + email + "', " + COLUMN_INTERESTS + " = '" + interests + "' WHERE " + COLUMN_LOGIN + " = '" + login + "';";
        db.execSQL(query);
    }

    public Cursor getData(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_DATA + " WHERE " + COLUMN_LOGIN + " = '" + login + "';";
        return db.rawQuery(query, null);
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_DATA;
        return db.rawQuery(query, null);
    }
    public Cursor getLastUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_DATA + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        return db.rawQuery(query, null);
    }
}


