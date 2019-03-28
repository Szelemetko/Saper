package pl.szelemetko;

import javax.swing.*;

public class MineCounter extends JLabel {
    private int minesToFind;

    public MineCounter(int mines) {
        super(String.valueOf(mines),SwingConstants.CENTER);
        this.minesToFind = mines;
    }

    public void setMinesToFind(int mines) {
        this.minesToFind = mines;
        this.setText(String.valueOf(this.minesToFind));
    }

    public void increment() {
        this.minesToFind++;
        this.setText(String.valueOf(this.minesToFind));
    }

    public void decrement() {
        this.minesToFind--;
        this.setText(String.valueOf(this.minesToFind));
    }
}
