package com.edlr.ipcalc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "historical.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table history(ids integer not null primary key, sks integer, ipk real, timestamp text);";
        //Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "create table grade(ids integer not null primary key, a real, ab real, b real, bc real, c real, d real, e real, k real);";
        db.execSQL(sql);
        assignDefaultGrade(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void clearHistory(SQLiteDatabase db) {
        String sql = "delete from history;";
        db.execSQL(sql);
    }

    public void assignDefaultGrade(SQLiteDatabase db){
        String sql = "INSERT INTO grade (a, ab, b, bc, c, d, e, k) VALUES ('4.00', '3.50', '3.00', '2.50', '2.00', '1.00', '0.00', '0.00');";
        db.execSQL(sql);
    }

    public void resetGradeToDefault(SQLiteDatabase db){
        String sql = "UPDATE grade SET " +
                "a='4.00', ab='3.50', b='3.00', bc='2.50', c='2.00', d='1.00', e='0.00', k='0.00' " +
                "WHERE ids='1'";
        db.execSQL(sql);
    }
}