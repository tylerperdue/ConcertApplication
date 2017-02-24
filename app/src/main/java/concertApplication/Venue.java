package concertApplication;

/**
 * Created by Ewurafua on 2/23/2017.
 */

public class Venue {
    // Labels table name
    public static final String TABLE = "Venue";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_address = "address";

    // Properties to help us keep data
    private int id;
    private String name;
    private String address;

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
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }

}
