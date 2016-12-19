package com.epicodus.krause197.marbsocc;

/**
 * Created by Guest on 12/19/16.
 */
public class MainThread extends Thread {

    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while (running) {

        }
    }
}
