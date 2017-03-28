package concertApplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Ewurafua on 2/23/2017.
 */


public class VenueListDisplay extends ListActivity {
    // Array of strings...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //later change concertApplication.DBHandler.db.HEY_username to column name of events in database
        DBHandler db = new DBHandler(getBaseContext());
        String[] fromColumns = {Venue.KEY_name, Venue.KEY_address};
        int[] toView = new int[] { R.id.venueName, R.id.venueLocation };

        Cursor cursor = db.getAllVenues();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.venues_listview, cursor, fromColumns, toView, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
    }
}
