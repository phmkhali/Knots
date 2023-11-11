package com.example.knots;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mData;
    private DatabaseHelper databaseHelper;

    public CustomAdapter(Context context, List<String> data, DatabaseHelper dbHelper) {
        super(context, 0, data);
        mContext = context;
        mData = data;
        databaseHelper = dbHelper;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent, false);
        }

        TextView textView = listItem.findViewById(R.id.text);
        ImageButton delete = listItem.findViewById(R.id.button);
        textView.setText(mData.get(position));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteItem(mData.get(position));
                mData.remove(position);
                notifyDataSetChanged();
            }
        });
        return listItem;
    }
}

