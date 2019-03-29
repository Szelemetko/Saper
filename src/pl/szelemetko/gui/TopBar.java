package pl.szelemetko.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa TopBar reprezentuje element graficzny belki nad główną planszą gry
 *
 */
public class TopBar extends JPanel {

    /**
     * Tworzy nową belkę do umieszczenia nad planszą.
     *
     * @param timer       the timer
     * @param counter     the counter
     * @param resetButton the reset button
     */
    public TopBar(GameTimer timer, MineCounter counter, ResetButton resetButton) {
        this.setSize(100,10);
        this.setLayout(new GridLayout());
        this.add(new JLabel("Mines",SwingConstants.RIGHT));
        this.add(counter);
        this.add(resetButton);
        this.add(new JLabel("Time", SwingConstants.RIGHT));
        this.add(timer);
    }
}
