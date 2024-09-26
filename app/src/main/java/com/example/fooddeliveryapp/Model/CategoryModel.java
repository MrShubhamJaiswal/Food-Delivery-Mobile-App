package com.example.fooddeliveryapp.Model;

public class CategoryModel {
    private int Id;
    private String ImagePath,Name;
    
    public CategoryModel() {
    }
    
    public CategoryModel(int id, String imagePath, String name) {
        Id = id;
        ImagePath = imagePath;
        Name = name;
    }
    
    public int getId() {
        return Id;
    }
    
    public void setId(int id) {
        Id = id;
    }
    
    public String getImagePath() {
        return ImagePath;
    }
    
    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
    
    public String getName() {
        return Name;
    }
    
    public void setName(String name) {
        Name = name;
    }
}
