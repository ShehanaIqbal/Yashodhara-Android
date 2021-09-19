package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        registerReceiver(new NetworkStateChecker(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        // check if login: true = home; else = login
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }

    public void goToHome(View view){
        Intent intent = new Intent(MainActivity.this, login.class);
        startActivity(intent);
    }

    private void loadData(){
        Database database = new Database(MainActivity.this);
//        database.addChild("John","Male","20/12/2020",1);
//        database.addChild("Smith","Male","20/01/2021",0.5);
//        database.addChild("Monika","Female","12/11/2020",1);
//        database.addChild("Fanny","Female","04/08/2020",1);

    }

    private void displayData(){
        Database database = new Database(MainActivity.this);
        Cursor cursor = database.readAllChildData();
        if (cursor.getCount()==0){
            Toast.makeText(MainActivity.this, "Failed to save data", Toast.LENGTH_SHORT).show();
        }else{
//        read data
        }
    }

}