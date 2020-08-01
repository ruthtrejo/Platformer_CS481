package edu.utep.cs4381.hw3platformer;

public class BackgroundData {
    public String bitmapName;
    public boolean isParallax;
    //layer 0 is the map
    public int layer;
    public float startY;
    public float endY;
    public float speed;
    public int height;
    public int width;

    public BackgroundData(String bitmap, boolean isParallax, int layer, float startY, float endY, float speed, int height){
        this.bitmapName = bitmap;
        this.isParallax = isParallax;
        this.layer = layer;
        this.startY = startY;
        this.endY = endY;
        this.speed = speed;
        this.height = height;
    }

}


