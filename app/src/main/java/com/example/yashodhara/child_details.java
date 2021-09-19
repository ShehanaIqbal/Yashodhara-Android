package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class child_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_details);
    }

    public void goToHome(View view){
        Intent intent = new Intent(child_details.this, home.class);
        startActivity(intent);
    }
}