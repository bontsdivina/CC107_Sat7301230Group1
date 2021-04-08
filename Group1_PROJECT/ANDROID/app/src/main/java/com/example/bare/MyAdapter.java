package com.example.bare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Feed> {

    Context context;
    List<Feed> arrayListFeed;


    public MyAdapter(@NonNull Context context, List<Feed> arrayListFeed) {
        super(context, R.layout.list_feed,arrayListFeed);

        this.context = context;
        this.arrayListFeed = arrayListFeed;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_feed,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvDate = view.findViewById(R.id.txt_date);
        TextView tvShift = view.findViewById(R.id.txtShift);

        tvID.setText(arrayListFeed.get(position).getid());
        tvDate.setText(arrayListFeed.get(position).getDate());
        tvShift.setText(arrayListFeed.get(position).getType_Of_Food());

        return view;
    }
}