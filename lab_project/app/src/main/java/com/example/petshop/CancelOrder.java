package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CancelOrder extends AppCompatActivity {
    ImageView image;
    TextView orderno,address,contact;
    Button cancel;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);

        image=(ImageView)findViewById(R.id.image);
        orderno=(TextView)findViewById(R.id.orderno);
        address=(TextView)findViewById(R.id.address);
        contact=(TextView)findViewById(R.id.contact);
        cancel=(Button)findViewById(R.id.cancel);
        mydb=new DatabaseHelper(this);

        int cimage=getIntent().getIntExtra("image",0);
        String corderno=getIntent().getStringExtra("orderno");
        String caddress=getIntent().getStringExtra("address");
        String ccontact=getIntent().getStringExtra("contact");
        String imagename=getResources().getResourceName(cimage);
        String[] images=imagename.split("/");

        image.setImageResource(cimage);
        orderno.setText("Order Number: "+corderno);
        address.setText("Delivery Address: "+caddress);
        contact.setText("Contact Number: "+ccontact);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mydb.deleteOrder(Integer.parseInt(corderno)))
                {
                    Toast.makeText(CancelOrder.this, "Order Cancelled", Toast.LENGTH_SHORT).show();
                    Cursor cursor1=mydb.fetchDetail(images[1]);
                    cursor1.moveToFirst();
                    String name=cursor1.getString(1);
                    String price=cursor1.getString(2);
                    ActivityCompat.requestPermissions(CancelOrder.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
                    SmsManager mySmsManager = SmsManager.getDefault();
                    String text="YOUR ORDER IS CANCELLED\nPet:"+name+"\nPrice:"+price+"\nOrder Number:"+corderno+"\nDelivery Address:"+caddress+"\nTHANK YOU FOR SHOPPING";
                    mySmsManager.sendTextMessage(ccontact,null,text,null,null);
                }
                else {
                    Toast.makeText(CancelOrder.this, "Failed to cancel", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}