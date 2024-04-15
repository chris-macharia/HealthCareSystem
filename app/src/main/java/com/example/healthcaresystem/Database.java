package com.example.healthcaresystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text, email text, password text)";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Register new users-----Sign Up page
    public void registerUser(String username, String email, String passwd){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email", email);
        cv.put("password", passwd);
        SQLiteDatabase db = getReadableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    //Log in existing users
    public int login(String username, String password){
        int result = 0; //user not found
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if(c.moveToFirst()){
            result=1; //user exist
        }

        return result;
    }
}
