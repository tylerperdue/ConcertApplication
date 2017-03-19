package concertApplication;

/**
 * Created by Ewurafua on 2/23/2017.
 */

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.database.Cursor;


public class EventListDisplay extends ListActivity {
    // Array of strings...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //later change concertApplication.DBHandler.db.HEY_username to column name of events in database
        DBHandler db = new DBHandler(getBaseContext());
//        String[] fromColumns = {Event.KEY_name, Event.KEY_venue, Event.KEY_date};
        String[] fromColumns = {"name", "venue", "date"};
        int[] toView = new int[] { R.id.eventName_entry, R.id.venue_entry, R.id.date_entry };

        Cursor cursor = db.getAllEvents();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_results_events, cursor, fromColumns, toView, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
    }
}