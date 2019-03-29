package pl.szelemetko.gui;

import pl.szelemetko.game.GameController;

import javax.swing.*;

/**
 * Klasa łącząca w całośc graficzny interfejs użtkownika oraz będąca oknem gry.
 */
public class GUI extends JFrame {

    private Board board;
    private TopBar topBar;
    private GameMenu menu;

    /**
     * Tworzy nowy interfej użytkownika.
     *
     * @param gameController the game controller
     */
    public GUI(GameController gameController) {
        super("Saper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.board = gameController.getBoard();
        this.topBar = new TopBar(gameController.getGameTimer(), gameController.getMineCounter(), new ResetButton(gameController));
        this.menu = new GameMenu(gameController);
        this.getContentPane().add(topBar, "North");
        this.getContentPane().add(board, "Center");
        this.setSize(board.getBoardWidth() * 40,board.getBoardHeight() * 40);
        this.setJMenuBar(this.menu);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Resetuje interfejs użytkownia.
     *
     * @param board the board
     */
    public void resetGUI(Board board) {
        this.remove(this.board);
        this.board = board;
        this.add(board);
        this.setSize(board.getBoardWidth() * 40,board.getBoardHeight() * 40);
        this.revalidate();
        this.repaint();
    }
}
