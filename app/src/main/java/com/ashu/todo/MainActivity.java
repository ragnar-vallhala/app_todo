package com.ashu.todo;
import com.ashu.todo.data_save.*;

import com.ashu.todo.add_task_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Context context;
    FloatingActionButton add_button;

    LinearLayout item_view;

    static ArrayList<task> task_array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        * Object instantiation
        * */
        add_button = findViewById(R.id.add_button);
        item_view = findViewById(R.id.item_view);



        Bundle bundle = getIntent().getBundleExtra("pass");
        if(bundle!=null){


            task parcel = bundle.getParcelable("new_task");

            if(parcel!=null){
                Toast.makeText(MainActivity.this, "Ran", Toast.LENGTH_SHORT).show();
                String new_task_hash = parcel.toString();
                task new_task = new task(new_task_hash);
                task_array.add(new_task);

            }

        }



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                if(!(task_array.size()>0)){

                    //TODO add item to display when the task is empty
                    return;
                }




                for(int i=0;i<task_array.size();i++){

                    LinearLayout item_layout = new LinearLayout(MainActivity.this);
                    item_layout.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    ));
                    item_layout.setOrientation(LinearLayout.VERTICAL);
                    item_layout.setPadding(
                            10,
                            20,
                            10,
                            0
                    );

                    item_layout.setBackground(getDrawable(R.drawable.item_back));
                    item_layout.setElevation(10);

                    item_view.addView(item_layout);










//                    LinearLayout layout;
//                    layout = new LinearLayout(MainActivity.this);
//                    layout.setLayoutParams(new ViewGroup.LayoutParams(
//                            LinearLayout.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT));
//                    layout.setOrientation(LinearLayout.VERTICAL);
//                    TextView textView = new TextView(MainActivity.this);
//                    textView.setText(task_array.get(i).toString());
//                    layout.addView(textView);
//                    item_view.addView(layout);
                }



            }
        });

        thread.run();




        /**
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










    }

}