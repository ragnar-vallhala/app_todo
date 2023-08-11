package com.ashu.todo;

import java.util.ArrayList;



public class utility {


    /**
    * This class contains static functions utilised throughout the application
    *
    * 1.
    * static ArrayList<String> hash2str(String hash);
    *
    */



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

}
