package ca.akshatpatel.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //Initialization
        String recievedName, recievedMessage;

        final DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        final EditText name = findViewById(R.id.name);
        final EditText message = findViewById(R.id.message);
        ImageButton save = findViewById(R.id.save);


        //Get Intent after starting activity (add or update)
        final Intent intent = getIntent();
        final String option = intent.getStringExtra("option");

        if (option.equals("update")) {
            recievedName = intent.getStringExtra("name");
            recievedMessage = intent.getStringExtra("message");

            //Set the values
            name.setText(recievedName);
            message.setText(recievedMessage);

        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if either name or message has value
                if (name.getText().toString().isEmpty() && message.getText().toString().isEmpty()) {
                    Toast.makeText(NoteActivity.this, "Name and message are empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if you create a new Note or Update an existing
                if (option.equals("add")) {
                    dbHandler.addNote(name.getText().toString(), message.getText().toString());
                    Toast.makeText(NoteActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));
                } else {
                    long recievedId = intent.getLongExtra("id", -1);
                    dbHandler.updateNote(recievedId, name.getText().toString(), message.getText().toString());
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));

                }

            }
        });

    }
}
