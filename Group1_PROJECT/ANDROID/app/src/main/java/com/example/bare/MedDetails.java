package com.example.bare;

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
        tvPrescription = findViewById(R.id.txtPrescription);
        tvKind = findViewById(R.id.txtKind);
        tvQuantity = findViewById(R.id.txtQuantity);

        tvid.setText("ID: "+Medicine.MedArrayList.get(position).getid());
        tvDate.setText("DateTime: "+Medicine.MedArrayList.get(position).getDate());
        tvPrescription.setText("Prescription: "+Medicine.MedArrayList.get(position).getPrescription());
        tvKind.setText("Kind: "+Medicine.MedArrayList.get(position).getKind());
        tvQuantity.setText("Quantity: "+Medicine.MedArrayList.get(position).getQuantity());

    }
}
