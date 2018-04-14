package com.example.android.readfromcsv;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import static com.example.android.readfromcsv.students.studentsContract.*;


public class showDatabase extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView listView;
    studentCursorAdaper adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        listView = findViewById(R.id.list);
        Cursor cursor = null;
        adapter = new studentCursorAdaper(this,cursor);
        listView.setAdapter(adapter);
        getLoaderManager().initLoader(0,null,this);


    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String projection[] = {
                studentsEntry.COLUMN_STUDENT_ID,
                studentsEntry.COLUMN_STUDENT_NAME,
                studentsEntry.COLUMN_STUDENT_CONTACT
        };

        return new CursorLoader(this,
                studentsEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

            adapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }
}
