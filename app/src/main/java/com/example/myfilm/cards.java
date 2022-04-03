package com.example.myfilm;

public class cards {
    private String Id;
    private String name;
    private String image;

    public cards(String Id, String name, String image) {
        this.Id = Id;
        this.name = name;
        this.image = image;
    }

    public cards(String name) {
        this.name = name;
    }

    public String getId(){
        return Id;
    }
    public void setID(String ID){
        this.Id = Id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getimage(){
        return image;
    }
    public void setimage(String image){
        this.image = image;
    }
}