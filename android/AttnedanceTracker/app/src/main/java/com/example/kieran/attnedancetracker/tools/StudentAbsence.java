package com.example.kieran.attnedancetracker.tools;

import java.io.Serializable;

import model.User;

import java.io.Serializable;

public class StudentAbsence {
    String name,id;
    int numberOfAbsences;

    public StudentAbsence(String id, String name, int numberOfAbsences)
    {
        setId(id);
        setName(name);
        setNumberOfAbsences(numberOfAbsences);
    }
    public StudentAbsence()
    {
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumberOfAbsences(int numberOfAbsences) {
        this.numberOfAbsences = numberOfAbsences;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfAbsences() {
        return numberOfAbsences;
    }
}

