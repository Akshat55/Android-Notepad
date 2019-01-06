package ca.akshatpatel.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
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

        final DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        notes = db.getAllNotes();

        // Retrieve attributes from layout & set adapter for ListView
        ImageButton newEntry = findViewById(R.id.add);
        ListView myList = findViewById(R.id.notepad_listview);
        NotepadAdapter notepadAdapter = new NotepadAdapter(this, notes);
        myList.setAdapter(notepadAdapter);


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
                sendEditIntent(position);
            }
        });


        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.note_select_menu, popup.getMenu());
                popup.setGravity(Gravity.END);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //Gets selected title

                        switch (item.getItemId()) {
                            case R.id.menuEdit:
                                sendEditIntent(position);
                                break;
                            case R.id.menuDelete:

                                db.deleteNote(notes.get(position).getId());
                                notes.remove(position);
                                break;
                            default:
                                break;
                        }

                        return false;
                    }
                });
                popup.show();
                return true;
            }
        });


    }

    private void sendEditIntent(int position) {
        Intent updateNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
        updateNoteActivity.putExtra("option", "update");
        updateNoteActivity.putExtra("id", notes.get(position).getId());
        updateNoteActivity.putExtra("name", notes.get(position).getName());
        updateNoteActivity.putExtra("message", notes.get(position).getMessage());
        startActivity(updateNoteActivity);
    }
}
