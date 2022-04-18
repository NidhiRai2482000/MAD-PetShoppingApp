package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.petshop.Adapters.CartAdapter;
import com.example.petshop.Adapters.MainAdapter;
import com.example.petshop.Models.CartModel;
import com.example.petshop.Models.MainModel;
import com.example.petshop.databinding.ActivityHomeBinding;
import com.example.petshop.databinding.ActivityMyCartBinding;

import java.util.ArrayList;

public class MyCart extends AppCompatActivity {
    ActivityMyCartBinding binding;
    DatabaseHelper mydb;
    ArrayList<CartModel> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new DatabaseHelper(this);
        String username;

        binding=ActivityMyCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle1=getIntent().getExtras();
        if(bundle1!=null){
            username=bundle1.getString("username");
            Cursor cursor=mydb.fetchCart(username);
            fetch(cursor,username);
        }

        CartAdapter adapter=new CartAdapter(list,this);
        binding.recycleview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recycleview.setLayoutManager(layoutManager);
    }
    public void fetch(Cursor cursor,String username)
    {
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            String image = cursor.getString(1);
            Cursor cursor1=mydb.fetchImage(image);
            if(cursor1.moveToFirst())
            {
                String imagec = cursor1.getString(0);
                String name = cursor1.getString(1);
                String price = cursor1.getString(2);
                String desc = cursor1.getString(3);
                int i=getResources().getIdentifier(imagec,"drawable",getPackageName());
                list.add(new CartModel(i,name,price,desc,username));
            }
            cursor.moveToNext();
        }
    }
}