package com.ashu.todo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import android.widget.Toast;
import com.ashu.todo.task;
import com.ashu.todo.utility;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class add_task_activity extends AppCompatActivity {



    ImageView main_back;

    Calendar calendar = Calendar.getInstance();

    EditText date_text;

    Button add_button;

    EditText task_name;

    Spinner priority;
    ArrayList<task> taskArrayList;




    private static  final String[] Priority = {"I","II","III"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        main_back = findViewById(R.id.back_button);
        date_text = findViewById(R.id.date_text);
        add_button = findViewById(R.id.add_button);
        task_name = findViewById(R.id.task_name);
        priority = findViewById(R.id.priority);


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


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hash="";
                String temp_task_name = task_name.getText().toString();
                if(temp_task_name.equals("")) {
                    Toast.makeText(add_task_activity.this, "Enter task name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                hash+=(temp_task_name+"*$*");


                temp_task_name = date_text.getText().toString();
                if(temp_task_name.equals("")) {
                    Toast.makeText(add_task_activity.this, "Enter date!", Toast.LENGTH_SHORT).show();
                    return;
                }



                hash+=temp_task_name;


                hash = spinnerOut(hash);

                hash+="0"+"*$*";

                task new_task = new task(utility.hash2str(hash));


                /**@Used_Code
                 *
                 * data_save.save_pref(add_task_activity.this,_pref_main_name,task_name.getText().toString(),hash);

                data_save.save_pref(add_task_activity.this,
                        _pref_main_name,
                        task._pref_name,
                        task_name.getText().toString()+"*$*"+data_save.get_pref(add_task_activity.this,_pref_main_name,task._pref_name));

                */
                Intent main_intent = new Intent(add_task_activity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("new_task", new_task);
                main_intent.putExtra("pass",bundle);
                Toast.makeText(add_task_activity.this, "Added", Toast.LENGTH_SHORT).show();
                startActivity(main_intent);



            }
        });




        ArrayAdapter<String> priority_adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,Priority);
        priority.setAdapter(priority_adapter);

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        //TODO implement additional logic for ending of form page
    }




    private  String spinnerOut(String str){
        str+="*$*"+priority.getSelectedItem().toString();
        return str+"*$*";
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

    private final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(year,month,dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
            date_text.setText(sdf.format(calendar.getTime()));





            /**
             * Log.d("Log",Boolean.toString((calendar.getTime().before(Calendar.getInstance().getTime()))));
             * Toast.makeText(add_task_activity.this, sdf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
             *
             * */

        }
    };
}