package com.example.almira.windson;

public class uploadinfo {

    public String imageName;
    public String imageURL;
    public uploadinfo(){
        //empty constructor needed
    }

    public uploadinfo(String name, String url) {
        this.imageName = name;
        this.imageURL = url;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String name) {
        imageName = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String url) {
        imageURL = url;
    }
}