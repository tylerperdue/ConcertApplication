package concertApplication;

import java.util.ArrayList;

/**
 * Created by Ewurafua on 2/23/2017.
 */

public class Event {
    // Labels table name
    public static final String TABLE = "Event";

    // Labels Table Columns names
    public static final String KEY_ID = "_id";
    public static final String KEY_name = "name";
    public static final String KEY_date = "date";
    public static final String KEY_venue = "venue";

    // Properties to help us keep data
    private int id;
    private String name;
    private String venue;
    private String date;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getVenue() {
        return venue;
    }
    public String getDate() {
        return date;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
