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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a,
                                    View v, int position, long id) {
                DBHandler db = new DBHandler(getBaseContext());
                Artist artist = db.getArtistWithID(position + 1);
                Intent intent = new Intent(ArtistListDisplay.this, ArtistDetail.class);
                intent.putExtra("Artist", artist);
                startActivity(intent);
            }
        });
    }
}

