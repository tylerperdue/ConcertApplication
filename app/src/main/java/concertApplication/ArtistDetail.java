package concertApplication;

/*
 * Created by Tyler on 3/27/2017.
 *
 * Class Description: The EventDetail class is the view for accessing data related to a specific
 * event.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ArtistDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        Artist artist = (Artist) getIntent().getSerializableExtra("Artist");
        TextView artistName = (TextView) findViewById(R.id.artistDetailNameTxt);
        TextView artistGenre = (TextView) findViewById(R.id.artistDetailGenreTxt);
        TextView artistDescription = (TextView) findViewById(R.id.artistDetailDescriptionTxt);
        artistName.setText(artist.getName());
        artistGenre.setText(artist.getGenre());
        artistDescription.setText(artist.getDescription());

    }
}
