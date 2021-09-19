package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class caregiver_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_info);
    }

    public void goToMotherInfo(View view){
        Intent intent = new Intent(caregiver_info.this, motherInfo.class);
        startActivity(intent);
    }

    public void goToHome(View view){
        Intent intent = new Intent(caregiver_info.this, home.class);
        startActivity(intent);
    }
}