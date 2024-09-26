package com.example.fooddeliveryapp.Model;

public class TimeModel {
    private int id;
    private String Value;
    
    public TimeModel() {
    }
    
    @Override
    public String toString() {
        return Value;
    }
    
    public String getValue() {
        return Value;
    }
    
    public void setValue(String value) {
        Value = value;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}

