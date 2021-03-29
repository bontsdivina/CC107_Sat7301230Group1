package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Eating extends AppCompatActivity {
    EditText txtType,txtQty;
    String Type_Of_Food,Quantity;
    TextView tvStatus;
    private String URL="https://baredb.000webhostapp.com/bare/insertfeed.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating);
        txtType=findViewById(R.id.txtType);
        txtQty=findViewById(R.id.txtQty);
        tvStatus=findViewById(R.id.tvStatus);
        Type_Of_Food=Quantity="";

    }
    public void Back(View view) {
        Intent intent=new Intent(this, Home1.class);
        startActivity(intent);
    }

    public void Feed(View view) {
        Type_Of_Food = txtType.getText().toString().trim();
        Quantity = txtQty.getText().toString().trim();
        if (!Type_Of_Food.equals("") && !Quantity.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Eating.this, "Yummy !!", Toast.LENGTH_SHORT).show();
                        txtType.setText("");
                        txtQty.setText("");

                    } else if (response.equals("failure")) {
                        Toast.makeText(Eating.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
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
                    data.put("Type_Of_Food", Type_Of_Food);
                    data.put("Quantity", Quantity);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(Eating.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }
    }
    }

