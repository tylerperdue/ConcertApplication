package concertApplication;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/*
 * Created by Ewurafua on 2/23/2017.
 *
 * Class Description: The ArtistListDisplay is the view for populating the artists list view that
 * shows information related to artists in the database.
 */


public class VenueListDisplay extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler db = new DBHandler(getBaseContext());
        String[] fromColumns = {Venue.KEY_name, Venue.KEY_address};
        int[] toView = new int[] { R.id.venueName, R.id.venueLocation };

        Cursor cursor = db.getAllVenues();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.venues_listview, cursor, fromColumns, toView, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a,
                                    View v, int position, long id) {
                DBHandler db = new DBHandler(getBaseContext());
                Venue venue = db.getVenueWithID(position + 1);
                Intent intent = new Intent(VenueListDisplay.this, VenueDetail.class);
                intent.putExtra("Venue", venue);
                startActivity(intent);
            }
        });
    }
}