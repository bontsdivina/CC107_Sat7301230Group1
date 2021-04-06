package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FeedDetails extends Eating {
    TextView tvid,tvDate,tvTime,tvType,tvQuantity;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_details);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvDate = findViewById(R.id.txtDate);
        tvType = findViewById(R.id.txtType);
        tvQuantity = findViewById(R.id.txtQuantity);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+Eating.FeedArrayList.get(position).getid());
        tvDate.setText("Date And Time: "+Eating.FeedArrayList.get(position).getDate());
        tvType.setText("Type Of Food: "+Eating.FeedArrayList.get(position).getType_Of_Food());
        tvQuantity.setText("Quantity: "+Eating.FeedArrayList.get(position).getQuantity());

    }
}
