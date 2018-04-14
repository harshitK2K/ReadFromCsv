package com.example.android.readfromcsv.students;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.android.readfromcsv.students.studentsContract.*;

/**
 * Created by harshit on 14/4/18.
 */

public class studentsDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shelter.db";
    public static final int DATABASE_VERSION = 1;

    public studentsDbHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_STUDENTS_TABLE =  "CREATE TABLE " + studentsEntry.TABLE_NAME + " ("
                + studentsEntry._ID + " TEXT, "
                + studentsEntry.COLUMN_STUDENT_NAME + " TEXT, "
                + studentsEntry.COLUMN_STUDENT_CONTACT + " TEXT, "
                + studentsEntry.COLUMN_STUDENT_ADDRESS + " TEXT);";
        db.execSQL(SQL_CREATE_STUDENTS_TABLE);
        Log.v("studentsDbHelper"," Table has create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
