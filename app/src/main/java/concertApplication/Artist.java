package concertApplication;

import java.io.Serializable;

/**
 * Created by Ewurafua on 2/23/2017.
 *
 * Class Description: The Artist class is a model class that represents artist objects.
 */

public class Artist implements Serializable {
    // Labels table name
    public static final String TABLE = "Artist";

    // Labels Table Columns names
    public static final String KEY_ID = "_id";
    public static final String KEY_name = "name";
    public static final String KEY_genre = "genre";
    public static final String KEY_description = "description";

    // Properties to help us keep data
    private int id;
    private String name;
    private String genre;
    private String description;


    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
    public String getDescription(){ return description; }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setDescription(String description) { this.description = description; }

}
