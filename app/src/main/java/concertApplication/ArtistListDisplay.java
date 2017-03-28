package concertApplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ewurafua on 2/23/2017.
 */


public class ArtistListDisplay extends ListActivity {
    // Array of strings...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //later change concertApplication.DBHandler.db.HEY_username to column name of events in database
        DBHandler db = new DBHandler(getBaseContext());
        String[] fromColumns = {Artist.KEY_name, Artist.KEY_genre};
        int[] toView = new int[] { R.id.artistName, R.id.artistGenre };

        Cursor cursor = db.getAllArtists();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.artists_listview, cursor, fromColumns, toView, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
    }
}

