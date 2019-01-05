package ca.akshatpatel.notepad;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class NotepadAdapter extends ArrayAdapter<Note> {
    private Activity mContext;
    private ArrayList<Note> notes;


    NotepadAdapter(Activity context, ArrayList<Note> list) {
        super(context, R.layout.notepad_list_view, list);
        this.mContext = context;
        this.notes = list;
    }


    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.mContext.getLayoutInflater();
        view = inflater.inflate(R.layout.notepad_list_view, parent, false);


        return view;
    }

}
