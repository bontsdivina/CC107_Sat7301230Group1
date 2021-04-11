package com.example.bare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Medicine extends AppCompatActivity {
    EditText etPrescription,etKind,etQuantity;
    String Prescription,Kind,DateTime,Quantity,user;
    Medicines med;
    ListView listView;
    ProgressDialog mProgressDialog;
    Med adapter;
    Calendar calendar;
    SimpleDateFormat format;
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
        calendar = Calendar.getInstance();
        format = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Manila"));
        DateTime =format.format(calendar.getTime());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setTitle(MedArrayList.get(position).getDate());
                builder.setItems(dialogItem,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),MedDetails.class)
                                        .putExtra("position",position));

                                break;

                            case 1:

                                deleteData(MedArrayList.get(position).getid());

                                break;

                        }



                    }
                });


                builder.create().show();


            }
        });

    }
    public void Back(View view) {
        Intent intent=new Intent(this, Home1.class);
        startActivity(intent);
    }

    public void InsertMed(View view) {
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");
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
                    data.put("DateTime", DateTime);
                    data.put("Prescription", Prescription);
                    data.put("Kind", Kind);
                    data.put("Quantity", Quantity);
                    data.put("user", user);
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
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");
        mProgressDialog = new ProgressDialog(Medicine.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();


        StringRequest request = new StringRequest(Request.Method.POST, URL2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {

                    showJSON(response);
                    mProgressDialog.dismiss();

                } else {

                    showJSON(response);
                    mProgressDialog.dismiss();


                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Medicine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user", user);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    private void showJSON(String response) {
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
    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://baredb.000webhostapp.com/bare/deleteMed.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Deleted")) {
                            Toast.makeText(Medicine.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Medicine.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Medicine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        retrieveData();
    }

    public void ViewAll(View view) {
        retrieveData();
    }
}