package pl.szelemetko;

import javax.swing.*;

public class ResetButton extends JButton {
    GameController gameController;

    public ResetButton(GameController gameController) {
        super("New Game");
        this.gameController = gameController;
        this.addActionListener(e -> gameController.resetGame());
    }

}
