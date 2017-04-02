package concertApplication;

/*
 * Created by Tyler on 3/27/2017.
 *
 * Class Description: The EventDetail class is the view for accessing data related to a specific
 * event.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class EventDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Event event = (Event) getIntent().getSerializableExtra("Event");
        TextView eventName = (TextView) findViewById(R.id.eventDetailNameTxt);
        TextView eventDate = (TextView) findViewById(R.id.eventDetailDateTxt);
        TextView eventVenue = (TextView) findViewById(R.id.eventDetailVenueTxt);
        TextView eventDescription = (TextView) findViewById(R.id.eventDetailDescriptionTxt);

        eventName.setText(event.getName());
        eventDate.setText(event.getDate());
        eventVenue.setText(event.getVenue());
        eventDescription.setText(event.getDescription());

        DBHandler db = new DBHandler(getBaseContext());
        ListView lineup = (ListView) findViewById(R.id.eventDetailLineupListview);
        ArrayList<String> artistLineup = db.getLineup(event.getId());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.lineup_listview, R.id.artistInEventTxt, artistLineup);
        lineup.setAdapter(adapter);

    }
}
