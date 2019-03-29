package pl.szelemetko.saper.gui;

import javax.swing.*;

/**
 * Klasa jest logiczną oraz graficzną reprezentacją licznika min, które zostały do odrkycia.
 */
public class MineCounter extends JLabel {
    private int minesToFind;

    /**
     * Tworzy nowy licznik min.
     *
     * @param mines liczba min
     */
    public MineCounter(int mines) {
        super(String.valueOf(mines),SwingConstants.CENTER);
        this.minesToFind = mines;
    }

    /**
     * Ustawia liczbę min do odkrycia.
     *
     * @param mines liczba min
     */
    public void setMinesToFind(int mines) {
        this.minesToFind = mines;
        this.setText(String.valueOf(this.minesToFind));
    }

    /**
     * Zwiększa licznik min o 1.
     */
    public void increment() {
        this.minesToFind++;
        this.setText(String.valueOf(this.minesToFind));
    }

    /**
     * Zmniejsza licznik min o 1.
     */
    public void decrement() {
        this.minesToFind--;
        this.setText(String.valueOf(this.minesToFind));
    }
}
