package com.ashu.todo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;



public class utility {


    /**
    * This class contains static functions utilised throughout the application
    *
    * 1.
    * static ArrayList<String> hash2str(String hash);
    *
    */

    /**
     * Pref Names
     * _pref_main_name  name of the main pref
     *
     * */
    static final   String _pref_main_name = "_PREF_MAIN" ;



    /**
     * _pref_main_name items
     *
     * 1. _task_names
     * 2. for each task in _task_names a new data exist containing hash version of the related task
     *
     * */

    static  final String _task_name_list_key = "_TASK_NAMES_LIST";





    static final  String _log = "test_log";

    static ArrayList<String> hash2str(String hash){


        ArrayList<String> lst=new ArrayList<>();
        String temp="";
        for (int i = 0; i < hash.length(); i++) {
            if(hash.charAt(i)=='*'){
                i+=2;
                lst.add(temp);
                temp="";
            }
            else{
                temp+=hash.charAt(i);
            }

        }

        return lst;
    }


    static  void logHash(String tag, String hash){
        ArrayList<String> lst = utility.hash2str(hash);
        for(int i=0;i<lst.size();i++){
            Log.d(tag,lst.get(i));
        }
    }



    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
