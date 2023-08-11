package com.ashu.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add_task_activity extends AppCompatActivity {

    ImageView main_back;

    Calendar calendar = Calendar.getInstance();

    EditText date_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        main_back = findViewById(R.id.back_button);
        date_text = findViewById(R.id.date_text);



        /*
         * Attached to open the add task main Intent
         * */
        main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_task_activity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });


        date_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCalender();
            }
        });
    }

    private void OpenCalender(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONDAY),
                calendar.get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy", Locale.getDefault());
            date_text.setText(sdf.format(calendar.getTime()));
        }
    };
}