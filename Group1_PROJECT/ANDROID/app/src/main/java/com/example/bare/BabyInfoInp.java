package com.example.bare;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
public class BabyInfoInp extends AppCompatActivity {
    Button btnSave;
    EditText etBabyName, etPlace, etBday, etBlood, etGender, etWeight, etHeight;
    String name, bplace, bday, btype, gender, height, weight;
    private String URL = "https://baredb.000webhostapp.com/bare/babyinf.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baby_info_input);
        etBabyName = findViewById(R.id.etBabyName);
        etPlace = findViewById(R.id.etPlace);
        etBday = findViewById(R.id.etBday);
        etBlood = findViewById(R.id.etBlood);
        etGender = findViewById(R.id.etGender);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btnSave = findViewById(R.id.btnSave);
        name=bplace= bday=btype=gender=height=weight= "";

    }

    public void save(View view) {
        name = etBabyName.getText().toString();
        bplace = etPlace.getText().toString();
        btype = etBlood.getText().toString();
        gender = etGender.getText().toString();
        bday = etBday.getText().toString();
        height = etHeight.getText().toString();
        weight = etWeight.getText().toString();
        if (!name.equals("") && !bplace.equals("") && !btype.equals("") && !gender.equals("") && !bday.equals("") && !height.equals("") && !weight.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(BabyInfoInp.this, "Saved", Toast.LENGTH_SHORT).show();
                        etBabyName.setText("");
                        etPlace.setText("");
                        etBlood.setText("");
                        etGender.setText("");
                        etBday.setText("");
                        etHeight.setText("");
                        etWeight.setText("");
                        btnSave.setClickable(false);

                    } else if (response.equals("failure")) {
                        Toast.makeText(BabyInfoInp.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                        btnSave.setClickable(false);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Baby_Name", name);
                    data.put("Birthday", bday);
                    data.put("Blood_Type", btype);
                    data.put("Gender", gender);
                    data.put("Weight", weight);
                    data.put("Height", height);
                    data.put("Birth_Place", bplace);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(BabyInfoInp.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }

    }
}

