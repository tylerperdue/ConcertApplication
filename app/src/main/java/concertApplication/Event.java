package concertApplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ewurafua on 2/23/2017.
 *
 * Class Description: The Event class is a model class that represents event objects.
 */

public class Event implements Serializable {
    // Labels table name
    public static final String TABLE = "Event";

    // Labels Table Columns names
    public static final String KEY_ID = "_id";
    public static final String KEY_name = "name";
    public static final String KEY_date = "date";
    public static final String KEY_description = "description";
    public static final String KEY_venue = "venue";

    // Properties to help us keep data
    private int id;
    private String name;
    private String venue;
    private String date;
    private String description;

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
    public String getDescription() { return description; }
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
    public void setDescription(String description) {this.description = description; }

}
