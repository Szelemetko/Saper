package pl.szelemetko.game;

import pl.szelemetko.gui.Board;
import pl.szelemetko.gui.GUI;
import pl.szelemetko.gui.GameTimer;
import pl.szelemetko.gui.MineCounter;

import java.util.concurrent.atomic.AtomicBoolean;

public class GameController {

    private GameSettings gameSettings;
    private Board board;
    private AtomicBoolean started;
    private GameTimer gameTimer;
    private MineCounter mineCounter;
    private Thread counterThread;

    private GUI gui;

    public GameController() {
        this.gameSettings = GameSettings.BEGINNER;
        this.board = new Board(this);
        this.started = new AtomicBoolean(false);
        this.gameTimer = new GameTimer();
        this.mineCounter = new MineCounter(gameSettings.getMines());
        this.gui = new GUI(this);
    }

    public void startGame() {
        this.started.set(true);
        counterThread = new Thread(gameTimer);
        counterThread.start();
    }

    public void resetGame() {
        this.counterThread.interrupt();
        this.gameTimer.setTime(0);
        this.mineCounter.setMinesToFind(this.gameSettings.getMines());
        this.board = new Board(this);
        this.gui.resetGUI(this.board);
        startGame();
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        resetGame();
    }

    public void loseGame() {
        this.counterThread.interrupt();
        this.board.revealMines();
    }

    public void revealBoard(){
        this.board.revealAll();
    }

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public Board getBoard() {
        return board;
    }

    public AtomicBoolean getStarted() {
        return started;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public MineCounter getMineCounter() {
        return mineCounter;
    }

}
