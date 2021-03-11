package com.example.bare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signup,home1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup =(Button) findViewById(R.id.signup);
        home1 =(Button) findViewById(R.id.login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignup();

            }
        });

        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Home();
            }
        });
    }
    public void openSignup(){
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);
    }

    public void Home(){
        Intent intentt = new Intent(this,Home1.class);
        startActivity(intentt);
    }
}