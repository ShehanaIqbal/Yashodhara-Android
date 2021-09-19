package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToDemographicInfo(View view){
        Intent intent = new Intent(home.this, demographic_info.class);
        startActivity(intent);
    }

    public void goToAnthropoetry(View view){
        Intent intent = new Intent(home.this, anthropometry_prog.class);
        startActivity(intent);
    }
}