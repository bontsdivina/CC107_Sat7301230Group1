package com.example.ovs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class RegisterActivity extends AppCompatActivity {
    private String URL="https://cc107project.000webhostapp.com/OVS/registration.php";
    EditText inputName, inputPhone, inputPassword, inputRetype, inputemail, inputSection;
    Button btnNextReg, buttonBack;
String name,email,password,retype,phone,section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.txtName);
        inputemail = findViewById(R.id.txtEmail);
        inputPassword = findViewById(R.id.txtPwd1);
        inputRetype = findViewById(R.id.txtPwd2);
        inputPhone = findViewById(R.id.txtPhone);
        inputSection = findViewById(R.id.txtSection);

        buttonBack = findViewById(R.id.btnBackReg);
    }

            public void Register(View view) {
                name = inputName.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                email = inputemail.getText().toString().trim();
                retype = inputRetype.getText().toString().trim();
                phone = inputPhone.getText().toString().trim();
                section = inputSection.getText().toString().trim();
                if (!password.equals(retype)) {
                    Toast.makeText(this, "Password Mismatch Make Sure You Enter Correct Password", Toast.LENGTH_SHORT).show();
                } else if (!name.equals("") && !email.equals("") && !password.equals("")) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                inputName.setText("");
                                inputPassword.setText("");
                                inputemail.setText("");
                                inputRetype.setText("");
                                inputPhone.setText("");
                                inputSection.setText("");
                                btnNextReg.setClickable(false);

                            } else if (response.equals("failure")) {
                                Toast.makeText(RegisterActivity.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

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
                            data.put("name", name);
                            data.put("email", email);
                            data.put("password", password);
                            data.put("retype", retype);
                            data.put("phone", phone);
                            data.put("section", section);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Fields Should not be empty", Toast.LENGTH_SHORT).show();
                }


            }
}