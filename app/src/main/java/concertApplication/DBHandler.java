package concertApplication;

/*
 * Created by tylerperdue on 2/9/17.
 * Last Edited by tylerperdue on 3/28/17.
 *
 * Class Description: The DBHandler class is responsible for all things database related.
 *
 * Database version needs to be changed when any modifications to hardcoded data or table changes
 * are made.
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
    private static final int DATABASE_VERSION = 22;

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
                + Venue.KEY_address + " TEXT, "
                + Venue.KEY_description + " TEXT);";

        String CREATE_TABLE_EVENT = "CREATE TABLE " + Event.TABLE + "( "
                + Event.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Event.KEY_name + " TEXT, "
                + Event.KEY_date + " TEXT, "
                + Event.KEY_description + " TEXT, "
                + Event.KEY_venue + " INTEGER, "
                + "FOREIGN KEY(" + Event.KEY_venue + ") REFERENCES " + Venue.TABLE +
                "(" + Venue.KEY_ID + "));";

        String CREATE_TABLE_ARTIST = "CREATE TABLE " + Artist.TABLE + "( "
                + Artist.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Artist.KEY_name + " TEXT, "
                + Artist.KEY_genre + " TEXT, "
                + Artist.KEY_description + " TEXT);";

        String CREATE_TABLE_EVENT_ARTIST = "CREATE TABLE event_artist(event_id INTEGER, " +
                "artist_id INTEGER, FOREIGN KEY(event_id) REFERENCES " + Event.TABLE + "("
                + Event.KEY_ID + "), FOREIGN KEY(artist_id) REFERENCES " + Artist.TABLE + "("
                + Artist.KEY_ID + "));";

        String CREATE_TABLE_FAVORITED_ARTISTS = "CREATE TABLE favorited_artists (_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, " +
                "artist_id INTEGER, FOREIGN KEY(user_id) REFERENCES " + User.TABLE + "("
                + User.KEY_ID + "), FOREIGN KEY(artist_id) REFERENCES " + Artist.TABLE + "("
                + Artist.KEY_ID + "));";


        // All hardcoded data will be intered here

        String INSERT_TEST_VENUE1 = "INSERT INTO " + Venue.TABLE + " VALUES(1, 'Theatre of Living Artists', 'Philadelphia, PA', 'The Theatre of Living Artists is a converted movie theatre with no seats dedicated to smaller musicians.');";
        String INSERT_TEST_VENUE2 = "INSERT INTO " + Venue.TABLE + " VALUES(2, 'The 930 Club', 'Washington, DC', 'The 930 Club is a medium sized venue located in the nations capital.');";
        String INSERT_TEST_VENUE3 = "INSERT INTO " + Venue.TABLE + " VALUES(3, 'The Norva', 'Norfolk, VA', 'The Norva is a performing theatre. Its name consists of an abbreviation for its location.');";
        String INSERT_TEST_VENUE4 = "INSERT INTO " + Venue.TABLE + " VALUES(4, 'Bryce Jordan Center', 'State College, PA', 'The Bryce Jordan Center (BJC) is a sports arena for a majority of Penn States indoor sporting events. The BJC is also home to several big musicians year round.');";
        String INSERT_TEST_VENUE5 = "INSERT INTO " + Venue.TABLE + " VALUES(5, 'Jiffy Lube Live', 'Bristow, VA', 'Jiffy Lube Live is a musical venue found in northern virginia. This venue hold concert events for larger artists for the DC/Maryland/Virginia area.');";
        String INSERT_TEST_VENUE6 = "INSERT INTO " + Venue.TABLE + " VALUES(6, 'Cameleon Club', 'Lancaster, PA', 'The Cameleon Club is a smaller venue located in central Pennsylvania.');";

        String INSERT_TEST_EVENT1 = "INSERT INTO " + Event.TABLE + " VALUES(1, 'Lemonade Tour', 'August 23, 2017','Lemonade Tour is Beyonces nationwide tour where she travels the country promoting her most recent album.', 1);";
        String INSERT_TEST_EVENT2 = "INSERT INTO " + Event.TABLE + " VALUES(2, 'Views Tour', 'April 10, 2017', 'Drakes newest album Views is apart of this nationwide tour primarily viewed by stadium audiences.', 2);";
        String INSERT_TEST_EVENT3 = "INSERT INTO " + Event.TABLE + " VALUES(3, 'Lalapolooza', 'May 5, 2017','Lalapolooza is a musical festival in Chicago, IL. It is one of the more dominant and popular musical festivals in the United States and accomodates hundreds of different artists.', 3);";
        String INSERT_TEST_EVENT4 = "INSERT INTO " + Event.TABLE + " VALUES(4, 'Moving On', 'May 9, 2017', 'Moving On is a concert event promoted and setup by the Student Programming Association at Penn State. The concert is performed at the end of the spring semester to reward students for their work throughout the year.', 4);";
        String INSERT_TEST_EVENT5 = "INSERT INTO " + Event.TABLE + " VALUES(5, 'Firefly', 'November 17, 2017', 'Firefly is a musical festival in Dover, Delaware. It is one of the more dominant and popular musical festivals in the United States.', 5);";
        String INSERT_TEST_EVENT6 = "INSERT INTO " + Event.TABLE + " VALUES(6, 'Superbowl', 'October, 10, 2017','The Superbowl is a sporting event featuring the best two teams in the NFL. Included with the event, is a halftime concert performed by one of the top pop artists in the industry.', 6);";

        String INSERT_TEST_ARTIST1 = "INSERT INTO " + Artist.TABLE + " VALUES(1, 'Beyonce', 'Pop', 'Beyonce is an American singer, songwriter, and actress born and raised in Houston, Texas.');";
        String INSERT_TEST_ARTIST2 = "INSERT INTO " + Artist.TABLE + " VALUES(2, 'Drake', 'Rap/Hip-Hop', 'Drake is a Canadian rapper, singer, songwriter, record producer, and actor.');";
        String INSERT_TEST_ARTIST3 = "INSERT INTO " + Artist.TABLE + " VALUES(3, 'Cold Play', 'Alternative', 'Coldplay are a British rock band formed in 1996 by lead vocalist and keyboardist Chris Martin and lead guitarist Jonny Buckland at University College London (UCL).');";
        String INSERT_TEST_ARTIST4 = "INSERT INTO " + Artist.TABLE + " VALUES(4, 'Migos', 'Rap/Hip-Hop', 'Migos is an American hip hop group formed in 2009, from Lawrenceville, Georgia. The group is composed of three rappers, known by their stage names Quavo, Takeoff and Offset.');";
        String INSERT_TEST_ARTIST5 = "INSERT INTO " + Artist.TABLE + " VALUES(5, 'The Red Hot Chili Peppers', 'Rock', 'Red Hot Chili Peppers, also sometimes shortened to The Chili Peppers or abbreviated as RHCP, are an American funk rock band formed in Los Angeles in 1983.');";
        String INSERT_TEST_ARTIST6 = "INSERT INTO " + Artist.TABLE + " VALUES(6, 'Taylor Swift', 'Pop', 'Taylor Alison Swift (born December 13, 1989) is an American singer-songwriter. One of the most popular contemporary female recording artists, she is known for narrative songs about her personal life, which has received much media attention.');";

        String INSERT_EVENT_ARTIST1 = "INSERT INTO event_artist VALUES(1, 1);";
        String INSERT_EVENT_ARTIST2 = "INSERT INTO event_artist VALUES(2, 2);";
        String INSERT_EVENT_ARTIST3 = "INSERT INTO event_artist VALUES(3, 1);";
        String INSERT_EVENT_ARTIST4 = "INSERT INTO event_artist VALUES(3, 2);";
        String INSERT_EVENT_ARTIST5 = "INSERT INTO event_artist VALUES(3, 3);";
        String INSERT_EVENT_ARTIST6 = "INSERT INTO event_artist VALUES(3, 4);";
        String INSERT_EVENT_ARTIST7 = "INSERT INTO event_artist VALUES(3, 5);";
        String INSERT_EVENT_ARTIST8 = "INSERT INTO event_artist VALUES(4, 1);";
        String INSERT_EVENT_ARTIST9 = "INSERT INTO event_artist VALUES(4, 2);";
        String INSERT_EVENT_ARTIST10 = "INSERT INTO event_artist VALUES(4, 3);";
        String INSERT_EVENT_ARTIST11 = "INSERT INTO event_artist VALUES(4, 4);";
        String INSERT_EVENT_ARTIST12 = "INSERT INTO event_artist VALUES(4, 5);";
        String INSERT_EVENT_ARTIST13 = "INSERT INTO event_artist VALUES(5, 1);";
        String INSERT_EVENT_ARTIST14 = "INSERT INTO event_artist VALUES(5, 2);";
        String INSERT_EVENT_ARTIST15 = "INSERT INTO event_artist VALUES(5, 3);";
        String INSERT_EVENT_ARTIST16 = "INSERT INTO event_artist VALUES(6, 6);";


        // Commmands to execute SQL statements created above.

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
        db.execSQL(INSERT_EVENT_ARTIST1);
        db.execSQL(INSERT_EVENT_ARTIST2);
        db.execSQL(INSERT_EVENT_ARTIST3);
        db.execSQL(INSERT_EVENT_ARTIST4);
        db.execSQL(INSERT_EVENT_ARTIST5);
        db.execSQL(INSERT_EVENT_ARTIST6);
        db.execSQL(INSERT_EVENT_ARTIST7);
        db.execSQL(INSERT_EVENT_ARTIST8);
        db.execSQL(INSERT_EVENT_ARTIST9);
        db.execSQL(INSERT_EVENT_ARTIST10);
        db.execSQL(INSERT_EVENT_ARTIST11);
        db.execSQL(INSERT_EVENT_ARTIST12);
        db.execSQL(INSERT_EVENT_ARTIST13);
        db.execSQL(INSERT_EVENT_ARTIST14);
        db.execSQL(INSERT_EVENT_ARTIST15);
        db.execSQL(INSERT_EVENT_ARTIST16);

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
        String [] columns = {Event.KEY_ID, Event.KEY_name, Event.KEY_venue, Event.KEY_date, Event.KEY_description};
        return columns;
    }

    // Class to return Venue table column names
    public String[] getVenueColumns() {
        String [] columns = {Venue.KEY_ID, Venue.KEY_name, Venue.KEY_address, Venue.KEY_description};
        return columns;
    }

    // Class to return Artist table column names
    public String[] getArtistColumns() {
        String [] columns = {Artist.KEY_ID, Artist.KEY_name, Artist.KEY_genre, Artist.KEY_description};
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

    public ArrayList<String> getAllFavoritedArtists(int userID) {
        String selectQuery = "SELECT * FROM favorited_artists WHERE user_id = " + userID + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery(selectQuery, null);
        ArrayList<String> favoritedArtists = new ArrayList<>();

        if (mCursor.moveToFirst()) {
            do {
                Artist artist = getArtist(Integer.parseInt(mCursor.getString(2)));
                favoritedArtists.add(artist.getName());
            } while (mCursor.moveToNext());
        }
        return favoritedArtists;
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

        System.out.println(values);

        long favorited_artist_id = db.insert("favorited_artists", null, values);
        db.close();
        return (int) favorited_artist_id;
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
                artist.setDescription(cursor.getString(3));
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

    public ArrayList<String> getAllEventNames() {
        ArrayList<String> eventNameList = new ArrayList<>();
        //Select All Query
        String selectQuery = "Select * FROM " + Event.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String eventName = cursor.getString(1);
                eventNameList.add(eventName);
            } while (cursor.moveToNext());
        }
        return eventNameList;
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
                artist.setDescription(cursor.getString(3));
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
                venue.setDescription(cursor.getString(3));
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
                event.setDescription(cursor.getString(3));
                event.setVenue(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return event;
    }

    public Artist getArtistWithID(int id) {
        String selectQuery = "SELECT * FROM " + Artist.TABLE + " WHERE _id = " + id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Artist artist = new Artist();
        if(cursor.moveToFirst()){
            do{
                artist = new Artist();
                artist.setId(Integer.parseInt(cursor.getString(0)));
                artist.setName(cursor.getString(1));
                artist.setGenre(cursor.getString(2));
                artist.setDescription(cursor.getString(3));
            }while(cursor.moveToNext());
        }
        return artist;
    }

    public Event getEventWithID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT Event._id, Event.name, Event.date, Event.description, Venue.name AS venue " +
                "FROM Event INNER JOIN Venue ON Event.venue = Venue._id WHERE Event._id = " + id + ";", null, null);
        Event event = new Event();
        if(cursor.moveToFirst()){
            do{
                event = new Event();
                event.setId(Integer.parseInt(cursor.getString(0)));
                event.setName(cursor.getString(1));
                event.setDate(cursor.getString(2));
                event.setDescription(cursor.getString(3));
                event.setVenue(cursor.getString(4));
            }while(cursor.moveToNext());
        }
        return event;
    }

    public Venue getVenueWithID(int id) {
        String selectQuery = "SELECT * FROM " + Venue.TABLE + " WHERE _id = " + id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Venue venue = new Venue();
        if(cursor.moveToFirst()){
            do{
                venue = new Venue();
                venue.setId(Integer.parseInt(cursor.getString(0)));
                venue.setName(cursor.getString(1));
                venue.setAddress(cursor.getString(2));
                venue.setDescription(cursor.getString(3));
            }while(cursor.moveToNext());
        }
        return venue;
    }

    public ArrayList<String> getLineup(int id) {
        String selectQuery = "SELECT * FROM event_artist WHERE event_id = " + id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> lineup = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Artist artist = getArtist(Integer.parseInt(cursor.getString(1)));
                lineup.add(artist.getName());
            }while(cursor.moveToNext());
        }
        return lineup;
    }

    public ArrayList<String> getEventsArtistIsPlayingAt(int id) {
        String selectQuery = "SELECT * FROM event_artist WHERE artist_id = " + id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> events = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Event event = getEventWithID(Integer.parseInt(cursor.getString(0)));
                events.add(event.getName());
            }while(cursor.moveToNext());
        }
        return events;
    }

    public Artist getArtistWithArtistPositionAndEventID(int event_id, int artist_position) {
        String selectQuery = "SELECT * FROM event_artist WHERE event_id = " + event_id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(artist_position);
        Artist artist = getArtistWithID(Integer.parseInt(cursor.getString(1)));
        return artist;
    }

    public Event getEventWithEventPositionAndArtistID(int artist_id, int event_position) {
        String selectQuery = "SELECT * FROM event_artist WHERE artist_id = " + artist_id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(event_position);
        Event event = getEventWithID(Integer.parseInt(cursor.getString(0)));
        return event;
    }

    public Artist getArtistWithArtistPositionAndUserID(int user_id, int artist_position) {
        String selectQuery = "SELECT * FROM favorited_artists WHERE user_id = " + user_id + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToPosition(artist_position);
        Artist artist = getArtistWithID(Integer.parseInt(cursor.getString(2)));
        return artist;
    }
}
