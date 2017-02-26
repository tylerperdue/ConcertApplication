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
        String[] fromColumns = db.getEventColumns();
        int[] toView = new int[] { android.R.layout.simple_list_item_1 };

        Cursor cursor = db.getAllEvents();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.activity_results, cursor, fromColumns, toView, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
    }
}