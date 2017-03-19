package concertApplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final Button eventButton = (Button) findViewById(R.id.eventsButton);
        eventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Navigation.this, EventListDisplay.class);
                Navigation.this.startActivity(myIntent);
            }
        });
        final Button venueButton = (Button) findViewById(R.id.venuesButton);
        venueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Navigation.this, VenueListDisplay.class);
                Navigation.this.startActivity(myIntent);
            }
        });
        final Button artistButton = (Button) findViewById(R.id.artistsButton);
        artistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Navigation.this, ArtistListDisplay.class);
                Navigation.this.startActivity(myIntent);
            }
        });
        final Button searchButton = (Button) findViewById(R.id.searchMenuButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Navigation.this, Search.class);
                Navigation.this.startActivity(myIntent);
            }
        });
    }
}