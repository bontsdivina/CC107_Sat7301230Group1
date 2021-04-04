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

public class Med extends ArrayAdapter<Medicines> {
    Context context;
    List<Medicines> arrayListMed;


    public Med(@NonNull Context context, List<Medicines> arrayListMed) {
        super(context, R.layout.list_feed,arrayListMed);

        this.context = context;
        this.arrayListMed = arrayListMed;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_feed,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvDate = view.findViewById(R.id.txt_date);

        tvID.setText(arrayListMed.get(position).getid());
        tvDate.setText(arrayListMed.get(position).getDate());

        return view;
    }
}
