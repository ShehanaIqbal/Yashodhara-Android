package com.example.yashodhara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class motherInfo extends AppCompatActivity {

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    ImageView motherDobPick;
    EditText motherDob;

    ImageView motherEduLevelPick;
    EditText motherEduLevel;

    ImageView occupationPick;
    EditText occupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mother_info);

        motherDob=findViewById(R.id.mt_dob);
        motherDobPick=findViewById(R.id.mt_dob_pick);

        motherEduLevel=findViewById(R.id.edu_level_mother);
        motherEduLevelPick=findViewById(R.id.edu_level_mother_pick);

        occupation=findViewById(R.id.occup_mother);
        occupationPick=findViewById(R.id.occup_mother_pick);

        motherEduLevelPick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickPickFromDropDown(view,R.menu.menu_edu_level, motherEduLevelPick,motherEduLevel);
                    }
                }
        );

        occupationPick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickPickFromDropDown(view,R.menu.menu_occupation, occupationPick,occupation);
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

    public void onClickPickBirthday(View view){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(motherInfo.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        motherDob.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void goToDemographicInfo(View view){
        Intent intent = new Intent(motherInfo.this, demographic_info.class);
        startActivity(intent);
    }

    public void goToCareGiverInfo(View view){
        Intent intent = new Intent(motherInfo.this, caregiver_info.class);
        startActivity(intent);    }
}
