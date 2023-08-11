package com.ashu.todo;
import com.ashu.todo.data_save.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Object instantiation
        * */
        add_button = findViewById(R.id.add_button);



        /*
        * Add button Navigates to the add_task_activity
        * */

        /*
        * Attached to open the add task Intent*/
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this,
                        add_task_activity.class);
                startActivity(intent);
            }
        });

        data_save.save_pref(this,"my_pref","key","hello");
        Log.d("Log",data_save.get_pref(this,"my_pref","key"));



    }



}