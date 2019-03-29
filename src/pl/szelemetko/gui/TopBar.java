package pl.szelemetko.gui;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

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
