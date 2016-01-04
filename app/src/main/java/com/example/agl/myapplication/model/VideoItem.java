/**
 * Created by agl on 12/14/15.
 */

package com.example.agl.myapplication.model;

public class VideoItem {

    private String Title;
    private String Extract;
    private String Image;
    private String URL_Video;
    private int Views;


    public VideoItem(String Title, String Extract, String Image, String URL_Video, int Views){
        this.Title      = Title;
        this.Extract    = Extract;
        this.Image      = Image;
        this.URL_Video  = URL_Video;
        this.Views      = Views;
    }


    public String getTitle(){
        return Title;
    }

    public String getExtract(){
        return  Extract;
    }

    public String getImage(){
        return Image;
    }

    public String getURL_Video(){
        return  URL_Video;
    }

    public int getViews(){
        return Views;
    }


}
