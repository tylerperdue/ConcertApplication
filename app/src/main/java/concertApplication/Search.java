package concertApplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText searchTxt = (EditText) findViewById(R.id.searchText);

        Button search = (Button) findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHandler db = new DBHandler((getBaseContext()));
                final ArrayList<String> resultStrings = new ArrayList<>();
                final ArrayList<Integer> resultInts = new ArrayList<>();
                Event event = db.getEvent(searchTxt.getText().toString());
                if(event.getName() != null){
                    resultStrings.add(event.getName() + " (Event)");
                    resultInts.add(event.getId());
                }
                Artist artist = db.getArtist(searchTxt.getText().toString());
                if(artist.getName() != null){
                    resultStrings.add(artist.getName() + " (Artist)");
                    resultInts.add(artist.getId());
                }
                Venue venue = db.getVenue(searchTxt.getText().toString());
                if(venue.getName() != null){
                    resultStrings.add(venue.getName() + " (Venue)");
                    resultInts.add(venue.getId());
                }
                db.close();

                ListView results = (ListView) findViewById(R.id.search_listview);

                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), R.layout.search_listview, R.id.search_name_txt, resultStrings);
                results.setAdapter(adapter);

                results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        DBHandler db = new DBHandler(getBaseContext());
                        if(resultStrings.size() == 1){
                            if(resultStrings.get(0).endsWith("(Event)")){
                                Event event = db.getEventWithID(resultInts.get(0));
                                Intent intent = new Intent(Search.this, EventDetail.class);
                                intent.putExtra("Event", event);
                                startActivity(intent);
                            }else if(resultStrings.get(0).endsWith("(Artist)")){
                                Artist artist = db.getArtistWithID(resultInts.get(1));
                                Intent intent = new Intent(Search.this, ArtistDetail.class);
                                intent.putExtra("Artist", artist);
                                startActivity(intent);
                            }else{
                                Venue venue = db.getVenueWithID(resultInts.get(2));
                                Intent intent = new Intent(Search.this, VenueDetail.class);
                                intent.putExtra("Venue", venue);
                                startActivity(intent);
                            }
                        }else if(resultStrings.size() == 2){
                            if(position == 0){
                                if(resultStrings.get(0).endsWith("(Event)")){
                                    Event event = db.getEventWithID(resultInts.get(0));
                                    Intent intent = new Intent(Search.this, EventDetail.class);
                                    intent.putExtra("Event", event);
                                    startActivity(intent);
                                }else if(resultStrings.get(0).endsWith("(Artist)")){
                                    Artist artist = db.getArtistWithID(resultInts.get(0));
                                    Intent intent = new Intent(Search.this, ArtistDetail.class);
                                    intent.putExtra("Artist", artist);
                                    startActivity(intent);
                                }else{
                                    Venue venue = db.getVenueWithID(resultInts.get(0));
                                    Intent intent = new Intent(Search.this, VenueDetail.class);
                                    intent.putExtra("Venue", venue);
                                    startActivity(intent);
                                }
                            }else{
                                if(resultStrings.get(1).endsWith("(Event)")){
                                    Event event = db.getEventWithID(resultInts.get(1));
                                    Intent intent = new Intent(Search.this, EventDetail.class);
                                    intent.putExtra("Event", event);
                                    startActivity(intent);
                                }else if(resultStrings.get(1).endsWith("(Artist)")){
                                    Artist artist = db.getArtistWithID(resultInts.get(1));
                                    Intent intent = new Intent(Search.this, ArtistDetail.class);
                                    intent.putExtra("Artist", artist);
                                    startActivity(intent);
                                }else{
                                    Venue venue = db.getVenueWithID(resultInts.get(1));
                                    Intent intent = new Intent(Search.this, VenueDetail.class);
                                    intent.putExtra("Venue", venue);
                                    startActivity(intent);
                                }
                            }
                        }else{
                            if(position == 0){
                                Event event = db.getEventWithID(resultInts.get(0));
                                Intent intent = new Intent(Search.this, EventDetail.class);
                                intent.putExtra("Event", event);
                                startActivity(intent);
                            }else if(position == 1){
                                Artist artist = db.getArtistWithID(resultInts.get(1));
                                Intent intent = new Intent(Search.this, ArtistDetail.class);
                                intent.putExtra("Artist", artist);
                                startActivity(intent);
                            }else{
                                Venue venue = db.getVenueWithID(resultInts.get(2));
                                Intent intent = new Intent(Search.this, VenueDetail.class);
                                intent.putExtra("Venue", venue);
                                startActivity(intent);
                            }
                        }
                        db.close();
                    }
                });
            }
        });

    }
}
