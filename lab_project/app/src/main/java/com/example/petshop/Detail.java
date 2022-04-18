package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Detail extends AppCompatActivity {
    ImageView image;
    TextView name,price,desc;
    Button cart,speak,order;
    TextToSpeech textToSpeech;
    DatabaseHelper mydb;
    Login login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image=(ImageView)findViewById(R.id.image);
        name=(TextView)findViewById(R.id.name);
        price=(TextView)findViewById(R.id.price);
        desc=(TextView)findViewById(R.id.desc);
        speak=(Button)findViewById(R.id.speak);
        cart=(Button)findViewById(R.id.cart);
        order=(Button)findViewById(R.id.order);
        mydb=new DatabaseHelper(this);

        int dimage=getIntent().getIntExtra("image",0);
        String dname=getIntent().getStringExtra("name");
        String dprice=getIntent().getStringExtra("price");
        String ddesc=getIntent().getStringExtra("desc");
        String username=getIntent().getStringExtra("username");
        String imagename=getResources().getResourceName(dimage);
        String[] images=imagename.split("/");

        image.setImageResource(dimage);
        name.setText(dname);
        price.setText(dprice);
        desc.setText(ddesc);

        textToSpeech=new TextToSpeech(Detail.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR)
                {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
        speak.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                String text=name.getText().toString()+". Price "+price.getText().toString()+". "+desc.getText().toString();
                textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mydb.searchCart(username,images[1]))
                    Toast.makeText(Detail.this, "Added to cart", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Detail.this, "Already in cart", Toast.LENGTH_SHORT).show();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("username",username);
                bundle.putString("image",images[1]);
                Intent intent = new Intent(Detail.this, PlaceOrder.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}