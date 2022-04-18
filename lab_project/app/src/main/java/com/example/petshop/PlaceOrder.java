package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceOrder extends AppCompatActivity {
    ImageView image;
    EditText address,phone;
    Button order;
    Boolean status;
    DatabaseHelper mydb;
    boolean connected=true;
    AlarmManager alarmManager;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        image=(ImageView)findViewById(R.id.image);
        address=(EditText)findViewById(R.id.address);
        phone=(EditText)findViewById(R.id.phone);
        order=(Button)findViewById(R.id.order);
        mydb=new DatabaseHelper(this);

        Bundle bundle1=getIntent().getExtras();
        String username=bundle1.getString("username");
        String images=bundle1.getString("image");
        int id=getResources().getIdentifier(images,"drawable",getPackageName());
        image.setImageResource(id);

        order.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String orderaddress=address.getText().toString().trim();
                String orderphone=phone.getText().toString().trim();
                if(orderaddress.equals("") || orderphone.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the details!!", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidPhone(orderphone))
                {
                    Toast.makeText(PlaceOrder.this, "Enter Valid Phone Number", Toast.LENGTH_LONG).show();
                }
                else {
                    status = mydb.insertOrder(username, images,orderaddress,orderphone);
                    if (status) {
                        Toast.makeText(PlaceOrder.this, "Order Placed", Toast.LENGTH_SHORT).show();
                        address.setText("");
                        phone.setText("");

                        Cursor cursor=mydb.fetchNum();
                        cursor.moveToFirst();
                        String Orderno= String.valueOf(cursor.getInt(0));
                        Cursor cursor1=mydb.fetchDetail(images);
                        cursor1.moveToFirst();
                        String name=cursor1.getString(1);
                        String price=cursor1.getString(2);
                        ActivityCompat.requestPermissions(PlaceOrder.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
                        SmsManager mySmsManager = SmsManager.getDefault();
                        String text="YOUR ORDER IS CONFIRMED\nPet:"+name+"\nPrice:"+price+"\nOrder Number:"+Orderno+"\nDelivery Address:"+orderaddress+"\nTHANK YOU FOR SHOPPING";
                        mySmsManager.sendTextMessage(orderphone,null,text,null,null);

                        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE) ;
                        Calendar calendar=Calendar.getInstance();
                        calendar.add(Calendar.DATE,2);
                        calendar.set(Calendar.HOUR_OF_DAY,9);
                        calendar.set(Calendar.MINUTE,0);
                        calendar.set(Calendar.SECOND,0);
                        //SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        //Toast.makeText(PlaceOrder.this, f.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                        Intent alarmIntent=new Intent(PlaceOrder.this, AlarmReceiver.class);
                        alarmIntent.putExtra("noti",name+" IS OUT FOR DELIVERY TODAY");
                        int id=(int)System.currentTimeMillis();
                        PendingIntent pIntent=PendingIntent.getBroadcast(PlaceOrder.this,id,alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pIntent);
                    } else {
                        Toast.makeText(PlaceOrder.this, "Order Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean isValidPhone(final String phone)
    {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN="^\\d{10}$";
        pattern=Pattern.compile(PASSWORD_PATTERN);
        matcher=pattern.matcher(phone);
        return matcher.matches();
    }
}