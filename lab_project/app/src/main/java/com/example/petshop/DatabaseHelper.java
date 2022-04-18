package com.example.petshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE = "PetShopDB";
    static final int DATABASE_VERSION=1;
    static final String TABLE_NAME="User";
    static final String TABLE_NAME1="Pets";
    static final String TABLE_NAME2="Cart";
    static final String TABLE_NAME3="Orders";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String IMAGE = "image";
    static final String NAME = "name";
    static final String PRICE = "price";
    static final String DESC = "descrip";
    static final String CATEGORY = "category";
    public DatabaseHelper(Context context) {
        super(context,DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(username TEXT NOT NULL PRIMARY KEY,password TEXT NOT NULL)");
        db.execSQL("CREATE TABLE "+TABLE_NAME1+"(image TEXT NOT NULL PRIMARY KEY,name TEXT NOT NULL,price TEXT NOT NULL,descrip TEXT NOT NULL,category TEXT NOT NULL)");
        db.execSQL("CREATE TABLE "+TABLE_NAME2+"(username TEXT NOT NULL,image TEXT NOT NULL," +
                "PRIMARY KEY(username,image),FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,FOREIGN KEY (image) REFERENCES Pets(image) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE "+TABLE_NAME3+"(orderno INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL,image TEXT NOT NULL,address TEXT NOT NULL,contact TEXT NOT NULL," +
                "FOREIGN KEY (username) REFERENCES User(username) ON DELETE CASCADE,FOREIGN KEY (image) REFERENCES Pets(image) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
        onCreate(db);
    }

    public boolean insertUser(String username,String password)
    //Donot use the name insert for method here. It will try to override default method.
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result;
        ContentValues values=new ContentValues();
        values.put(USERNAME,username);
        values.put(PASSWORD,password);
        result=db.insert(TABLE_NAME,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertPet(String image,String name,String price,String desc,String cat)
    //Donot use the name insert for method here. It will try to override default method.
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result;
        ContentValues values=new ContentValues();
        values.put(IMAGE,image);
        values.put(NAME,name);
        values.put(PRICE,price);
        values.put(DESC,desc);
        values.put(CATEGORY,cat);
        result=db.insert(TABLE_NAME1,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertOrder(String username,String image,String address,String phone)
    //Donot use the name insert for method here. It will try to override default method.
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result;
        ContentValues values=new ContentValues();
        values.put(USERNAME,username);
        values.put(IMAGE,image);
        values.put("address",address);
        values.put("contact",phone);
        result=db.insert(TABLE_NAME3,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor fetchData(String cat)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME1+" WHERE category = '"+cat+"'",null);
        return cursor;
    }
    public Cursor fetchDetail(String image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME1+" WHERE image = '"+image+"'",null);
        return cursor;
    }
    public Cursor fetchImage(String image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME1+" WHERE image = '"+image+"'",null);
        return cursor;
    }
    public Cursor fetchOrder(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME3+" WHERE username = '"+username+"'",null);
        return cursor;
    }
    public Cursor fetchNum()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT MAX (orderno) FROM "+TABLE_NAME3,null);
        return cursor;
    }
    public Cursor fetchCart(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME2+" WHERE username = '"+username+"'",null);
        return cursor;
    }
    public boolean searchUser(String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE username = '"+uname+"'",null);
        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }
    public boolean deleteOrder(int orderno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME3, "orderno = "+orderno,null) ;
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean deleteCart(String user,String image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME2, "username = ? AND image = ?",new String[]{user,image}) ;
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean searchCart(String uname,String image)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME2+" WHERE username = '"+uname+"'"+" AND image = '"+image+"'",null);
        if(cursor.moveToFirst()) {
            return false;
        }
        else {
            long result;
            ContentValues values=new ContentValues();
            values.put(USERNAME,uname);
            values.put(IMAGE,image);
            result=db.insert(TABLE_NAME2,null,values);
            if(result==-1)
                return false;
            else
                return true;
        }
    }
    public boolean CheckLoginStatus(String uname,String pwd)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE username = '"+uname+"'"+" AND password = '"+pwd+"'",null);
        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }
    public boolean searchImage(String iname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME1+" WHERE image = '"+iname+"'",null);
        if(cursor.moveToFirst())
            return true;
        else
            return false;
    }
}