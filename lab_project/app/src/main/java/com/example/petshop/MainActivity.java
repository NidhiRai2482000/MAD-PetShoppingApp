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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button regsignup,regsignin,admin;
    EditText reguser,regpwd;
    DatabaseHelper mydb;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        regsignup=(Button)findViewById(R.id.regsignup);
        regsignin=(Button)findViewById(R.id.regsignin);
        reguser=(EditText)findViewById(R.id.reguser);
        regpwd=(EditText)findViewById(R.id.regpwd);
        admin=(Button)findViewById(R.id.admin);
        regsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                username=reguser.getText().toString();
                password=regpwd.getText().toString();
                if(username.equals("") || password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the details!!", Toast.LENGTH_SHORT).show();
                }
                else if(mydb.searchUser(username.trim()))
                {
                    Toast.makeText(MainActivity.this, "Username Already Taken", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidPassword(password.trim()))
                {
                    Toast.makeText(MainActivity.this, "Enter Valid Password with combination of capital letters, small letters, digits and special characters", Toast.LENGTH_LONG).show();
                }
                else{
                    status = mydb.insertUser(username.trim(), password.trim());
                    if (status) {
                        Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        reguser.setText("");
                        regpwd.setText("");
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        regsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }
        });
    }
    public boolean isValidPassword(final String Password){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN="^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&*+=])(?=\\S+$).{8,}$";
        pattern=Pattern.compile(PASSWORD_PATTERN);
        matcher=pattern.matcher(Password);
        return matcher.matches();
    }
}