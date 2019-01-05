package ca.akshatpatel.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notes;

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
                Intent addNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
                addNoteActivity.putExtra("option", "add");
                startActivity(addNoteActivity);
            }
        });


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent updateNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
                updateNoteActivity.putExtra("option", "update");
                startActivity(updateNoteActivity);
            }
        });
    }
}
