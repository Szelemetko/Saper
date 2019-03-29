package pl.szelemetko.game;

/**
 * Klasa reprezentująca ustawienia gry, czyli wysokośc planszy, szerokość planszy oraz liczbę min na planszy.
 */
public class GameSettings {

    /**
     * Standardowe ustawienia gry na poziomie BEGINNER.
     */
    public static final GameSettings BEGINNER = new GameSettings(8,8,10);
    /**
     *  Standardowe ustawienia gry na poziomie ADVANCED.
     */
    public static final GameSettings ADVANCED = new GameSettings(16,16,40);
    /**
     *  Standardowe ustawienia gry na poziomie EXPERT.
     */
    public static final GameSettings EXPERT = new GameSettings(16,30,99);

    private int boardHeight;
    private int boardWidth;
    private int mines;

    /**
     * Stwórz nowy obiek typu GameSettings
     *
     * @param boardHeight wysokośc planszy
     * @param boardWidth  szerokkość planszy
     * @param mines       liczba min
     */
    public GameSettings(int boardHeight, int boardWidth, int mines) {

        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.mines = mines;
    }


    /**
     * Pobierz wysokość planszy.
     *
     * @return wysokość planszy
     */
    public int getBoardHeight() {
        return boardHeight;
    }


    /**
     * Pobierz szerokość planszy.
     *
     * @return szerokość planszy
     */
    public int getBoardWidth() {
        return boardWidth;
    }


    /**
     * Pobierz liczbę min.
     *
     * @return liczba min
     */
    public int getMines() {
        return mines;
    }


}
