package com.example.group1_age;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;



public class MainActivity extends AppCompatActivity {


    EditText firstname;
    EditText lastname;
    EditText inputyear;
    EditText inputday;
    EditText inputmonth;
    Button buttoninput;
    TextView outputAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
        inputday = (EditText)findViewById(R.id.inputday);
        inputmonth =(EditText) findViewById(R.id.inputmonth);
        inputyear =(EditText) findViewById(R.id.inputyear);
        buttoninput=(Button)findViewById(R.id.buttoninput);
        outputAge=(TextView)findViewById(R.id.outputAge);
        //public fname:
        //  String fname="";
        // String lname="";


        buttoninput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String yearnumber= inputyear3.getText().toString().trim();
                String fname= firstname.getText().toString();
                String lname= lastname.getText().toString();

                int yn=Integer.parseInt(inputyear.getText().toString());
                int year= Calendar.getInstance().get(Calendar.YEAR);
                //int year=2021;
                int age=year-yn;


                // String name=setText(firstname,lastname);

                //  if (age<=0){
                // Toast.makeText( MainActivity.this,"Please enter your year", Toast.LENGTH_SHORT).show();
                // }
                if(age<18) {
                    // Toast.makeText(MainActivity.this,"You can Vote", Toast.LENGTH_SHORT).show();
                    //outputAge.setText(toString(lastname+", "+firstname+"You can Vote"));
                    Toast.makeText(MainActivity.this, fname+" "+lname+"  "+"Cannot Vote", Toast.LENGTH_SHORT).show();
                    //outputAge.setText(String.valueOf(lname+", "+fname+","+"You can Vote"));
                }
                //final TextView tv = (TextView) findViewById(R.id.bar); String name = ...; int age = ...; String city = ...; tv.setText(getString(R.string.foo, name, age, city));
                //final TextView tv = (TextView) findViewById(R.id.bar);
                //String name = ...;
                //int age = ...;
                //String city = ...;
                //tv.setText(getString(R.string.foo, name, age, city));
                //mhbcount.setText(String.valueOf(variableValue));

                else{
                    // Toast.makeText(MainActivity.this, "You cannot Vote", Toast.LENGTH_SHORT).show();
                    //outputAge.setText(lastname+", "+firstname+"You cannot Vote".toString());
                    Toast.makeText(MainActivity.this,fname+" "+lname+"  "+"Can Vote", Toast.LENGTH_SHORT).show();
                    //outputAge.setText(String.valueOf(lname+", "+fname+","+"You cannot Vote"));

                }

            }
        });

    }
}