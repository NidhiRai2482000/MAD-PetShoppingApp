package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCart extends AppCompatActivity {
    ImageView image;
    TextView name,price,desc;
    Button cart,order;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        image=(ImageView)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.name);
        price=(TextView)findViewById(R.id.price);
        desc=(TextView)findViewById(R.id.desc);
        cart=(Button)findViewById(R.id.cart);
        order=(Button)findViewById(R.id.order);
        mydb=new DatabaseHelper(this);

        int cimage=getIntent().getIntExtra("image",0);
        String cname=getIntent().getStringExtra("name");
        String cprice=getIntent().getStringExtra("price");
        String cdesc=getIntent().getStringExtra("desc");
        String cuser=getIntent().getStringExtra("username");
        String imagename=getResources().getResourceName(cimage);
        String[] images=imagename.split("/");

        image.setImageResource(cimage);
        name.setText(cname);
        price.setText("Price: "+cprice);
        desc.setText(cdesc);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username",cuser);
                bundle.putString("image",images[1]);
                Intent intent = new Intent(ViewCart.this, PlaceOrder.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mydb.deleteCart(cuser,images[1]))
                {
                    Toast.makeText(ViewCart.this, "Removed from Cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ViewCart.this, "Failed to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}