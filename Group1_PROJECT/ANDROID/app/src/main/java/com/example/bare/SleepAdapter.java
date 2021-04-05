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

public class SleepAdapter extends ArrayAdapter<Slept> {
    Context context;
    List<Slept> arrayListSleep;


    public SleepAdapter(@NonNull Context context, List<Slept> arrayListSleep) {
        super(context, R.layout.list_feed,arrayListSleep);

        this.context = context;
        this.arrayListSleep = arrayListSleep;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_feed,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvDate = view.findViewById(R.id.txt_date);

        tvID.setText(arrayListSleep.get(position).getid());
        tvDate.setText(arrayListSleep.get(position).getDate());

        return view;
    }
}
