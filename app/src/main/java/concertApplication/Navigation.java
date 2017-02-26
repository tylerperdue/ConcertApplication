package concertApplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import concertApplication.R;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final ImageButton eventImageButton = (ImageButton) findViewById(R.id.imageButton7);
        eventImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(Navigation.this, EventListDisplay.class);
                Navigation.this.startActivity(myIntent);
            }
        });
    }

    public void onClickResults()
    {
        Intent intent = new Intent(this, EventListDisplay.class);
        startActivity(intent);
    }
}
