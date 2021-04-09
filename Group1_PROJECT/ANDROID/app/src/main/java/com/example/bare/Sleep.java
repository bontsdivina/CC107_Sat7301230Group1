package com.example.bare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Sleep extends AppCompatActivity {
    ListView listView;
    SleepAdapter adapter;
    Slept slept;
    Calendar calendar;
    SimpleDateFormat format;
    String DateTime;


    public static ArrayList<Slept> SleepArrayList = new ArrayList<>();
    private String URL="https://baredb.000webhostapp.com/bare/insertSleep.php";
    private String URL2="https://baredb.000webhostapp.com/bare/retrieveSleep.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        listView=findViewById(R.id.myListView);
        adapter = new SleepAdapter(this,SleepArrayList);
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
                builder.setTitle(SleepArrayList.get(position).getDate());
                builder.setItems(dialogItem,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),SleepDetails.class)
                                        .putExtra("position",position));

                                break;

                            case 1:

                                deleteData(SleepArrayList.get(position).getid());

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

    public void LogSleep(View view) {
        String Shift="Sleep";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Sleep.this, "Sleep Well!!", Toast.LENGTH_SHORT).show();

                    } else if (response.equals("failure")) {
                        Toast.makeText(Sleep.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
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
                    data.put("Shift", Shift);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }

    public void LogWake(View view) {
            String Shift="Wake";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Sleep.this, "Awake!!", Toast.LENGTH_SHORT).show();

                    } else if (response.equals("failure")) {
                        Toast.makeText(Sleep.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
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
                    data.put("Shift", Shift);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, URL2,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                SleepArrayList.clear();
                try{

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("Sleep");

                    if(success.equals("1")){


                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String Date = object.getString("DateTime");
                            String Shift = object.getString("Shift");

                            slept = new Slept(id,Date,Shift);
                            SleepArrayList.add(slept);
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
                Toast.makeText(Sleep.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://baredb.000webhostapp.com/bare/deleteSleep.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Deleted")) {
                            Toast.makeText(Sleep.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Sleep.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Sleep.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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