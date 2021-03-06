package com.example.group1_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
//import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText uname,pword,NUpdate;
    Button createUser, read,update,delete;
    myDbAdapter helper,del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText)findViewById(R.id.eUName);
        pword=(EditText)findViewById(R.id.ePWord);
        NUpdate=(EditText)findViewById(R.id.newupdate);
        createUser=(Button)findViewById(R.id.add_User);
        read=(Button)findViewById(R.id.readd);
        update=(Button)findViewById(R.id.Updated);
        delete=(Button)findViewById(R.id.Deleted);

        helper =  new myDbAdapter(this);
        del =  new myDbAdapter(this);
        createUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String t1= uname.getText().toString();
                String t2= pword.getText().toString();
                if (t1.isEmpty() || t2.isEmpty()){
                    Message.message(getApplicationContext(),"Enter Username and Password");
                    // Toast.makeText(MainActivity.this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    long id = helper.insertData(t1, t2);
                    if (id < 0) {
                        Message.message(getApplicationContext(), "Add user unsuccessful!");
                        //Toast.makeText(MainActivity.this, "Add user unsuccessful!", Toast.LENGTH_SHORT).show();
                        uname.setText("");
                        pword.setText("");
                    } else {
                        Message.message(getApplicationContext(), "Add user successful!");
                        //Toast.makeText(MainActivity.this, "Add user successful!", Toast.LENGTH_SHORT).show();
                        uname.setText("");
                        pword.setText("");
                    }
                }
            }


        } );
        read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = helper.getData();
                // Message.message(this,data);
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data= uname.getText().toString();
                del.delete(data);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old= uname.getText().toString();
                String neww= NUpdate.getText().toString();

                helper.updateName(old,neww);

            }
        });

    }
}