package concertApplication;

import java.io.Serializable;

/**
 * Created by Ewurafua on 2/23/2017.
 */

public class Venue implements Serializable {
    // Labels table name
    public static final String TABLE = "Venue";

    // Labels Table Columns names
    public static final String KEY_ID = "_id";
    public static final String KEY_name = "name";
    public static final String KEY_address = "address";
    public static final String KEY_description = "description";

    // Properties to help us keep data
    private int id;
    private String name;
    private String address;
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getDescription() { return description; }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setDescription(String description ) { this.description = description; }

}
