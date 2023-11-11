package com.example.knots;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    EditText entry;
    ImageButton add, delete;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        add = findViewById(R.id.bearbutton);
        entry = findViewById(R.id.entry);
        delete = findViewById(R.id.delete);
        databaseHelper = new DatabaseHelper(this);
        displayData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = String.valueOf(entry.getText());
                addToDatabase(text);
                entry.setText("");
                displayData();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteAllData();
                displayData();
            }
        });
    }

    private void addToDatabase(String text) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("text", text);
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

    private void displayData() {
        List<String> data = databaseHelper.getAllData();
        CustomAdapter adapter = new CustomAdapter(this, data, databaseHelper);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
