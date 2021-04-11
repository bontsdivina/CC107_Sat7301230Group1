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

public class Vaccine extends AppCompatActivity {
    EditText etnameVaccine,etkind;
    String Name_Of_Vaccine,Kind,DateTime,user;
    Vaccined vac;
    ListView listView;
    VaccineAdapter adapter;
    Calendar calendar;
    ProgressDialog mProgressDialog;
    SimpleDateFormat format;
    public static ArrayList<Vaccined> VaccineArrayList = new ArrayList<>();
    private String URL2="https://baredb.000webhostapp.com/bare/retrieveVaccine.php";
    private String URL="https://baredb.000webhostapp.com/bare/insertVaccine.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);
        etnameVaccine=findViewById(R.id.etnameVaccine);
        etkind=findViewById(R.id.etkind);
        listView=findViewById(R.id.myListView);
        adapter = new VaccineAdapter(this,VaccineArrayList);
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
                builder.setTitle(VaccineArrayList.get(position).getid());
                builder.setItems(dialogItem,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),VaccineDetails.class)
                                        .putExtra("position",position));

                                break;

                            case 1:

                                deleteData(VaccineArrayList.get(position).getid());

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


    public void LogVaccine(View view) {
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");
        Name_Of_Vaccine = etnameVaccine.getText().toString().trim();
        Kind = etkind.getText().toString().trim();
        if (!Name_Of_Vaccine.equals("") && !Kind.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Vaccine.this, "Done Vaccinated!!", Toast.LENGTH_SHORT).show();
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
                    data.put("DateTime", DateTime);
                    data.put("Name_Of_Vaccine", Name_Of_Vaccine);
                    data.put("Kind", Kind);
                    data.put("user", user);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(Vaccine.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }
    }
    public void retrieveData() {

        Intent intent =getIntent();
        user = intent.getExtras().getString("user");
        mProgressDialog = new ProgressDialog(Vaccine.this);
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
                Toast.makeText(Vaccine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://baredb.000webhostapp.com/bare/deleteVaccine.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Deleted")) {
                            Toast.makeText(Vaccine.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Vaccine.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Vaccine.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void showJSON(String response) {
        VaccineArrayList.clear();
        try {

            JSONObject jsonObject = new JSONObject(response);
            String success = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("Vaccine");

            if (success.equals("1")) {


                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String Date = object.getString("DateTime");
                    String Name_Of_Vaccine = object.getString("Name_Of_Vaccine");
                    String Kind = object.getString("Kind");

                    vac  = new Vaccined(id, Date,Name_Of_Vaccine,Kind);
                    VaccineArrayList.add(vac);
                    adapter.notifyDataSetChanged();


                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void ViewAll(View view) {
        retrieveData();
    }
}