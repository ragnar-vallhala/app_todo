package com.ashu.todo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;


import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




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

        String name_list = data_save.get_pref(MainActivity.this,utility._pref_main_name,utility._task_name_list_key);


        if(name_list!=null && task_array.size()==0){
            ArrayList<String> task_name = utility.hash2str(name_list);

            for (int i=0;i<task_name.size();i++){
                String task_hash = data_save.get_pref(MainActivity.this,utility._pref_main_name,utility._task_name_list_key+task_name.get(i));
                if(task_hash!=null)
                    task_array.add(new task(task_hash));
                Log.d(utility._log,Integer.toString(i));
            }

        }

        else utility.logHash(utility._log,"None*$*");



        Bundle bundle = getIntent().getBundleExtra("pass");
        if(bundle!=null){


            task parcel = bundle.getParcelable("new_task");

            if(parcel!=null){
                String new_task_hash = parcel.toString();
                task new_task = new task(new_task_hash);
                task_array.add(new_task);


                String name_list_hash = data_save.get_pref(MainActivity.this,utility._pref_main_name,utility._task_name_list_key);


                if(name_list_hash!=null){
                    name_list_hash+=new_task.get_name()+"*$*";
                }
                else name_list_hash = new_task.get_name()+"*$*";
                data_save.save_pref(MainActivity.this,utility._pref_main_name,utility._task_name_list_key,name_list_hash);

                data_save.save_pref(MainActivity.this,utility._pref_main_name,utility._task_name_list_key+new_task.get_name(),new_task.toString());

                //TODO Remove this to save data
                //data_save.clear_pref(MainActivity.this,utility._pref_main_name);
            }

        }



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                if(!(task_array.size()>0 )){

                    //TODO add item to display when the task is empty
                    return;
                }



                for(int i=0;i<task_array.size();i++){

                    render_view(i);

                }




            }
        });



        thread.run();




        /**
        * Add button Navigates to the add_task_activity
        * */

        /**
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
    private void render_view(int i){

        LinearLayout item_layout = new LinearLayout(MainActivity.this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                utility.dpToPx(MainActivity.this,60));

        layoutParams.setMargins(
                utility.dpToPx(MainActivity.this,10),
                utility.dpToPx(MainActivity.this,20),
                utility.dpToPx(MainActivity.this,10),
                utility.dpToPx(MainActivity.this,0)
        );

        item_layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int index = item_view.indexOfChild(item_layout);

                item_view.removeViewAt(index);
                data_save.remove_key(MainActivity.this,
                        utility._pref_main_name,
                        utility._task_name_list_key+task_array.get(index).get_name());

                if(index>=0 && index<task_array.size()){
                    task_array.remove(index);
                    Toast.makeText(MainActivity.this, "Completed!", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


        item_layout.setLayoutParams(layoutParams);
        item_layout.setOrientation(LinearLayout.VERTICAL);




        item_layout.setElevation(utility.dpToPx(MainActivity.this,10));

        item_layout.setBackground(getDrawable(R.drawable.item_back));





        TextView task_name = new TextView(MainActivity.this);
        LinearLayout.LayoutParams text_layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                utility.dpToPx(MainActivity.this, 40)
        );
        task_name.setLayoutParams(text_layout);

        task_name.setPadding(0, utility.dpToPx(MainActivity.this, 10), 0, 0);
        task_name.setText(task_array.get(i).get_name());
        task_name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        task_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        task_name.setTypeface(task_name.getTypeface(), Typeface.BOLD);



        LinearLayout horizontal_layout = new LinearLayout(MainActivity.this);
        LinearLayout.LayoutParams hori_layout_param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                utility.dpToPx(MainActivity.this,20)
        );
        horizontal_layout.setLayoutParams(hori_layout_param);
        horizontal_layout.setOrientation(LinearLayout.HORIZONTAL);



        TextView date = new TextView(MainActivity.this);
        LinearLayout.LayoutParams date_layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        date_layout.weight = 1.0f;

        date.setLayoutParams(date_layout);
        date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        date.setText(task_array.get(i).get_date());






        TextView priority = new TextView(MainActivity.this);
        LinearLayout.LayoutParams pri_layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        pri_layout.weight = 0.4f;

        priority.setLayoutParams(pri_layout);
        priority.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        priority.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        priority.setText(task_array.get(i).get_priority());

        horizontal_layout.addView(date);
        horizontal_layout.addView(priority);

        item_layout.addView(task_name);
        item_layout.addView(horizontal_layout);

        item_view.addView(item_layout);

    }
}