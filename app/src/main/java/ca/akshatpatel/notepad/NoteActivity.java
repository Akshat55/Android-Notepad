package ca.akshatpatel.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * @author Akshat Patel
 * created on 9/4/2018
 */
public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //Initialization
        final DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        final EditText name = findViewById(R.id.name);
        final EditText message = findViewById(R.id.message);
        ImageButton save = findViewById(R.id.save);


        //Get Intent after starting activity (add or update)
        final Intent intent = getIntent();
        final String option = intent.getStringExtra("option");

        //Set the text for the EditText if the option retrieved is "update"
        if (option.equals("update")) {
            //Set the values
            name.setText(intent.getStringExtra("name"));
            message.setText(intent.getStringExtra("message"));
        }


        //Set on click listener for the save button toolbar
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check if either name or message has value
                if (name.getText().toString().isEmpty() && message.getText().toString().isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Name and message are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if you create a new Note or Update an existing note and go back to main activity
                if (option.equals("add")) {
                    dbHandler.addNote(name.getText().toString(), message.getText().toString());
                    Toast.makeText(NoteActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));
                } else {
                    long receivedId = intent.getLongExtra("id", -1);
                    dbHandler.updateNote(receivedId, name.getText().toString(), message.getText().toString());
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));

                }
            }
        });

    }
}
