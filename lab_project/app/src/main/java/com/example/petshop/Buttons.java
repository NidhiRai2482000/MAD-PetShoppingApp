package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Buttons extends AppCompatActivity {
    Button dogs,cats,birds,rabbits,orders,cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        String username;
        dogs=(Button)findViewById(R.id.dogs);
        cats=(Button)findViewById(R.id.cats);
        birds=(Button)findViewById(R.id.birds);
        rabbits=(Button)findViewById(R.id.rabbits);
        orders=(Button)findViewById(R.id.orders);
        cart=(Button)findViewById(R.id.cart);
        Bundle bundle1=getIntent().getExtras();
        username=bundle1.getString("username");
        dogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Dogs");
                bundle.putString("username",username);
                Intent intent = new Intent(Buttons.this, Home.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Cats");
                bundle.putString("username",username);
                Intent intent = new Intent(Buttons.this, Home.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        birds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Birds");
                bundle.putString("username",username);
                Intent intent = new Intent(Buttons.this, Home.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        rabbits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("category","Rabbits");
                bundle.putString("username",username);
                Intent intent = new Intent(Buttons.this, Home.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Intent intent = new Intent(Buttons.this, Orders.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                Intent intent = new Intent(Buttons.this, MyCart.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}