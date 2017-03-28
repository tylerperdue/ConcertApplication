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
    private static final int DATABASE_VERSION = 13;

    // Database Name
    private static final String DATABASE_NAME = "ConcertApplication";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // All necessary tables will be created here

        String CREATE_TABLE_USER = "CREATE TABLE " + User.TABLE + "( "
                + User.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + User.KEY_name + " TEXT, "
                + User.KEY_username + " TEXT, "
                + User.KEY_password + " TEXT" + ");";

        String CREATE_TABLE_VENUE = "CREATE TABLE " + Venue.TABLE + "( "
                + Venue.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Venue.KEY_name + " TEXT, "
                + Venue.KEY_address + " TEXT" + ");";

        String CREATE_TABLE_EVENT = "CREATE TABLE " + Event.TABLE + "( "
                + Event.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Event.KEY_name + " TEXT, "
                + Event.KEY_date + " DATE, "
                + Event.KEY_venue + " INTEGER, "
                + "FOREIGN KEY(" + Event.KEY_venue + ") REFERENCES " + Venue.TABLE +
                "(" + Venue.KEY_ID + "));";

        String CREATE_TABLE_ARTIST = "CREATE TABLE " + Artist.TABLE + "( "
                + Artist.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Artist.KEY_name + " TEXT, "
                + Artist.KEY_genre + " TEXT" + ");";

        String CREATE_TABLE_EVENT_ARTIST = "CREATE TABLE event_artist(event_id INTEGER, " +
                "artist_id INTEGER, FOREIGN KEY(event_id) REFERENCES " + Event.TABLE + "("
                + Event.KEY_ID + "), FOREIGN KEY(artist_id) REFERENCES " + Artist.TABLE + "("
                + Artist.KEY_ID + "));";

        String CREATE_TABLE_FAVORITED_ARTISTS = "CREATE TABLE favorited_artists (_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, " +
                "artist_id INTEGER, FOREIGN KEY(user_id) REFERENCES " + User.TABLE + "("
                + User.KEY_ID + "), FOREIGN KEY(artist_id) REFERENCES " + Artist.TABLE + "("
                + Artist.KEY_ID + "));";

        String INSERT_TEST_VENUE1 = "INSERT INTO " + Venue.TABLE + " VALUES(1, 'Theatre of Living Artists', 'Philadelphia, PA');";
        String INSERT_TEST_VENUE2 = "INSERT INTO " + Venue.TABLE + " VALUES(2, 'The 930 Club', 'Washington, DC');";
        String INSERT_TEST_VENUE3 = "INSERT INTO " + Venue.TABLE + " VALUES(3, 'The Norva', 'Norfolk, VA');";
        String INSERT_TEST_VENUE4 = "INSERT INTO " + Venue.TABLE + " VALUES(4, 'Bryce Jordan Center', 'State College, PA');";
        String INSERT_TEST_VENUE5 = "INSERT INTO " + Venue.TABLE + " VALUES(5, 'Jiffy Lube Live', 'Bristow, VA');";
        String INSERT_TEST_VENUE6 = "INSERT INTO " + Venue.TABLE + " VALUES(6, 'Cameleon Club', 'Lancaster, PA');";

        String INSERT_TEST_EVENT1 = "INSERT INTO " + Event.TABLE + " VALUES(1, 'Beyonce Tour', '2017-8-23', 1);";
        String INSERT_TEST_EVENT2 = "INSERT INTO " + Event.TABLE + " VALUES(2, 'Drake Tour', '2017-4-10', 2);";
        String INSERT_TEST_EVENT3 = "INSERT INTO " + Event.TABLE + " VALUES(3, 'Lalapolooza', '2017-5-5', 3);";
        String INSERT_TEST_EVENT4 = "INSERT INTO " + Event.TABLE + " VALUES(4, 'Moving On', '2017-5-9', 4);";
        String INSERT_TEST_EVENT5 = "INSERT INTO " + Event.TABLE + " VALUES(5, 'Firefly', '2017-11-17', 5);";
        String INSERT_TEST_EVENT6 = "INSERT INTO " + Event.TABLE + " VALUES(6, 'Superbowl', '2017-10-14', 6);";

        String INSERT_TEST_ARTIST1 = "INSERT INTO " + Artist.TABLE + " VALUES(1, 'Beyonce', 'Pop');";
        String INSERT_TEST_ARTIST2 = "INSERT INTO " + Artist.TABLE + " VALUES(2, 'Drake', 'Rap/Hip-Hop');";
        String INSERT_TEST_ARTIST3 = "INSERT INTO " + Artist.TABLE + " VALUES(3, 'Cold Play', 'Alternative');";
        String INSERT_TEST_ARTIST4 = "INSERT INTO " + Artist.TABLE + " VALUES(4, 'Migos', 'Rap/Hip-Hop');";
        String INSERT_TEST_ARTIST5 = "INSERT INTO " + Artist.TABLE + " VALUES(5, 'Red Hot Chili Peppers', 'Rock');";
        String INSERT_TEST_ARTIST6 = "INSERT INTO " + Artist.TABLE + " VALUES(6, 'Taylor Swift', 'Pop');";


        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_VENUE);
        db.execSQL(CREATE_TABLE_EVENT);
        db.execSQL(CREATE_TABLE_ARTIST);
        db.execSQL(CREATE_TABLE_EVENT_ARTIST);
        db.execSQL(CREATE_TABLE_FAVORITED_ARTISTS);
        db.execSQL(INSERT_TEST_VENUE1);
        db.execSQL(INSERT_TEST_VENUE2);
        db.execSQL(INSERT_TEST_VENUE3);
        db.execSQL(INSERT_TEST_VENUE4);
        db.execSQL(INSERT_TEST_VENUE5);
        db.execSQL(INSERT_TEST_VENUE6);
        db.execSQL(INSERT_TEST_EVENT1);
        db.execSQL(INSERT_TEST_EVENT2);
        db.execSQL(INSERT_TEST_EVENT3);
        db.execSQL(INSERT_TEST_EVENT4);
        db.execSQL(INSERT_TEST_EVENT5);
        db.execSQL(INSERT_TEST_EVENT6);
        db.execSQL(INSERT_TEST_ARTIST1);
        db.execSQL(INSERT_TEST_ARTIST2);
        db.execSQL(INSERT_TEST_ARTIST3);
        db.execSQL(INSERT_TEST_ARTIST4);
        db.execSQL(INSERT_TEST_ARTIST5);
        db.execSQL(INSERT_TEST_ARTIST6);

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
        String [] columns = {Event.KEY_ID, Event.KEY_name, Event.KEY_venue, Event.KEY_date};
        return columns;
    }

    // Class to return Venue table column names
    public String[] getVenueColumns() {
        String [] columns = {Venue.KEY_ID, Venue.KEY_name, Venue.KEY_address};
        return columns;
    }

    // Class to return Artist table column names
    public String[] getArtistColumns() {
        String [] columns = {Artist.KEY_ID, Artist.KEY_name, Artist.KEY_genre};
        return columns;
    }

    public Cursor getAllEvents() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT Event._id, Event.name, Venue.name AS venue, Event.date " +
                "FROM Event " +
                "INNER JOIN Venue ON Event.venue = Venue._id;", null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getAllVenues() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(Venue.TABLE, getVenueColumns(),
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getAllArtists() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(Artist.TABLE, getArtistColumns(),
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public ArrayList<Artist> getAllFavoritedArtists(int userID) {
        String selectQuery = "SELECT * FROM favorited_artists WHERE user_id = " + userID + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery(selectQuery, null);
        ArrayList<Artist> favoritedArtists = new ArrayList<>();

        if (mCursor.moveToFirst()) {
            do {
                Artist artist = getArtist(Integer.parseInt(mCursor.getString(1)));
                favoritedArtists.add(artist);
            } while (mCursor.moveToNext());
        }
        return favoritedArtists;
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
        values.put(User.KEY_name, user.getName());
        values.put(User.KEY_username, user.getUsername());
        values.put(User.KEY_password, user.getPassword());

        // Inserting Row
        long user_Id = db.insert(User.TABLE, null, values);
        db.close();
        return (int) user_Id;
    }

    // Class to insert favorited artists
    public int insertFavoritedArtist(int userID, String artistName) {
        //Open connection to write data
        SQLiteDatabase db = getWritableDatabase();
        int artistID = getArtistID(artistName);
        ContentValues values = new ContentValues();
        values.put("user_id", userID);
        values.put("artist_id", artistID);

        long favorited_artist_id = db.insert("favorited_artists", null, values);
        db.close();
        return (int) favorited_artist_id;
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

    // Getting a single user
    public User getUser(int id) {
        User user = new User();
        String selectQuery = "SELECT * FROM " + User.TABLE + " WHERE _id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return user;
    }

    // Getting an artist
    public Artist getArtist(int id) {
        Artist artist = new Artist();
        String selectQuery = "SELECT * FROM " + Artist.TABLE + " WHERE _id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                artist.setId(Integer.parseInt(cursor.getString(0)));
                artist.setName(cursor.getString(1));
                artist.setGenre(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return artist;
    }

    // Getting an artist ID
    public int getArtistID(String artistName) {
        String selectQuery = "SELECT _id FROM " + Artist.TABLE + " WHERE name LIKE '%" + artistName + "%';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int artistID = -1;

        if(cursor.moveToFirst()){
            do{
                artistID = Integer.parseInt(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return artistID;
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
                user.setName((cursor.getString(1)));
                user.setUsername((cursor.getString(2)));
                user.setPassword((cursor.getString(3)));
                // Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    // Getting all artists' names
    public ArrayList<String> getAllArtistsNames() {
        ArrayList<String> artistNameList = new ArrayList<>();
        //Select All Query
        String selectQuery = "Select * FROM " + Artist.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String artistName = cursor.getString(1);
                artistNameList.add(artistName);
            } while (cursor.moveToNext());
        }
        return artistNameList;
    }

    public Artist getArtist(String artistName){
        String selectQuery = ("SELECT * FROM " + Artist.TABLE + " WHERE " + Artist.KEY_name + " LIKE '%" + artistName + "%';");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Artist artist = new Artist();
        if (cursor.moveToFirst()) {
            do {
                artist = new Artist();
                artist.setId(Integer.parseInt(cursor.getString(0)));
                artist.setName(cursor.getString(1));
                artist.setGenre(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return artist;
    }

    public Venue getVenue(String venueName){
        String selectQuery = ("SELECT * FROM " + Venue.TABLE + " WHERE " + Venue.KEY_name + " LIKE '%" + venueName + "%';");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Venue venue = new Venue();
        if (cursor.moveToFirst()) {
            do {
                venue = new Venue();
                venue.setId(Integer.parseInt(cursor.getString(0)));
                venue.setName(cursor.getString(1));
                venue.setAddress(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return venue;
    }

    public Event getEvent(String eventName){
        String selectQuery = ("SELECT Event._id, Event.name, Venue.name AS venue, Event.date " +
                " FROM " + Event.TABLE + " INNER JOIN Venue ON Event.venue = Venue._id " +
                " WHERE Event.name " + " LIKE '%" + eventName + "%';");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Event event = new Event();
        if (cursor.moveToFirst()) {
            do {
                event = new Event();
                event.setId(Integer.parseInt(cursor.getString(0)));
                event.setName(cursor.getString(1));
                event.setDate(cursor.getString(2));
                event.setVenue(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return event;
    }
}
