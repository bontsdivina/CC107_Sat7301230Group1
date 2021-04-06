package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VaccineDetails extends Vaccine {
    TextView tvid,tvDate,tvName,tvKind;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_details);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvDate = findViewById(R.id.txtDate);
        tvName = findViewById(R.id.txtName);
        tvKind = findViewById(R.id.txtKind);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+Vaccine.VaccineArrayList.get(position).getid());
        tvDate.setText("Date and Time: "+Vaccine.VaccineArrayList.get(position).getDate());
        tvName.setText("Name Of Vaccine: "+Vaccine.VaccineArrayList.get(position).getName_Of_Vaccine());
        tvKind.setText("Kind: "+Vaccine.VaccineArrayList.get(position).getKind());

    }
}

