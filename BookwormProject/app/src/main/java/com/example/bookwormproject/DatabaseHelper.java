package com.example.bookwormproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "bookwormparadise.db";
    public static String TABLE_NAME = "Member";
    public static String TABLE_NAME2 = "Reviews";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +TABLE_NAME+"(" +
                "MemberID TEXT PRIMARY KEY," +
                "Username TEXT NOT NULL," +
                "Fullname TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "Address TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Phone TEXT NOT NULL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS " +TABLE_NAME2+"(" +
                "ReviewID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MemberID TEXT NOT NULL," +
                "LibraryID INTEGER NOT NULL," +
                "ReviewTitle TEXT NOT NULL," +
                "ReviewDescription TEXT NOT NULL" +
                ")");

        db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES" +
                "('MB001', 'admin', 'Admin', 'admin', 'Jl. Kebon Jerok No. 21', 'admin@bookparadise.com', '08123456789')," +
                "('MB002', 'Deny_h0', 'Deny Howgart', 'Den0123', 'Jalan Panjang No. 12', 'Deny_h@bookparadise.com', '089863217865')");

        db.execSQL("INSERT INTO "+TABLE_NAME2+" VALUES" +
                "(1, 'MB001', 1, 'Good', 'This library have a complete series of books')," +
                "(2, 'MB002', 1, 'Best Library', 'Best Library ever')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2 );
        onCreate(db);
    }

    public boolean cekLogin(String Username, String Password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " +TABLE_NAME+" where Username = ? and Password = ?", new String[]{Username, Password});
        if(cursor.getCount()>0) {
            return true;
        }else{
            return false;
        }
    }

    public Cursor getLastID(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select MemberID from "+TABLE_NAME+" ORDER BY MemberID DESC", null);

        return result;
    }

    public boolean insertMember(String MemberID,String Username, String Fullname, String Password, String Address, String Email, String Phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MemberID",MemberID);
        cv.put("Username",Username);
        cv.put("Fullname",Fullname);
        cv.put("Password",Password);
        cv.put("Address",Address);
        cv.put("Email",Email);
        cv.put("Phone",Phone);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            return false;
        }
        return true;
    }

    public Cursor listReview(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);

        return data ;
    }

    public Cursor listReviewLibrary(String libID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE LibraryID = ? ", new String[]{libID});

        return data ;
    }

    public Integer deleteReview(String reviewid){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2,"ReviewID = ?",new String[]{reviewid});

    }


}
