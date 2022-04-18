package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petshop.Adapters.MainAdapter;
import com.example.petshop.Adapters.OrderAdapter;
import com.example.petshop.Models.MainModel;
import com.example.petshop.Models.OrderModel;
import com.example.petshop.databinding.ActivityHomeBinding;
import com.example.petshop.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    ActivityOrdersBinding binding;
    DatabaseHelper mydb;
    ArrayList<OrderModel> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new DatabaseHelper(this);

        binding=ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle1=getIntent().getExtras();
        if(bundle1!=null){
            String username=bundle1.getString("username");
            Cursor cursor=mydb.fetchOrder(username);
            fetch(cursor,username);
        }


        OrderAdapter adapter=new OrderAdapter(list,this);
        binding.recycleview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recycleview.setLayoutManager(layoutManager);
    }
    public void fetch(Cursor cursor,String username)
    {
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            int orderno = cursor.getInt(0);
            String image = cursor.getString(2);
            String address = cursor.getString(3);
            String contact = cursor.getString(4);
            int i=getResources().getIdentifier(image,"drawable",getPackageName());
            list.add(new OrderModel(i,Integer.toString(orderno),address,contact));
            cursor.moveToNext();
        }
    }
}