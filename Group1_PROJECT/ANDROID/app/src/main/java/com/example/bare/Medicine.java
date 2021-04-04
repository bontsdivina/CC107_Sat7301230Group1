package com.example.bare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Medicine extends AppCompatActivity {
    EditText etPrescription,etKind,etQuantity;
    String Prescription,Kind,Quantity;
    Medicines med;
    ListView listView;
    Med adapter;
    public static ArrayList<Medicines> MedArrayList = new ArrayList<>();
    private String URL="https://baredb.000webhostapp.com/bare/insertMed.php";
    private String URL2="https://baredb.000webhostapp.com/bare/retrieveMed.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        etPrescription=findViewById(R.id.etPrescription);
        etKind=findViewById(R.id.etKind);
        etQuantity=findViewById(R.id.etQuantity);
        Prescription=Kind=Quantity="";
        listView=findViewById(R.id.myListView);
        adapter = new Med(this,MedArrayList);
        listView.setAdapter(adapter);
    }
    public void Back(View view) {
        Intent intent=new Intent(this, Home1.class);
        startActivity(intent);
    }

    public void InsertMed(View view) {
        Prescription = etPrescription.getText().toString().trim();
        Kind = etKind.getText().toString().trim();
        Quantity = etQuantity.getText().toString().trim();
        if (!Prescription.equals("") && !Kind.equals("") && !Quantity.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Medicine.this, "Get Well Soon!!", Toast.LENGTH_SHORT).show();
                        etPrescription.setText("");
                        etKind.setText("");
                        etQuantity.setText("");

                    } else if (response.equals("failure")) {
                        Toast.makeText(Medicine.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
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
                    data.put("Prescription", Prescription);
                    data.put("Kind", Kind);
                    data.put("Quantity", Quantity);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(Medicine.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }

    }
    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, URL2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                MedArrayList.clear();
                try{

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("Medicine");

                    if(success.equals("1")){


                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String Date = object.getString("DateTime");
                            String Prescription = object.getString("Prescription");
                            String Kind = object.getString("Kind");
                            String Quantity = object.getString("Quantity");

                            med = new Medicines(id,Date,Prescription,Kind,Quantity);
                            MedArrayList.add(med);
                            adapter.notifyDataSetChanged();



                        }



                    }




                }
                catch (JSONException e){
                    e.printStackTrace();
                }






            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Medicine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void ViewAll(View view) {
        retrieveData();
    }
}