package com.raytech.android_studio_recyclerview_kullanimi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//dbhelper yazsının sonuna extendes ve sqliteopenhelper ekledim
//daha sonrasında aşağıda bulunan public yazısına tıklayıp impelement methods dedim
//çıkan ekranda her iki seçeneği de seçtim
//daha sonrasında hemen aşağıda bulunan @ovveride yazsının üstünde boş bir yere sağ tıkladım
//Generate yazısına tıkladım ve constructora tıkladım ve çıkan ekranda 3 seçenekten en üstekini seçtim
//gelen hazır kodun üzerindeki @nullable yazılarını sıldım ve sadece context context yazısı kaldı

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {

//aşağıda default olarak name,factory,version yazıyor name kısmına veritabanı adını ekledim factory null ve version 1

        super(context, "userData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //veritabanı tablolarımızı oluşturmak için gerekli kod yazıldı
        DB.execSQL("create Table userDetails(name Text primary key,email text,age text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        //tablodan daha önce varsa silinmesi için
        DB.execSQL("drop table if exists userDetails");
    }

    //main activityden dbhelpere erişmemiz için veri ekleme metodumuz
    public Boolean insertUserData(String name, String email, String age) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("age", age);
        Long result = DB.insert("userDetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    //main activityden dbhelpere erişmemiz ve verileri listelememiz için metodumuz
    public Cursor getData(){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cursor= DB.rawQuery("Select * From userDetails",null);
        return  cursor;
    }
}
