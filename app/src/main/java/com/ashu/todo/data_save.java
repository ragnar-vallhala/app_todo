package com.ashu.todo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class data_save {

    public static void save_pref(Context context, String pref_name,String key, String value){

        SharedPreferences sharedPreferences = context.getSharedPreferences(pref_name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();

    }


    public static void clear_pref(Context context, String pref_name){

        SharedPreferences sharedPreferences = context.getSharedPreferences(pref_name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }

    public static String get_pref(Context context,String pref_name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static Map<String, ?> getAllPrefs(Context context, String pref_name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();

        return allEntries;
    }

    public static void remove_key(Context context, String pref_name, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();

    }



}
