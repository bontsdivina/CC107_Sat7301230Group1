package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Home1 extends AppCompatActivity {

    private ImageButton eating,medicine,sleep,vaccine;
String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        eating =(ImageButton) findViewById(R.id.eating);
        medicine =(ImageButton) findViewById(R.id.medicine);
        sleep =(ImageButton) findViewById(R.id.sleep);
        vaccine =(ImageButton) findViewById(R.id.vaccine);
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");


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
        startActivity(new Intent(getApplicationContext(),Eating.class)
                .putExtra("user",user));
    }

    public void openMedicine(){
        startActivity(new Intent(getApplicationContext(),Medicine.class)
                .putExtra("user",user));
    }

    public void openVaccine(){
        startActivity(new Intent(getApplicationContext(),Vaccine.class)
                .putExtra("user",user));
    }

    public void openSleep(){
        startActivity(new Intent(getApplicationContext(),Sleep.class)
                .putExtra("user",user));
    }


    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
