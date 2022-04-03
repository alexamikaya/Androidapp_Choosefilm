package com.example.myfilm.recommend;

public class Objects {

    private String name;
    private String image;
    private String desc;
    public Objects ( String name, String image, String desc){

        this.name = name;
        this.image = image;
        this.desc = desc;
    }

    public Objects(String name, String image) {
        this.name = name;
        this.image = image;
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
    public String getdesc(){
        return desc;
    }
    public void setdesc(String desc){
        this.desc = desc;
    }
}
