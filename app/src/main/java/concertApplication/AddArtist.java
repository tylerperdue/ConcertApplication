package concertApplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ewurafua1 on 3/19/17.
 */

public class AddArtist extends AppCompatActivity{

    private TextView mUser;
    private Button mButton;
    private Spinner artistSpinner;
    private ListView favoritedArtists;
    private String artist;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final int loggedInUser = getIntent().getIntExtra("loggedInUser", 0);
        DBHandler db = new DBHandler(getBaseContext());
        User user = db.getUser(loggedInUser);

        mUser = (TextView) findViewById(R.id.userTxt);
        mUser.setText(user.getName());

        favoritedArtists = (ListView) findViewById(R.id.favArtists);
        ArrayList<Artist> favoritedArtistArray = db.getAllFavoritedArtists(loggedInUser);
        System.out.println("The array of artists: " + favoritedArtistArray);
        ArrayList<String> favoritedArtistNames = new ArrayList<>();
        Artist lastArtist = favoritedArtistArray.get(favoritedArtistArray.size());
        System.out.println("The last Artist: " + lastArtist.getName());
        for (int i = 0; i < favoritedArtistArray.size(); i++) {
            Artist artist = favoritedArtistArray.get(i);
            favoritedArtistNames.add(artist.getName());
        }
        System.out.println("The array of favorited artists names: " + favoritedArtistNames);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.favorited_artists_listview, R.id.artistNameTxt, favoritedArtistNames);
        favoritedArtists.setAdapter(adapter1);

        artistSpinner = (Spinner) findViewById(R.id.artistSpinner);
        ArrayList<String> artistNames = db.getAllArtistsNames();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_layout, R.id.txt, artistNames);
        artistSpinner.setAdapter(adapter2);
        db.close();

        artistSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Object item = artistSpinner.getItemAtPosition(position);
                artist = item.toString();
                System.out.println("The artist value selected: " + artist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        mButton = (Button) findViewById(R.id.addFavArtist);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHandler db = new DBHandler(getBaseContext());
                System.out.println("The artist value when adding: " + artist);
                db.insertFavoritedArtist(loggedInUser, artist);
            }
        });
    }
}
