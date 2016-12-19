package com.epicodus.krause197.marbsocc;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Guest on 12/19/16.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public MainGamePanel (Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }


    @Override
    public SurfaceHolder getHolder() {
        return super.getHolder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
