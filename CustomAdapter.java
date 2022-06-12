package com.example.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Newsmodel> {

    private List<Newsmodel.Res> results;
    private Context context;
    private ArrayList<Newsmodel.Res> newslist;
    public CustomAdapter(@NonNull Context context, int resource,List<Newsmodel.Res> results ){
        super(context, resource);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View TextView, @NonNull ViewGroup parent){
        Newsmodel news = getItem(position);

        if (TextView == null) {
            TextView = LayoutInflater.from(getContext()).inflate(R.layout.row_item,
                    parent, true);
        }
        TextView heading = TextView.findViewById(R.id.heading);
        TextView description = TextView.findViewById(R.id.description);


        heading.setText(results.get(position).getHeading());
        description.setText(results.get(position).getDescription());
        return TextView;
    }
}

