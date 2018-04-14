package com.example.android.readfromcsv;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.readfromcsv.students.studentsDbHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import static com.example.android.readfromcsv.students.studentsContract.*;


public class MainActivity extends AppCompatActivity {

    private Button readFromCsv;
    private Button save;
    private Button display;
    private ArrayList<student> arrayList = new ArrayList<>();
    private studentsDbHelper mDbHelper = new studentsDbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFromCsv = findViewById(R.id.readData);
        display = findViewById(R.id.display);
        readFromCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    readFromCsv();
                }catch (Exception e){
                    Log.v("MainActivity",e.getMessage());
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,showDatabase.class);
                startActivity(intent);
            }
        });
    }
    private void insertStudent(String id,String name,String contact,String address){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(studentsEntry.COLUMN_STUDENT_ID,id);
        values.put(studentsEntry.COLUMN_STUDENT_NAME,name);
        values.put(studentsEntry.COLUMN_STUDENT_CONTACT,contact);
        values.put(studentsEntry.COLUMN_STUDENT_ADDRESS,address);

        Uri val = getContentResolver().insert(studentsEntry.CONTENT_URI,values);
        if(val == null) {
            Toast.makeText(this,"add Fail", Toast.LENGTH_SHORT).show();
        }
        Log.v("MainActivity","HI  New Row Id" + val);

    }
    private void readFromCsv() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line;
        reader.readLine();
        while( (line = reader.readLine()) != null){
            student st = new student();
            String[] tokens = line.split(",");
            st.setId(tokens[0]);
            st.setName(tokens[1]);
            if(tokens[2].length() == 0)
                st.setContact("Not availalbe");
            else
                st.setContact(tokens[2]);
            if(tokens[3].length() == 0)
                st.setAddress("Not available");
            else
                st.setAddress(tokens[3]);
            arrayList.add(st);
            insertStudent(tokens[0],tokens[1],tokens[2],tokens[3]);
        }
        Toast.makeText(this,"Data Has saved in Database",Toast.LENGTH_SHORT).show();

    }
}
