package com.example.petshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.database.Cursor;
import com.example.petshop.Adapters.MainAdapter;
import com.example.petshop.Models.MainModel;
import com.example.petshop.databinding.ActivityHomeBinding;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;

import com.example.petshop.Adapters.MainAdapter;
import com.example.petshop.Models.MainModel;
import com.example.petshop.databinding.ActivityHomeBinding;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    ActivityHomeBinding binding;
    DatabaseHelper mydb;
    Cursor cursor;
    String user;
    ArrayList<MainModel> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb=new DatabaseHelper(this);
        String category,username;

        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle1=getIntent().getExtras();
        if(bundle1!=null){
            category=bundle1.getString("category");
            username=bundle1.getString("username");
            Cursor cursor=mydb.fetchData(category);
            fetch(cursor,username);
        }

        MainAdapter adapter=new MainAdapter(list,this);
        binding.recycleview.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recycleview.setLayoutManager(layoutManager);
    }
    public void fetch(Cursor cursor,String username)
    {
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            String image = cursor.getString(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String desc = cursor.getString(3);
            int i=getResources().getIdentifier(image,"drawable",getPackageName());
            list.add(new MainModel(i,name,price,desc,username));
            cursor.moveToNext();
        }
    }
}