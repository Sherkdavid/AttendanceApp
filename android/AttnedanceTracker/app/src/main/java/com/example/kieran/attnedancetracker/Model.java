package com.example.kieran.attnedancetracker;

/**
 * Created by Kieran on 20/03/2017.
 */

public class Model{
    String name;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    Model(String name, int value){
        this.name = name;
        this.value = value;
    }
    public String getName(){
        return this.name;
    }
    public int getValue(){
        return this.value;
    }

}