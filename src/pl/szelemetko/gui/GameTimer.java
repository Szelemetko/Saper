package pl.szelemetko.gui;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Klasa jest graficzną i logiczną reprezentacją stopera mierzącego czas gry.
 */
public class GameTimer extends JLabel implements Runnable {

    private AtomicInteger time;
    private Thread counterThread;

    /**
     * Tworzy nowy stoper, u ustawia jego wartośc na 0.
     */
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

    /**
     * Uruchom stoper.
     */
    public void start() {
        this.counterThread = new Thread(this::run);
        this.counterThread.start();
    }

    /**
     * Zatrzymaj stoper.
     */
    public void stop() {
        this.counterThread.interrupt();
    }

    /**
     * Zresetuj stoper.
     */
    public void reset() {
        this.time.set(0);
        this.setText(String.valueOf(time.getAndIncrement()));
    }
}
