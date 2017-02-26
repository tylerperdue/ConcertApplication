package concertApplication;

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
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "ConcertApplication";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // All necessary tables will be created here

        String CREATE_TABLE_USER = "CREATE TABLE " + User.TABLE + "("
                + User.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + User.KEY_username + " TEXT, "
                + User.KEY_password + " TEXT" + ");";

        String CREATE_TABLE_VENUE = "CREATE TABLE " + Venue.TABLE + "("
                + Venue.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Venue.KEY_name + " TEXT, "
                + Venue.KEY_address + " TEXT" + ");";

        String CREATE_TABLE_EVENT = "CREATE TABLE " + Event.TABLE + "("
                + Event.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Event.KEY_name + " TEXT, "
                + Event.KEY_date + " DATE, "
                + Event.KEY_venue + " INTEGER, "
                + "FOREIGN KEY(" + Event.KEY_venue + ") REFERENCES " + Venue.TABLE +
                "(" + Venue.KEY_ID + "));";

        String CREATE_TABLE_ARTIST = "CREATE TABLE " + Artist.TABLE + "("
                + Artist.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Artist.KEY_name + " TEXT, "
                + Artist.KEY_genre + " TEXT" + ");";

        String CREATE_TABLE_EVENT_ARTIST = "CREATE TABLE event_artist(event_id INTEGER, " +
                "artist_id INTEGER, FOREIGN KEY(event_id) REFERENCES " + Event.TABLE + "("
                + Event.KEY_ID + "), FOREIGN KEY(artist_id) REFERENCES " + Artist.TABLE + "("
                + Artist.KEY_ID + "));";

        String CREATE_TABLE_FAVORITED_ARTISTS = "CREATE TABLE favorited_artists (user_id INTEGER, " +
                "artist_id INTEGER, FOREIGN KEY(user_id) REFERENCES " + User.TABLE + "("
                + User.KEY_ID + "), FOREIGN KEY(artist_id) REFERENCES " + Artist.TABLE + "("
                + Artist.KEY_ID + "));";

        String INSERT_TEST_VENUE = "INSERT INTO " + Venue.TABLE + " VALUES(1, 'Example Venue', '1234 Address St.');";
        String INSERT_TEST_EVENT = "INSERT INTO " + Event.TABLE + " VALUES(1, 'Example Event', '1988-10-30', 1);";

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_VENUE);
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_ARTIST);
        db.execSQL(CREATE_TABLE_EVENT_ARTIST);
        db.execSQL(CREATE_TABLE_FAVORITED_ARTISTS);
        db.execSQL(INSERT_TEST_VENUE);
        db.execSQL(INSERT_TEST_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS favorited_artists");
        db.execSQL("DROP TABLE IF EXISTS event_artist");
        db.execSQL("DROP TABLE IF EXISTS " + Artist.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Event.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Venue.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);

        // Creating tables again
        onCreate(db);
    }

    // Class to return Event table column names
    public String[] getEventColumns() {
        String [] columns = {Event.KEY_ID, Event.KEY_name, Event.KEY_date, Event.KEY_venue};
        return columns;
    }

    public Cursor getAllEvents() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(Event.TABLE, getEventColumns(),
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public int getNumberOfEvents() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM Event";
        Cursor mcursor = db.rawQuery(count, null);
        if (mcursor != null) {
            mcursor.moveToFirst();
        }
        int icount = mcursor.getInt(0);
        return icount;
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
