package com.example.android.readfromcsv.students;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by harshit on 14/4/18.
 */

public final class studentsContract {

    public static final String CONTENT_AUTHORITY = "com.example.android.students";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_STUDENTS = "students";

    public static class studentsEntry implements BaseColumns {

        public static final String TABLE_NAME = "students";
        public static final String COLUMN_STUDENT_ID =  "_id";
        public static final String COLUMN_STUDENT_NAME = "name";
        public static final String COLUMN_STUDENT_CONTACT = "contact";
        public static final String COLUMN_STUDENT_ADDRESS = "address";


        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_STUDENTS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STUDENTS;

    }
}
