package pl.szelemetko;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {

    public TopBar(GameTimer timer, MineCounter counter, ResetButton resetButton) {
        this.setSize(100,10);
        this.setLayout(new GridLayout());
        this.add(counter);
        this.add(resetButton);
        this.add(timer);
    }
}
