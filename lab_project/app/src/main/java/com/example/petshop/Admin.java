package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    Button login;
    EditText pwd;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        login=(Button)findViewById(R.id.login);
        pwd=(EditText)findViewById(R.id.pwd);
        mydb=new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Currentuser="ADMIN";
                String Currentpassword=pwd.getText().toString().trim();
                if(mydb.CheckLoginStatus(Currentuser,Currentpassword))
                {
                    pwd.setText("");
                    Toast.makeText(Admin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Admin.this, Addpets.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Admin.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}