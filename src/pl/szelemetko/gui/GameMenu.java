package pl.szelemetko.gui;

import pl.szelemetko.game.GameController;
import pl.szelemetko.game.GameSettings;

import javax.swing.*;

public class GameMenu extends JMenuBar {

    GameController gameController;

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

        options.add(beginner);
        options.add(advanced);
        options.add(expert);
        this.add(options);
    }
}
