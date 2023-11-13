package com.example.knots;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    EditText entry;
    TextView emptyText;
    ImageButton add, delete;
    ImageView cat;
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
        cat = findViewById(R.id.cat);
        displayData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        entry.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    add();
                    return true;
                }
                return false;
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

    public void add() {
        text = String.valueOf(entry.getText());
        databaseHelper.addToDatabase(text);
        entry.setText("");
        displayData();
    }

    private void displayData() {
        List<String> data = databaseHelper.getAllData();
        CustomAdapter adapter = new CustomAdapter(this, data, databaseHelper);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        emptyText = findViewById(R.id.empty_text);
        if (data.isEmpty()) {
            listView.setVisibility(View.GONE);
            cat.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            cat.setVisibility(View.GONE);
            emptyText.setVisibility(View.GONE);
        }
    }
}