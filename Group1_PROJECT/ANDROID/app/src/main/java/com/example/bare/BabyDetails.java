package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BabyDetails extends BabyInf {
    TextView tvid,tvName, tvBday, tvBtype, tvGender, tvWeight, tvHeight, tvBplace;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baby_info);
        tvid = findViewById(R.id.txtid);
        tvName = findViewById(R.id.txtBname);
        tvBday = findViewById(R.id.txtBday);
        tvBtype = findViewById(R.id.txtBtype);
        tvGender = findViewById(R.id.txtGender);
        tvWeight = findViewById(R.id.txtWeight);
        tvHeight = findViewById(R.id.txtHeight);
        tvBplace = findViewById(R.id.txtBplace);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+BabyInf.BabyArrayList.get(position).getid());
        tvName.setText("Name: " + BabyInf.BabyArrayList.get(position).getname());
        tvBday.setText("Birthday: " + BabyInf.BabyArrayList.get(position).getbday());
        tvBtype.setText("Blood type: " + BabyInf.BabyArrayList.get(position).getBtype());
        tvGender.setText("Gender: " + BabyInf.BabyArrayList.get(position).getgender());
        tvWeight.setText("Weight: " + BabyInf.BabyArrayList.get(position).getweight());
        tvHeight.setText("Height: " + BabyInf.BabyArrayList.get(position).getheight());
        tvBplace.setText("Birth Place: " + BabyInf.BabyArrayList.get(position).getbplace());
    }
}
