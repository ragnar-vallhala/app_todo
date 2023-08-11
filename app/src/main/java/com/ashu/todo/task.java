package com.ashu.todo;


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
public class task {
    private String _name=null;
    private String _date = null;
    private String _priority;
    private Boolean _status = null;

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
        return
                _name + "*$*" + _date + "*$*" + _priority +"*$*"+ _status ;

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
}
