package concertApplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ewurafua on 2/23/2017.
 *
 * Class Description: The ArtistListDisplay is the view for populating the artists list view that
 * shows information related to artists in the database.
 */


public class ArtistListDisplay extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBHandler db = new DBHandler(getBaseContext());
        Cursor cursor = db.getAllArtists();
        db.close();
        String[] fromColumns = {Artist.KEY_name, Artist.KEY_genre};
        int[] toView = new int[] { R.id.artistName, R.id.artistGenre };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.artists_listview, cursor, fromColumns, toView, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
    }
}

