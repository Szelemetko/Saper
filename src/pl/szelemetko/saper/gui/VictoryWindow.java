package pl.szelemetko.saper.gui;

import javax.swing.*;

/**
 * Okno wyświetlane w wypadku zwycięstwa.
 */
public class VictoryWindow extends JFrame {

    /**
     * Tworzy, konfiguruje i wyświetla okno informujące o zwycięstwie.
     */
    public VictoryWindow() {
        super();
        this.add(new JLabel("YOU WON", SwingConstants.CENTER));
        this.setVisible(true);
        this.setSize(200, 100);
        this.setAlwaysOnTop(true);
    }
}
