package concertApplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText searchTxt = (EditText) findViewById(R.id.searchText);
        final RadioGroup searchRadios = (RadioGroup) findViewById(R.id.searchRadioGroup);
        final TextView errorMessage = (TextView) findViewById(R.id.searchErrorMessage);
        final TextView result_one = (TextView) findViewById(R.id.searchResult1);
        final TextView result_two = (TextView) findViewById(R.id.searchResult2);
        final TextView result_three = (TextView) findViewById(R.id.searchResult3);

        Button search = (Button) findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(searchRadios.getCheckedRadioButtonId() == -1){
                    errorMessage.setText("Please select a search type.");
                }else if(searchTxt.getText().toString().matches("")){
                    errorMessage.setText("Please enter search criteria.");
                }else{
                    errorMessage.setText("");
                    int searchType = searchRadios.getCheckedRadioButtonId();
                    String searchCriteria = searchTxt.getText().toString();
                    String[] results = search(searchType, searchCriteria);
                    result_one.setText(results[0]);
                    result_two.setText(results[1]);
                    result_three.setText(results[2]);
                }
            }
        });
    }

    public String[] search(int type, String criteria){
        String[] results = new String[]{};
        DBHandler db = new DBHandler(getBaseContext());
        if(type == R.id.artistsRadioButton){
            Artist artist = db.getArtist(criteria);
            results = new String[]{artist.getName(), artist.getGenre(), null};
        }else if(type == R.id.venuesRadioButton){
            Venue venue = db.getVenue(criteria);
            results = new String[]{venue.getName(), venue.getAddress(), null};
        }else if(type == R.id.eventsRadioButton){
            Event event = db.getEvent(criteria);
            results = new String[]{event.getName(), event.getDate(), event.getVenue()};
        }
        if(results[0] == null){
            System.out.println("In Loop");
            System.out.println(results);
            results = new String[]{"No results for keyword '".concat(criteria).concat("'."), null, null};
        }
        return results;
    }
}
