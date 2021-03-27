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
public class MainActivity extends AppCompatActivity {


    private EditText etUsername, etPassword;
    private String Username ,Password;
    private String URL= "https://baredb.000webhostapp.com/bare/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = Password = "";
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
    }

    public void Signup(View view) {
        Intent intent = new Intent(MainActivity.this, Signup.class);
        startActivity(intent);
    }

    public void login(View view) {
        Username=etUsername.getText().toString();
        Password=etPassword.getText().toString();
        if(!Username.equals("") && !Password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(MainActivity.this, Home1.class);
                        startActivity(intent);
                    } else if (response.equals("failure")) {
                        Toast.makeText(MainActivity.this, "Invalid Login ID/Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("Username",Username);
                    data.put("Password",Password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }
}