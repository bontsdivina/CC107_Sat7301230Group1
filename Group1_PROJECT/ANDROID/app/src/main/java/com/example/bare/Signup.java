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


public class Signup extends AppCompatActivity {
    Button cancel, btnRegister;
    EditText etName,etPassword,etEmail,etReenterPassword;
    String name,email,password,reenterPassword;
    TextView tvStatus;
    private String URL="https://baredb.000webhostapp.com/bare/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etReenterPassword=findViewById(R.id.etReenterPassword);
        tvStatus=findViewById(R.id.tvStatus);
        btnRegister=findViewById(R.id.btnRegister);
        cancel=(Button) findViewById(R.id.cancel);
        name=email=password=reenterPassword="";



    }


    public void Register(View view) {
        name = etName.getText().toString();
        password = etPassword.getText().toString();
        email = etEmail.getText().toString();
        reenterPassword = etReenterPassword.getText().toString();
        if (!password.equals(reenterPassword)) {
            Toast.makeText(this, "Password Mismatch Make Sure You Enter Correct Password", Toast.LENGTH_SHORT).show();
        } else if (!name.equals("") && !email.equals("") && !password.equals("")) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(Signup.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        etName.setText("");
                        etEmail.setText("");
                        etPassword.setText("");
                        etReenterPassword.setText("");
                        btnRegister.setClickable(false);

                    } else if (response.equals("failure")) {
                        Toast.makeText(Signup.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
                        btnRegister.setClickable(false);
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
                    data.put("Username", name);
                    data.put("Password", password);
                    data.put("Email", email);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(Signup.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
        }
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public void cancel(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
