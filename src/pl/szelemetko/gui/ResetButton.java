package pl.szelemetko.gui;

import pl.szelemetko.game.GameController;

import javax.swing.*;

/**
 * Przycisk do resetowania stanu gry. Każdy reset generuje nowa planszę.
 */
public class ResetButton extends JButton {

    private GameController gameController;

    /**
     * Tworzy now przycisk resetu.
     *
     * @param gameController kontroler gry
     */
    public ResetButton(GameController gameController) {
        super("New");
        this.gameController = gameController;
        this.addActionListener(e -> gameController.resetGame());
    }

}
