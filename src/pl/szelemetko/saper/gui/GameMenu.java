package pl.szelemetko.saper.gui;

import pl.szelemetko.saper.game.GameController;
import pl.szelemetko.saper.game.GameSettings;

import javax.swing.*;

/**
 * Klasa jest logiczną i graficzną reprezentacją menu gry.
 */
public class GameMenu extends JMenuBar {

    private GameController gameController;

    /**
     * Stwórz menu gry.
     *
     * @param gameController kontroler gry.
     */
    public GameMenu(GameController gameController) {
        super();
        this.gameController = gameController;
        JMenu game = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        game.add(newGame);
        newGame.addActionListener(e -> {
            this.gameController.resetGame();
        });
        JMenuItem reveal = new JMenuItem("Reveal Board");
        game.add(reveal);
        reveal.addActionListener(e -> {
            this.gameController.revealBoard();
        });
        this.add(game);

        JMenu options = new JMenu("Options");
        JMenuItem beginner = new JMenuItem("Beginner");
        beginner.addActionListener(e -> this.gameController.setGameSettings(GameSettings.BEGINNER));
        JMenuItem advanced = new JMenuItem("Advanced");
        advanced.addActionListener(e -> this.gameController.setGameSettings(GameSettings.ADVANCED));
        JMenuItem expert = new JMenuItem("Expert");
        expert.addActionListener(e -> this.gameController.setGameSettings(GameSettings.EXPERT));
        JMenuItem custom = new JMenuItem("Custom");
        custom.addActionListener(e -> new CustomeSettingsWindow(this.gameController));

        options.add(beginner);
        options.add(advanced);
        options.add(expert);
        options.add(custom);
        this.add(options);
    }
}
