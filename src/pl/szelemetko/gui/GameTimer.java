package pl.szelemetko.gui;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTimer extends JLabel implements Runnable {

    private AtomicInteger time;

    public GameTimer() {
        super(String.valueOf(0), SwingConstants.CENTER);
        this.time = new AtomicInteger();
    }

    @Override
    public void run() {
        this.time.set(0);
        while(!Thread.currentThread().isInterrupted()) {
            this.setText(String.valueOf(time.getAndIncrement()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public int getTime() {
        return this.time.get();
    }
    public void setTime(int time) {
        this.time.set(time);
    }
}
