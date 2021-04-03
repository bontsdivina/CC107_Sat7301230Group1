package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Vaccine extends AppCompatActivity {
    EditText etnameVaccine,etkind;
    String Name_Of_Vaccine,Kind;
    private String URL="https://baredb.000webhostapp.com/bare/insertVaccine.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        etnameVaccine=findViewById(R.id.etnameVaccine);
        etkind=findViewById(R.id.etkind);
    }
    public void Back(View view) {
        Intent intent=new Intent(this, Home1.class);
        startActivity(intent);
    }


    public void LogVaccine(View view) {
        Name_Of_Vaccine = etnameVaccine.getText().toString().trim();
        Kind = etkind.getText().toString().trim();
        if (!Name_Of_Vaccine.equals("") && !Kind.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Vaccine.this, "Done Vaccinating!!", Toast.LENGTH_SHORT).show();
                        etnameVaccine.setText("");
                        etkind.setText("");

                    } else if (response.equals("failure")) {
                        Toast.makeText(Vaccine.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
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
                    data.put("Name_Of_Vaccine", Name_Of_Vaccine);
                    data.put("Kind", Kind);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(Vaccine.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }
    }
}