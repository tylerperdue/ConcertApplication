package concertApplication;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Navigation extends AppCompatActivity {

    private DrawerLayout navigation_drawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


        final ImageButton eventButton = (ImageButton) findViewById(R.id.eventsButton);
        eventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openEventActivity();
            }
        });
        final ImageButton venueButton = (ImageButton) findViewById(R.id.venuesButton);
        venueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openVenueActivity();
            }
        });
        final ImageButton artistButton = (ImageButton) findViewById(R.id.artistsButton);
        artistButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openArtistActivity();
            }
        });
        final ImageButton searchButton = (ImageButton) findViewById(R.id.searchMenuButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSearchActivity();
            }
        });
        final ImageButton userProfileButton = (ImageButton) findViewById(R.id.userProfileButton);
        userProfileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openProfileActivity();
            }
        });

        mToolbar = (Toolbar) findViewById(R.id.navigation_action);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigation_drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, navigation_drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        navigation_drawer.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_profile:
                        openProfileActivity();
                        break;
                    case R.id.nav_search:
                        openSearchActivity();
                        break;
                    case R.id.nav_events:
                        openEventActivity();
                        break;
                    case R.id.nav_venues:
                        openVenueActivity();
                        break;
                    case R.id.nav_artists:
                        openArtistActivity();
                        break;
                    case R.id.nav_logout:
                        finish();
                        openLoginActivity();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openProfileActivity(){
        final int loggedInUserID = getIntent().getIntExtra("loggedInUser", 0);
        Intent myIntent = new Intent(Navigation.this, Profile.class);
        myIntent.putExtra("loggedInUser", loggedInUserID);
        Navigation.this.startActivity(myIntent);
    }
    private void openSearchActivity(){
        final int loggedInUserID = getIntent().getIntExtra("loggedInUser", 0);
        Intent myIntent = new Intent(Navigation.this, Search.class);
        myIntent.putExtra("loggedInUser", loggedInUserID);
        Navigation.this.startActivity(myIntent);
    }
    private void openEventActivity(){
        final int loggedInUserID = getIntent().getIntExtra("loggedInUser", 0);
        Intent myIntent = new Intent(Navigation.this, EventListDisplay.class);
        myIntent.putExtra("loggedInUser", loggedInUserID);
        Navigation.this.startActivity(myIntent);
    }
    private void openVenueActivity(){
        final int loggedInUserID = getIntent().getIntExtra("loggedInUser", 0);
        Intent myIntent = new Intent(Navigation.this, VenueListDisplay.class);
        myIntent.putExtra("loggedInUser", loggedInUserID);
        Navigation.this.startActivity(myIntent);
    }
    private void openArtistActivity(){
        final int loggedInUserID = getIntent().getIntExtra("loggedInUser", 0);
        Intent myIntent = new Intent(Navigation.this, ArtistListDisplay.class);
        myIntent.putExtra("loggedInUser", loggedInUserID);
        Navigation.this.startActivity(myIntent);
    }
    private void openLoginActivity(){
        Intent myIntent = new Intent(Navigation.this, Login.class);
        Navigation.this.startActivity(myIntent);
    }



}
