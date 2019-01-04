package ca.akshatpatel.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets the new toolbar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);


        // Retrieve attributes from layout
        ImageButton newEntry = findViewById(R.id.add);
        ListView myList = findViewById(R.id.notepad_listview);


        //Set on click listener which starts the NoteActivity activity
        newEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(startNoteActivity);
            }
        });
    }
}
