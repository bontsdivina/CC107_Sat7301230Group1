package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MedDetails extends Medicine{
    TextView tvid,tvDate,tvPrescription,tvKind,tvQuantity;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_details);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvDate = findViewById(R.id.txtDate);
        tvPrescription = findViewById(R.id.txtType);
        tvQuantity = findViewById(R.id.txtQuantity);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+Medicine.MedArrayList.get(position).getid());
        tvDate.setText("DateTime: "+Medicine.MedArrayList.get(position).getDate());
        tvPrescription.setText("Prescription: "+Medicine.MedArrayList.get(position).getPrescription());
        tvKind.setText("Kind: "+Medicine.MedArrayList.get(position).getKind());
        tvQuantity.setText("Quantity: "+Medicine.MedArrayList.get(position).getQuantity());

    }
}
