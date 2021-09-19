package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.Calendar;

public class demographic_info extends AppCompatActivity {

    EditText dateOfReg;
    Calendar calendar;
    DatePickerDialog datePickerDialog;

    ImageView genderPick;
    EditText gender;

    EditText dateOfBirth;

    ImageView ethnicityPick;
    EditText ethnicity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demographic_info);

        dateOfReg=findViewById(R.id.date_of_reg);

        genderPick =findViewById(R.id.sex_pick);
        gender=findViewById(R.id.sex);

        dateOfBirth=findViewById(R.id.date_of_birth);

        ethnicityPick=findViewById(R.id.ethnicity_pick);
        ethnicity=findViewById(R.id.ethnicity);

        genderPick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickPickFromDropDown(view,R.menu.menu_gender, genderPick,gender);
                    }
                }
        );

        ethnicityPick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickPickFromDropDown(view,R.menu.menu_ethnicity, ethnicityPick,ethnicity);
                    }
                }
        );


    }

    public void onClickPickFromDropDown(View view, int menu, ImageView btn, EditText editText){
        PopupMenu dropDownMenu = new PopupMenu(getApplicationContext(), btn);
        dropDownMenu.getMenuInflater().inflate(menu, dropDownMenu.getMenu());
        dropDownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                editText.setText(menuItem.getTitle());
                return true;
            }
        });
        dropDownMenu.show();
    }

    public void onClickPickDate(View view){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(demographic_info.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateOfReg.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void onClickPickBirthday(View view){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(demographic_info.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void goToHome(View view){
        Intent intent = new Intent(demographic_info.this, home.class);
        startActivity(intent);
    }

    public void goToMotherInfo(View view){
        Intent intent = new Intent(demographic_info.this, motherInfo.class);
        startActivity(intent);
    }
}