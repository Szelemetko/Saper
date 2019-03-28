package pl.szelemetko.gui;

import pl.szelemetko.game.GameController;

import javax.swing.*;

public class GUI extends JFrame {

    private Board board;
    private TopBar topBar;
    private GameMenu menu;

    public GUI(GameController gameController) {
        super("Saper");
        this.board = gameController.getBoard();
        this.topBar = new TopBar(gameController.getGameTimer(), gameController.getMineCounter(), new ResetButton(gameController));
        this.menu = new GameMenu(gameController);
        initializeGUI();
    }

    private void initializeGUI() {
        this.getContentPane().add(topBar, "North");
        this.getContentPane().add(board, "Center");
        this.setSize(board.getBoardWidth() * 40,board.getBoardHeight() * 40);
        this.setJMenuBar(this.menu);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void resetGUI(Board board) {
        this.remove(this.board);
        this.board = board;
        this.add(board);
        this.setSize(board.getBoardWidth() * 40,board.getBoardHeight() * 40);
        this.revalidate();
        this.repaint();
    }
}
