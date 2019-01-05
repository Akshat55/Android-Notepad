package ca.akshatpatel.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    DatabaseHandler(Context context) {
        super(context, "NotepadDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notepad (id INTEGER PRIMARY KEY, name TEXT, message TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notepad");
        onCreate(db);
    }

    boolean deleteNote(long id) {

        SQLiteDatabase db = getWritableDatabase();
        String id_string = "id = '" + id + "'";

        //Returns the number of rows deleted
        int deleted = db.delete("notepad", id_string, null);
        db.close();

        return deleted > 0;
    }


    boolean addNote(String name, String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("message", message);

        //Returns id if successful, -1 if not
        long added = db.insert("notepad", null, contentValues);
        db.close();

        return added != -1;
    }


    boolean updateNote(long id, String name, String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String id_string = "id = '" + id + "'";
        contentValues.put("name", name);
        contentValues.put("message", message);
        int update = db.update("notepad", contentValues, id_string, null);

        return update > 0;
    }


    ArrayList<Note> getAllNotes() {

        ArrayList<Note> itemArray = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notepad", null);
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getInt(0);
                String name = cursor.getString(1);
                String message = cursor.getString(2);
                Note note = new Note(id, name, message);
                itemArray.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itemArray;
    }

}
