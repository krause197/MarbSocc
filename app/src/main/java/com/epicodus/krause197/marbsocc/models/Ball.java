package com.epicodus.krause197.marbsocc.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.epicodus.krause197.marbsocc.models.components.Speed;

/**
 * Created by Guest on 12/21/16.
 */
public class Ball {

    private Bitmap bitmap;
    private Rect sourceRect;
    private int frameNr;
    private int currentFrame;
    private long frameTicker;
    private int framePeriod;
    private int spriteWidth;
    private int spriteHeight;
    private int x;
    private int y;
    private boolean touched;
    private Speed speed;

    public Ball (Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        speed = new Speed();
        currentFrame = 0;
        frameNr = frameCount;
        spriteWidth = bitmap.getWidth() / frameCount;
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect (0, 0, spriteWidth, spriteHeight);
        framePeriod = 1000/fps;
        frameTicker = 01;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public void draw(Canvas canvas) {
        Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }

    public Speed getSpeed() {
        return speed;
    }

    public void handleActionDown(int eventX, int eventY) {
        if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth() / 2))) {
            if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
                setTouched(true);
            } else {
                setTouched(false);
            }
        } else {
            setTouched(false);
        }
    }

    public void update(long gameTime) {
        if (!touched) {
            x += (int)(speed.getXv() * speed.getxDirection());
            y += (int)(speed.getYv() * speed.getyDirection());
        }
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime;
            currentFrame++;
            if (currentFrame >= frameNr) {
                currentFrame = 0;
            }
        }
        this.sourceRect.left = currentFrame * spriteWidth;
        this.sourceRect.right = this.sourceRect.left + spriteWidth;
    }
}
