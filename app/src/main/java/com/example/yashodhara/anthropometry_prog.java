package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

public class anthropometry_prog extends AppCompatActivity {

    Calendar calendar;
    DatePickerDialog datePickerDialog;
    EditText date;
    ImageButton datePick;
    EditText dateOfReg;
    ImageButton dateOfRegPick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anthropometry_prog);

        date = findViewById(R.id.an_date);
        dateOfReg=findViewById(R.id.an_date_of_reg);

        datePick=findViewById(R.id.an_date_pick);
        dateOfRegPick=findViewById(R.id.an_reg_date_pick);

        datePick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickPickDate(view, date);
                    }
                }
        );

        dateOfRegPick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickPickDate(view,dateOfReg);
                    }
                }
        );

    }

    public void goToHome(View view){
        Intent intent = new Intent(anthropometry_prog.this, anthropometric_measures.class);
        startActivity(intent);
    }

    public void onClickPickDate(View view, EditText editText){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(anthropometry_prog.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        editText.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
}