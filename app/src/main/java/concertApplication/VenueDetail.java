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


public class VenueDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);

        Venue venue = (Venue) getIntent().getSerializableExtra("Venue");
        TextView venueName = (TextView) findViewById(R.id.venueDetailNameTxt);
        TextView venueLocation = (TextView) findViewById(R.id.venueDetailLocationTxt);
        TextView venueDescription = (TextView) findViewById(R.id.venueDetailDescriptionTxt);
        venueName.setText(venue.getName());
        venueLocation.setText(venue.getAddress());
        venueDescription.setText(venue.getDescription());

    }
}
