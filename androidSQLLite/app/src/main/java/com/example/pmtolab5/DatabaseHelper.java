package com.example.pmtolab5;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lab5.db"; // name db
    private static final int SCHEMA = 1; // version db
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Android (_id INTEGER PRIMARY KEY AUTOINCREMENT,Owner_Company TEXT, Version TEXT, Architecture TEXT,MarketShare TEXT,Unique(Owner_Company,Version,Architecture,MarketShare))");
        db.execSQL("CREATE TABLE IF NOT EXISTS IOS (_id INTEGER PRIMARY KEY AUTOINCREMENT,Owner_Company TEXT, Version TEXT, Architecture TEXT,MarketShare TEXT,Unique(Owner_Company,Version,Architecture,MarketShare))");
        db.execSQL("CREATE TABLE IF NOT EXISTS BlackBerryOS (_id INTEGER PRIMARY KEY AUTOINCREMENT,Owner_Company TEXT, Version TEXT, Architecture TEXT,MarketShare TEXT,Unique(Owner_Company,Version,Architecture,MarketShare))");
        db.execSQL("CREATE TABLE IF NOT EXISTS WindowsPhone (_id INTEGER PRIMARY KEY AUTOINCREMENT,Owner_Company TEXT, Version TEXT, Architecture TEXT,MarketShare TEXT,Unique(Owner_Company,Version,Architecture,MarketShare))");
        db.execSQL("INSERT OR IGNORE INTO Android(Owner_Company,Version,Architecture,MarketShare) VALUES ('Google', '11','Linux Kernel, collection of c/c++ libraries','73');");
        db.execSQL("INSERT OR IGNORE INTO IOS(Owner_Company,Version,Architecture,MarketShare) VALUES ('Apple Inc.', '15.0.2','A layered architecture','26.34');");
        db.execSQL("INSERT OR IGNORE INTO BlackBerryOS(Owner_Company,Version,Architecture,MarketShare) VALUES ('BlackBerry Limited', '10.3.3.3216','Multiplatform EMM solution','0.11');");
        db.execSQL("INSERT OR IGNORE INTO WindowsPhone(Owner_Company,Version,Architecture,MarketShare) VALUES ('Microsoft', '10.0.15254.603','Written in C,C++','0.13');");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Android");
        db.execSQL("DROP TABLE IF EXISTS IOS");
        db.execSQL("DROP TABLE IF EXISTS BlackBerryOS");
        db.execSQL("DROP TABLE IF EXISTS WindowsPhone");
        onCreate(db);
    }
}