package com.epicodus.krause197.marbsocc.models;

import android.graphics.Bitmap;

/**
 * Created by Guest on 12/21/16.
 */
public class Ball {

    private Bitmap bitmap;
    private int x;
    private int y;

    private Ball (Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX() {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY() {
        this.y = y;
    }


}
