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
    private ArrayList<Artist> lineup;
    private Date date;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ArrayList getLineup() {
        return lineup;
    }
    public Date getDate() {
        return date;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLineup(ArrayList<Artist> lineup) {
        this.lineup = lineup;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
