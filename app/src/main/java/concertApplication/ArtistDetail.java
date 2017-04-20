package concertApplication;

/*
 * Created by Tyler on 3/27/2017.
 *
 * Class Description: The EventDetail class is the view for accessing data related to a specific
 * event.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ArtistDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        final Artist artist = (Artist) getIntent().getSerializableExtra("Artist");
        TextView artistName = (TextView) findViewById(R.id.artistDetailNameTxt);
        TextView artistGenre = (TextView) findViewById(R.id.artistDetailGenreTxt);
        TextView artistDescription = (TextView) findViewById(R.id.artistDetailDescriptionTxt);

        artistName.setText(artist.getName());
        artistGenre.setText(artist.getGenre());
        artistDescription.setText(artist.getDescription());

        DBHandler db = new DBHandler(getBaseContext());
        ListView upcomingEvents = (ListView) findViewById(R.id.artistDetailEventListview);
        ArrayList<String> events = db.getEventsArtistIsPlayingAt(artist.getId());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.lineup_listview, R.id.artistInEventTxt, events);
        upcomingEvents.setAdapter(adapter);
        db.close();

        upcomingEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                DBHandler db = new DBHandler(getBaseContext());
                Event event = db.getEventWithEventPositionAndArtistID(artist.getId(), position);
                Intent intent = new Intent(ArtistDetail.this, EventDetail.class);
                intent.putExtra("Event", event);
                startActivity(intent);
                db.close();
            }
        });

    }
}
