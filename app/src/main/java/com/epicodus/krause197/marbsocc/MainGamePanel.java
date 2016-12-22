package com.epicodus.krause197.marbsocc;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.epicodus.krause197.marbsocc.models.Ball;
import com.epicodus.krause197.marbsocc.models.components.Speed;

/**
 * Created by Guest on 12/19/16.
 */
public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;
    private Ball ball;
    private String avgFps;


    public MainGamePanel (Context context) {
        super(context);
        getHolder().addCallback(this);
        ball = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.ui_ball), 10, 50, 100, 40, 10, 4);
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

    public void setAveFps(String avgFps) {
        this.avgFps = avgFps;
    }


    public void render(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        ball.draw(canvas);
        displayFps(canvas, avgFps);
    }

    private void displayFps(Canvas canvas, String fps) {
        if (canvas != null && fps != null) {
            Paint paint = new Paint();
            paint.setARGB(255, 255, 255, 255);
            canvas.drawText(fps, this.getWidth() - 50, 20, paint);
        }
    }

    public void update() {
        if (ball.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT && ball.getX() + ball.getBitmap().getWidth() / 2 >= getWidth()) {
            ball.getSpeed().toggleXDirection();
        }
        if (ball.getSpeed().getxDirection() == Speed.DIRECTION_LEFT && ball.getX() - ball.getBitmap().getWidth() / 2 <= 0) {
            ball.getSpeed().toggleXDirection();
        }
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_DOWN && ball.getY() + ball.getBitmap().getHeight() / 2 >= getHeight()) {
            ball.getSpeed().toggleYDirection();
        }
        if (ball.getSpeed().getyDirection() == Speed.DIRECTION_UP && ball.getY() - ball.getBitmap().getHeight() / 2 <= 0) {
            ball.getSpeed().toggleYDirection();
        }
        ball.update(System.currentTimeMillis());
    }
}
