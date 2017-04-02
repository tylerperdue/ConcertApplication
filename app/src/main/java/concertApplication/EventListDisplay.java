package concertApplication;

/*
 * Created by Ewurafua on 2/23/2017.
 *
 * Class Description: The EventListDisplay is the view for populating the events list view that
 * shows information related to events in the database.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.database.Cursor;


public class EventListDisplay extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler db = new DBHandler(getBaseContext());
        ListView list = getListView();
        String[] fromColumns = {"name", "venue", "date"};
        int[] toView = new int[] { R.id.eventName, R.id.eventVenue, R.id.eventDate };
        Cursor cursor = db.getAllEvents();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.events_listview, cursor, fromColumns, toView, 0);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a,
                                    View v, int position, long id) {
                DBHandler db = new DBHandler(getBaseContext());
                Event event = db.getEventWithID(position + 1);
                Intent intent = new Intent(EventListDisplay.this, EventDetail.class);
                intent.putExtra("Event", event);
                startActivity(intent);
            }
        });
    }
}