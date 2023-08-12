package com.ashu.todo;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
* Task class has following member variables
 * 1. String _name
 * 2. String _date
 * 3. String _priority
 * 4. Boolean _status
 *
 *
 *
 *
 * Following Methods are contained in the class
 *
 * Constructor
 * public task(String _name, String _date, String _priority, Boolean _finished)
 *
 *
 * Getters and Setters for every field
 *
 *
 * */
public class task implements Parcelable {

    static  final String _pref_name = "_TASK_";
    private String _name=null;
    private String _date = null;
    private String _priority;
    private Boolean _status = null;

    public static final Creator<task> CREATOR = new Creator<task>() {
        @Override
        public task createFromParcel(Parcel in) {
            return new task(in);
        }

        @Override
        public task[] newArray(int size) {
            return new task[size];
        }
    };

    public String get_name() {
        return _name;
    }

    public String get_date() {
        return _date;
    }

    public String get_priority() {
        return _priority;
    }

    public Boolean get_finished() {
        return _status;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    @Override
    public String toString() {
        String stat;
        if(_status)
            stat = "1";
        else stat="0";

        return
                _name + "*$*" + _date + "*$*" + _priority +"*$*"+ stat+"*$*" ;

    }

    public void set_priority(String _priority) {
        this._priority = _priority;
    }

    public void set_finished(Boolean _finished) {
        this._status = _finished;
    }

    public task(String _name, String _date, String _priority, Boolean _finished) {
        this._name = _name;
        this._date = _date;
        this._priority = _priority;
        this._status = _finished;
    }

    public task(String _name, String _date, String _priority, String _finished) {
        this._name = _name;
        this._date = _date;
        this._priority = _priority;
        this._status = _finished.equals("1");
    }

    public task(ArrayList<String> lst){
        this._name = lst.get(0);
        this._date = lst.get(1);
        this._priority =lst.get(2);
        this._status = lst.get(3).equals("1");
    }

    public  task(String str){
        this(utility.hash2str(str));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(_name);
        dest.writeString(_date);
        dest.writeString(_priority);

        String temp_stat;
        if(_status) temp_stat="1";
        else temp_stat="0";


        dest.writeString(temp_stat);
    }

    public task(Parcel in){
        this._name = in.readString();
        this._date = in.readString();
        this._priority = in.readString();

        String temp_stat = in.readString();

        this._status = temp_stat.equals("1");
    }
}
