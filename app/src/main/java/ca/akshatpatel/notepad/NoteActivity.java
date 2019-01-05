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


        //Get Intent after starting activity (add or update)
        Intent intent = getIntent();
        final String option = intent.getStringExtra("option");


        final DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
        final EditText name = findViewById(R.id.name);
        final EditText message = findViewById(R.id.message);
        ImageButton save = findViewById(R.id.save);


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
                    //dbHandler.updateNote(, , );
                    startActivity(new Intent(NoteActivity.this, MainActivity.class));
                    s
                }

            }
        });

    }
}
