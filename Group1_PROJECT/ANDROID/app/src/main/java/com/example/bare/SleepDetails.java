package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SleepDetails extends Sleep{
    TextView tvid,tvDate,tvShift;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sleep_details);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvDate = findViewById(R.id.txtDate);
        tvShift = findViewById(R.id.txtShift);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+Sleep.SleepArrayList.get(position).getid());
        tvDate.setText("Date and Time: "+Sleep.SleepArrayList.get(position).getDate());
        tvShift.setText("Sleep / Wake?  "+Sleep.SleepArrayList.get(position).getShift());

    }
}
