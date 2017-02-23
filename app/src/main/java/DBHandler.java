package com.example.tylerperdue.concertapplication;

/**
 * Created by tylerperdue on 2/9/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ConcertApplication";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // All necessary tables will be created here

        String CREATE_TABLE_USER = "CREATE TABLE " + User.TABLE + "("
                + User.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + User.KEY_username + " TEXT,"
                + User.KEY_password + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);

        // Creating tables again
        onCreate(db);
    }

    // Class to insert user
    public int insertUser(User user) {
        //Open connection to write data
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_username, user.getUsername());
        values.put(User.KEY_password, user.getPassword());

        // Inserting Row
        long user_Id = db.insert(User.TABLE, null, values);
        db.close();
        return (int) user_Id;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_ID + "= ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_username, user.getUsername());
        values.put(User.KEY_password, user.getPassword());

        db.update(User.TABLE, values, User.KEY_ID + "= ?", new String[]{String.valueOf(user.getId())});
    }

    // Getting All Users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + User.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId((Integer.parseInt(cursor.getString(0))));
                user.setUsername((cursor.getString(1)));
                user.setPassword((cursor.getString(2)));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }
}
