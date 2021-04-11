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


public class Eating extends AppCompatActivity{
    EditText txtType,txtQty;
    MyAdapter adapter;
    String user,DateTime,Type_Of_Food,Quantity;
    SimpleDateFormat format;
    Calendar calendar;
    ProgressDialog mProgressDialog;
    ListView listView;
    public static ArrayList<Feed> FeedArrayList = new ArrayList<>();
    private String URL="https://baredb.000webhostapp.com/bare/insertfeed.php";
    private String URL2="https://baredb.000webhostapp.com/bare/retrieve.php";
    Feed feed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating);
        txtType=findViewById(R.id.txtType);
        txtQty=findViewById(R.id.txtQty);
        Type_Of_Food=Quantity="";
        listView=findViewById(R.id.myListView);
        adapter = new MyAdapter(this,FeedArrayList);
        listView.setAdapter(adapter);
        calendar = Calendar.getInstance();
        format = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Manila"));
        DateTime =format.format(calendar.getTime());
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setTitle(FeedArrayList.get(position).getDate());
                builder.setItems(dialogItem,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),FeedDetails.class)
                                        .putExtra("position",position));

                                break;

                            case 1:

                                deleteData(FeedArrayList.get(position).getid());

                                break;

                        }



                    }
                });


                builder.create().show();


            }
        });




    }
    public void retrieveData(){
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");
        mProgressDialog = new ProgressDialog(Eating.this);
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
                Toast.makeText(Eating.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        FeedArrayList.clear();
        try {

            JSONObject jsonObject = new JSONObject(response);
            String success = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("Feed");

            if (success.equals("1")) {


                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String Date = object.getString("DateTime");
                    String Type_Of_Food = object.getString("Type_Of_Food");
                    String Quantity = object.getString("Quantity");

                    feed = new Feed(id, Date, Type_Of_Food, Quantity);
                    FeedArrayList.add(feed);
                    adapter.notifyDataSetChanged();


                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://baredb.000webhostapp.com/bare/delete.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Data Deleted")) {
                            Toast.makeText(Eating.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Eating.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Eating.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    data.put("DateTime", DateTime);
                    data.put("Type_Of_Food", Type_Of_Food);
                    data.put("Quantity", Quantity);
                    data.put("user", user);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(Eating.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }
    }


    public void ViewAll(View view) {
        retrieveData();
    }
}


