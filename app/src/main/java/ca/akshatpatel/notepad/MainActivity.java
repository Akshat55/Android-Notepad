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

/**
 * @author Akshat Patel
 * created on 9/4/2018
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets the new toolbar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        //Retrieve the notes from the database & set the ListView adapter
        final DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        notes = db.getAllNotes();
        ImageButton newEntry = findViewById(R.id.add);
        ListView myList = findViewById(R.id.notepad_listview);
        final NotepadAdapter notepadAdapter = new NotepadAdapter(this, notes);
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


        //On item click (SHORT) in list view start note activity
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendEditIntent(position);
            }
        });


        //Set the on item long click listener to allow deletion
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                //Inflate view
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
                                notepadAdapter.notifyDataSetChanged();
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


    /**
     * @param position position of the item clicked on.
     *                 Starts Note Update activity.
     */
    private void sendEditIntent(int position) {
        Intent updateNoteActivity = new Intent(MainActivity.this, NoteActivity.class);
        updateNoteActivity.putExtra("option", "update");
        updateNoteActivity.putExtra("id", notes.get(position).getId());
        updateNoteActivity.putExtra("name", notes.get(position).getName());
        updateNoteActivity.putExtra("message", notes.get(position).getMessage());
        startActivity(updateNoteActivity);
    }
}
