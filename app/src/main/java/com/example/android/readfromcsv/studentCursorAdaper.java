package com.example.android.readfromcsv;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import static com.example.android.readfromcsv.students.studentsContract.*;

/**
 * Created by harshit on 14/4/18.
 */

public class studentCursorAdaper extends CursorAdapter {
    public studentCursorAdaper(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView id = (TextView) view.findViewById(R.id.id);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView contact = (TextView) view.findViewById(R.id.contact);

        id.setText(cursor.getString(cursor.getColumnIndex(studentsEntry.COLUMN_STUDENT_ID)));
        name.setText(cursor.getString(cursor.getColumnIndex(studentsEntry.COLUMN_STUDENT_NAME)));
        contact.setText(cursor.getString(cursor.getColumnIndex(studentsEntry.COLUMN_STUDENT_CONTACT)));


    }
}
