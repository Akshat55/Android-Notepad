package ca.akshatpatel.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * @author Akshat Patel
 * created on 9/4/2018
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    /**
     * @param context Application Context
     */
    DatabaseHandler(Context context) {
        super(context, "NotepadDB", null, 1);
    }


    /**
     * @param db SQLiteDatabase
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notepad (id INTEGER PRIMARY KEY, name TEXT, message TEXT)");
    }


    /**
     * @param db         SQLiteDatabase
     * @param oldVersion Older version number
     * @param newVersion New Version number
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notepad");
        onCreate(db);
    }


    /**
     * @param id id for the Note Object to be deleted
     * @return boolean variable if the delete is successful or not
     */
    boolean deleteNote(long id) {

        SQLiteDatabase db = getWritableDatabase();
        String id_string = "id = '" + id + "'";

        //Returns the number of rows deleted
        int deleted = db.delete("notepad", id_string, null);
        db.close();

        return deleted > 0;
    }


    /**
     * @param name    Note name
     * @param message Note Message
     * @return boolean variable if the create is successful or not
     */
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


    /**
     * @param id      Note id
     * @param name    Note name
     * @param message Note message
     * @return boolean variable if the update is successful or not
     */
    boolean updateNote(long id, String name, String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String id_string = "id = '" + id + "'";
        contentValues.put("name", name);
        contentValues.put("message", message);
        int update = db.update("notepad", contentValues, id_string, null);

        return update > 0;
    }


    /**
     * @return ArrayList\<Note>\
     */
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
