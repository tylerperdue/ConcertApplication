package concertApplication;

/**
 * Created by Ewurafua on 2/23/2017.
 */

public class Artist {
    // Labels table name
    public static final String TABLE = "Artist";

    // Labels Table Columns names
    public static final String KEY_ID = "_id";
    public static final String KEY_name = "name";
    public static final String KEY_genre = "genre";

    // Properties to help us keep data
    private int id;
    private String name;
    private String genre;


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
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

}
