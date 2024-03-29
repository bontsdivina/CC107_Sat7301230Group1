package com.example.bare;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class BabyAdap extends ArrayAdapter<BabyInfAdapter> {
    Context context;
    List<BabyInfAdapter> arrayListBaby;


    public BabyAdap(@NonNull Context context, List<BabyInfAdapter> arrayListBaby) {
        super(context, R.layout.list_baby,arrayListBaby);

        this.context = context;
        this.arrayListBaby = arrayListBaby;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_baby,null,true);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txtName);


        tvID.setText(arrayListBaby.get(position).getid());
        tvName.setText(arrayListBaby.get(position).getname());

        return view;
    }
}
