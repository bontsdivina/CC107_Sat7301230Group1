package com.example.bare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home1 extends AppCompatActivity {

    private ImageButton eating,medicine,sleep,vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        eating =(ImageButton) findViewById(R.id.eating);
        medicine =(ImageButton) findViewById(R.id.medicine);
        sleep =(ImageButton) findViewById(R.id.sleep);
        vaccine =(ImageButton) findViewById(R.id.vaccine);

        eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEating();
            }
        });

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedicine();
            }
        });
        vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVaccine();
            }
        });

        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSleep();
            }
        });

    }


    public void openEating(){
        Intent intent = new Intent(this,Eating.class);
        startActivity(intent);
    }

    public void openMedicine(){
        Intent intent = new Intent(this,Medicine.class);
        startActivity(intent);
    }

    public void openVaccine(){
        Intent intent = new Intent(this,Vaccine.class);
        startActivity(intent);
    }

    public void openSleep(){
        Intent intent = new Intent(this,Sleep.class);
        startActivity(intent);
    }


    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
