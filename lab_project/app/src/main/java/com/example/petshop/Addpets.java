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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addpets extends AppCompatActivity {
    EditText imagefile,petname,price,desc,category;
    Button add;
    DatabaseHelper mydb;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpets);

        imagefile=(EditText)findViewById(R.id.imagefile);
        petname=(EditText)findViewById(R.id.petname);
        price=(EditText)findViewById(R.id.price);
        desc=(EditText)findViewById(R.id.desc);
        category=(EditText)findViewById(R.id.category);
        add=(Button)findViewById(R.id.add) ;
        mydb=new DatabaseHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i,pn,p,d,c;
                i=imagefile.getText().toString();
                pn=petname.getText().toString();
                p=price.getText().toString();
                d=desc.getText().toString();
                c=category.getText().toString();
                if(i.equals("") || pn.equals("") || p.equals("") || d.equals("") || c.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the details!!", Toast.LENGTH_SHORT).show();
                }
                else if(mydb.searchImage(i.trim()))
                {
                    Toast.makeText(Addpets.this, "Image File Already in use", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        int i1=getResources().getIdentifier(i.trim(), "drawable", getPackageName());
                        status = mydb.insertPet(i.trim(), pn.trim(), p.trim(), d.trim(), c.trim());
                        if (status) {
                            Toast.makeText(Addpets.this, "Record Added!", Toast.LENGTH_SHORT).show();
                            imagefile.setText("");
                            petname.setText("");
                            price.setText("");
                            desc.setText("");
                            category.setText("");

                        } else {
                            Toast.makeText(Addpets.this, "Failed to add record", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(Addpets.this, "Image file not found", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}