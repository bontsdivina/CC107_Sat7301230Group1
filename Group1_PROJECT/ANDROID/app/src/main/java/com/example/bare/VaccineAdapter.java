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

public class VaccineAdapter extends ArrayAdapter<Vaccined> {
    Context context;
    List<Vaccined> arrayListVaccine;


    public VaccineAdapter(@NonNull Context context, List<Vaccined> arrayListVaccine) {
        super(context, R.layout.list_feed,arrayListVaccine);

        this.context = context;
        this.arrayListVaccine = arrayListVaccine;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_feed,null,true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvDate = view.findViewById(R.id.txt_date);

        tvID.setText(arrayListVaccine.get(position).getid());
        tvDate.setText(arrayListVaccine.get(position).getDate());

        return view;
    }
}
