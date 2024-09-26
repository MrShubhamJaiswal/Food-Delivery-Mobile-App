package com.example.fooddeliveryapp.Model;

public class LocationModel {
    private int id;
    private String Loc;
    
    public LocationModel() {
    }
    
    @Override
    public String toString() {
        return  Loc;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getLoc() {
        return Loc;
    }
    
    public void setLoc(String loc) {
        Loc = loc;
    }
}
