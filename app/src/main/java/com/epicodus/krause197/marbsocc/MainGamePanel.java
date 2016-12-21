package com.epicodus.krause197.marbsocc;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.epicodus.krause197.marbsocc.models.Ball;

/**
 * Created by Guest on 12/19/16.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;
    private Ball ball;

    public MainGamePanel (Context context) {
        super(context);
        getHolder().addCallback(this);
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.mipmap.ball), 50, 50);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }


    @Override
    public SurfaceHolder getHolder() {
        return super.getHolder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ball.handleActionDown((int)event.getX(), (int)event.getY());
            if (event.getY()> getHeight() - 50) {
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (ball.isTouched()) {
                ball.setX((int)event.getX());
                ball.setY((int)event.getY());
            }
        } if (event.getAction() == MotionEvent.ACTION_UP) {
            if (ball.isTouched()) {
                ball.setTouched(false);
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        ball.draw(canvas);

    }
}
