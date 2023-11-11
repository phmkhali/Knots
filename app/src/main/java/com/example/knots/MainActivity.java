package com.example.knots;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
                databaseHelper.addToDatabase(text);
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
    private void displayData() {
        List<String> data = databaseHelper.getAllData();
        CustomAdapter adapter = new CustomAdapter(this, data, databaseHelper);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
