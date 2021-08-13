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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BabyInf extends AppCompatActivity{
    ListView listView;
    BabyInfAdapter babyadapter;
    BabyAdap babyAdt;
    String user;
    ProgressDialog mProgressDialog;

    public static ArrayList<BabyInfAdapter> BabyArrayList = new ArrayList<>();
    private String URL="https://baredb.000webhostapp.com/bare/retreiveBaby.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby);
        babyAdt = new BabyAdap(this,BabyArrayList);
        Intent intent =getIntent();
        user = intent.getExtras().getString("user");
        listView=findViewById(R.id.myListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"View Data"};
                builder.setTitle(BabyArrayList.get(position).getname());
                builder.setItems(dialogItem,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),BabyDetails.class)
                                        .putExtra("position",position));

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
        mProgressDialog = new ProgressDialog(BabyInf.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();
    StringRequest request = new StringRequest(Request.Method.POST, URL,new Response.Listener<String>() {
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
            Toast.makeText(BabyInf.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        BabyArrayList.clear();
        try{

            JSONObject jsonObject = new JSONObject(response);
            String success = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("BabyInf");

            if(success.equals("1")){


                for(int i=0;i<jsonArray.length();i++){

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String Baby_Name = object.getString("Baby_Name");
                    String Birthday = object.getString("Birthday");
                    String Blood_Type = object.getString("Blood_Type");
                    String Gender = object.getString("Gender");
                    String Weight = object.getString("Weight");
                    String Height = object.getString("Height");
                    String Birth_Place = object.getString("Birth_Place");


                    babyadapter = new BabyInfAdapter(id,Baby_Name,Birthday,Blood_Type,Gender,Weight,Height,Birth_Place);
                    BabyArrayList.add(babyadapter);

                    babyAdt.notifyDataSetChanged();

                }



            }




        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }


    public void ViewAll(View view) {
        retrieveData();
    }
}
