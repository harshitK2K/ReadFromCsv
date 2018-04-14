package com.example.android.readfromcsv.students;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.android.readfromcsv.students.studentsContract.*;

/**
 * Created by harshit on 14/4/18.
 */

public class studentsProvider extends ContentProvider {

    private studentsDbHelper mDbHelper;
    private static final int students = 100;
    private static final int students_id = 101;
    private static final UriMatcher sUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String LOG_TAG = studentsProvider.class.getSimpleName();

    static{

        sUrimatcher.addURI(CONTENT_AUTHORITY, PATH_STUDENTS,students);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new studentsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = null;

        int match = sUrimatcher.match(uri);

        switch (match){

            case students:
                cursor = db.query(studentsEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                try {
                    throw new IllegalAccessException("No match found for given query" + uri);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Log.v("studentsProvider" , " hi checkin ");
        ContentValues values = contentValues;
        String id = values.getAsString(studentsEntry.COLUMN_STUDENT_ID);
        if(id.length() == 0){
            throw new IllegalArgumentException("Students requires a Id");
        }
        String name = values.getAsString(studentsEntry.COLUMN_STUDENT_NAME);
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Students requires a name");
        }

        // Check that the gender is valid
        String contact = values.getAsString(studentsEntry.COLUMN_STUDENT_CONTACT);

        // If the weight is provided, check that it's greater than or equal to 0 kg
        String address = values.getAsString(studentsEntry.COLUMN_STUDENT_ADDRESS);

        int match = sUrimatcher.match(uri);
        switch(match){

            case students:
                return insertStudent(uri,contentValues);
            default:
                throw new IllegalArgumentException("No Match Foud With " + uri);
        }
    }

    private Uri insertStudent(Uri uri,ContentValues contentValues){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long id = db.insert(studentsEntry.TABLE_NAME,null,contentValues);
        if(id == -1){
            Log.v(LOG_TAG, "Failed to insert row for " + uri);
        }
        else
            getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(studentsEntry.CONTENT_URI,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
