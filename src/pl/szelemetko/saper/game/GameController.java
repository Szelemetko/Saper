package pl.szelemetko.saper.game;

import pl.szelemetko.saper.gui.*;

/**
 * Klasa zarządzając grą Saper.
 * Zarządrza przygotowaniem planszy
 */
public class GameController {

    private GameSettings gameSettings;
    private Board board;
    private GameTimer gameTimer;
    private MineCounter mineCounter;
    private boolean started = false;
    private VictoryWindow victoryWindow;

    private GUI gui;

    /**
     * Konstruktor inicjalizujący kontroler oraz elementy gry
     */
    public GameController() {
        this.gameSettings = GameSettings.BEGINNER;
        this.board = new Board(this);
        this.gameTimer = new GameTimer();
        this.mineCounter = new MineCounter(gameSettings.getMines());
        this.gui = new GUI(this);
    }

    /**
     * Metoda rozpoczynająca grę
     */
    public void startGame() {
        this.started = true;
        this.gameTimer.start();
        if(this.victoryWindow!=null) {
            this.victoryWindow.dispose();
        }
    }

    /**
     * Metoda resetująca stan gry.
     * Przy każdym resecie generowana jest nowa plansza.
     */
    public void resetGame() {
        this.started = false;
        this.gameTimer.stop();
        this.gameTimer.reset();
        this.mineCounter.setMinesToFind(this.gameSettings.getMines());
        this.board = new Board(this);
        this.gui.resetGUI(this.board);
        if(this.victoryWindow!=null) {
            this.victoryWindow.dispose();
        }

    }

    /**
     * Metoda pozwaląjąca ustawić nowe parametry gry.
     * Po zmianie parametrów gra jest resetowana.
     *
     * @param gameSettings ustawienia gry
     */
    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        resetGame();
    }

    /**
     * Metoda do uruchomienia w przypadku, kiedy użytkownik przegra grę.
     * Zatrzymuje licznik, blokuje możliwość klikania, pokazuje pozycje nieodkrytych min oraz wyróżnia te, które zostały błędnie zaznaczone.
     */
    public void loseGame() {
        this.started = false;
        this.gameTimer.stop();
        this.board.revealMines();
        this.board.disableFields();
    }

    /**
     * Metoda do uruchomienia w przypadku, kiedy użytkownik przegra grę.
     * Zatrzymuje licznik oraz pokazuje informajcę o zwycięstwie.
     */
    public void winGame() {
        this.started = false;
        this.gameTimer.stop();
        this.board.disableFields();
        this.victoryWindow = new VictoryWindow();
    }


    /**
     * Metoda pozwalająca na odkrycie całej planszy, napisana w celu ułatwienia debugowania aplikacji.
     */
    public void revealBoard(){
        this.board.revealAll();
    }

    /**
     * Pobierz ustawienia gry
     *
     * @return ustawienia gry
     */
    public GameSettings getGameSettings() {
        return gameSettings;
    }

    /**
     * Pobierz planszę.
     *
     * @return plansza gry
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Pobierz stoper.
     *
     * @return stoper
     */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * Pobierz licznik min.
     *
     * @return liczik min
     */
    public MineCounter getMineCounter() {
        return mineCounter;
    }

    /**
     * Sprawdź, czy gra jest rozpoczęta.
     *
     * @return the boolean
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Ustaw czy gra jest rozpoczęta.
     *
     * @param started
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

}
