package com.raytech.android_studio_recyclerview_kullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //Tasarım elamanlarımızı çağırıyoruz bu kısımda
    EditText name, email, age;
    Button insert, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tasarım elamanlarımızı tanımlıyoruz
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        age = findViewById(R.id.age);
        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnView);

        //veritabanı classını çağırmak için
        DB = new DBHelper(this);


        //View yani veri görüntüleme butonuna tıklandığında userlist ekranına gitmesi için gerekli kod
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, userList.class));
            }
        });


        //veri ekle buttonuna bastığımızda dbhelperden çektiğimiz bilgilerle veri eklemek için kod satırımız
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String emailTxt = email.getText().toString();
                String ageTxt = age.getText().toString();

                //alanlarda boş değer varsa izin vermemesi için
                if (nameTxt.isEmpty() || emailTxt.isEmpty() || ageTxt.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Boş Değer Gönderemezsiniz Kontrol Ediniz.", Toast.LENGTH_LONG).show();
                    return;
                }

                Boolean checkInsertData = DB.insertUserData(nameTxt, emailTxt, ageTxt);
                if (checkInsertData == true ) {
                    Toast.makeText(MainActivity.this, "Veri Eklendi", Toast.LENGTH_SHORT).show();

                    //veri eklendikten sonra alanların temizlenmesi için
                    name.setText("");
                    email.setText("");
                    age.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this, "Hata Veri Eklenemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}