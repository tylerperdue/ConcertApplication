package concertApplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ewurafua on 3/19/17.
 * Last edited by Tyler on 3/28/17.
 *
 * Class Description: The Profile class is the main view for the user profile and its functions.
 */

public class Profile extends AppCompatActivity{

    private Spinner artistSpinner;
    private String artist;
    private TextView addArtistDialog;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final int loggedInUser = getIntent().getIntExtra("loggedInUser", 0);
        DBHandler db = new DBHandler(getBaseContext());
        User user = db.getUser(loggedInUser);

        TextView userTxt = (TextView) findViewById(R.id.userTxt);
        userTxt.setText(user.getName());

        ListView favoritedArtists = (ListView) findViewById(R.id.favArtists);

        ArrayList<String> favoritedArtistNames = db.getAllFavoritedArtists(loggedInUser);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.favorited_artists_listview, R.id.artistNameTxt, favoritedArtistNames);
        favoritedArtists.setAdapter(adapter1);

        artistSpinner = (Spinner) findViewById(R.id.artistSpinner);
        ArrayList<String> artistNames = db.getAllArtistsNames();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_layout, R.id.txt, artistNames);
        artistSpinner.setAdapter(adapter2);
        db.close();

        addArtistDialog = (TextView) findViewById(R.id.addArtistDialog);

        Button addArtistBtn = (Button) findViewById(R.id.addFavArtist);
        addArtistBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                artist = artistSpinner.getSelectedItem().toString();
                DBHandler db = new DBHandler(getBaseContext());
                db.insertFavoritedArtist(loggedInUser, artist);
                addArtistDialog.setText("Artist '" + artist + "' has been favorited.");
                db.close();
            }
        });
    }
}
