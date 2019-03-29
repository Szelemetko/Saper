package pl.szelemetko.saper.gui;

import pl.szelemetko.saper.game.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Klasa będąca jednocześnie graficzną oraz logiczną reprezentacją planszy.
 */
public class Board extends JPanel {

    private GameController gameController;
    private int boardHeight;
    private int boardWidth;
    private int mines;
    private int minesFound = 0;
    private int minesIncorrect = 0;
    private Field[][] fields;
    private Random random = new Random();

    /**
     * Tworzy nową tablicę i przekazuje jej kontroler gry, aby mogła wywoływać wydarzenia związane z grą, w reakcji na akcje gracza.
     *
     * @param gameController kontroler gry
     */
    public Board(GameController gameController) {
        super();
        this.gameController = gameController;
        this.boardHeight = gameController.getGameSettings().getBoardHeight();
        this.boardWidth = gameController.getGameSettings().getBoardWidth();
        this.mines = gameController.getGameSettings().getMines();
        this.setLayout(new GridLayout(this.boardHeight, this.boardWidth));
        this.setVisible(true);
        this.initBoard();
    }

    /**
     * Informuje sąsiednie pola, że pole na pozycji (x,y) zostało oznaczone przez gracza jako mina.
     *
     * @param x pozycja x pola
     * @param y pozycja y pola
     */
    public void addMarkedNeighbour(int x, int y) {
        int[] xToCheck = {x - 1, x, x + 1};
        int[] yToCheck = {y - 1, y, y + 1};
        this.gameController.getMineCounter().decrement();

        for (int i = 0; i < 3; i++) {
            if (xToCheck[i] >= 0 && xToCheck[i] < fields.length) {
                for (int j = 0; j < 3; j++) {
                    if (yToCheck[j] >= 0
                            && yToCheck[j] < fields[xToCheck[i]].length
                    ) {
                        fields[xToCheck[i]][yToCheck[j]].incrementMarkedNeighbourCount();
                    }
                }
            }
        }
    }

    /**
     * Informuje sąsiednie pola, że pole na pozycji (x,y) zostało odznaczone przez gracza jako mina.
     *
     * @param x pozycja x pola
     * @param y pozycja y pola
     */
    public void removeMarkedNeighbour(int x, int y) {
        int[] xToCheck = {x - 1, x, x + 1};
        int[] yToCheck = {y - 1, y, y + 1};
        this.gameController.getMineCounter().increment();

        for (int i = 0; i < 3; i++) {
            if (xToCheck[i] >= 0 && xToCheck[i] < fields.length) {
                for (int j = 0; j < 3; j++) {
                    if (yToCheck[j] >= 0
                            && yToCheck[j] < fields[xToCheck[i]].length
                    ) {
                        fields[xToCheck[i]][yToCheck[j]].decrementMarkedNeighbourCount();
                    }
                }
            }
        }
    }

    /**
     * Dodaje miny, we wskazanej ilości do losowych pól na planszy.
     * @param mines liczba min do dodania
     */
    private void addMines(int mines) {
        for (int i = 0; i < mines;) {
            int x = random.nextInt(boardHeight);
            int y = random.nextInt(boardWidth);

            if (!fields[x][y].hasMine()) {
                fields[x][y].setMine(true);
                i++;
            }
        }

        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                fields[x][y].setMinesAround(countNeighbouringMines(x, y));
            }
        }
    }

    /**
     * Liczy miny w polach sąsiadujących z polem na pozycji (x,y).
     * @param x pozycja x pola
     * @param y pozycja y pola
     * @return liczba min
     */
    private int countNeighbouringMines(int x, int y) {
        int result = 0;
        int[] xToCheck = {x - 1, x, x + 1};
        int[] yToCheck = {y - 1, y, y + 1};

        for (int i = 0; i < 3; i++) {
            if (xToCheck[i] >= 0 && xToCheck[i] < fields.length) {
                for (int j = 0; j < 3; j++) {
                    if (yToCheck[j] >= 0
                            && yToCheck[j] < fields[xToCheck[i]].length
                            && fields[xToCheck[i]][yToCheck[j]].hasMine()
                    ) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Odkrywa wszyetkie pola na planszy.
     */
    public void revealAll() {
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                fields[x][y].reveal();
            }
        }
    }

    /**
     * Wyłącza możliwość wykonywania akcji na wszystkich polach planszy.
     */
    public void disableFields() {
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                fields[x][y].disableClicking();
            }
        }
    }

    /**
     * Odkrywa wszysktie nieodkryte miny na planszy i oznacza te błędnie zaznaczone.
     */
    public void revealMines() {
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                if(fields[x][y].hasMine()) {
                    fields[x][y].reveal();
                } else if (fields[x][y].isMarked() && !fields[x][y].hasMine()){
                    fields[x][y].showError();
                }
            }
        }
    }

    /**
     * Odkrywa wszytkich pola sąsiadujące z polem na pozycji (x,y).
     * @param x
     * @param y
     */
    public void revealNeighbours(int x, int y) {
        int[] xToCheck = {x - 1, x, x + 1};
        int[] yToCheck = {y - 1, y, y + 1};

        for (int i = 0; i < 3; i++) {
            if (xToCheck[i] >= 0 && xToCheck[i] < fields.length) {
                for (int j = 0; j < 3; j++) {
                    if (yToCheck[j] >= 0
                            && yToCheck[j] < fields[xToCheck[i]].length
                            && !fields[xToCheck[i]][yToCheck[j]].isRevealed()
                    ) {
                        fields[xToCheck[i]][yToCheck[j]].reveal();
                    }
                }
            }
        }
    }

    /**
     * Inicjalizuje pola oraz dodaje miny do planszy.
     */
    private void initBoard() {
        populateBoard();
        addMines(mines);
    }

    /**
     * Wypełnia pustą planszę polami.
     */
    private void populateBoard() {
        this.fields = new Field[this.boardHeight][this.boardWidth];
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                Field field = new Field(x,y, this, this.gameController);
                fields[x][y] = field;
                this.add(field);
            }
        }
    }

    /**
     * Pobierz liczbę wierszy planszy.
     *
     * @return liczba wierszy
     */
    int getBoardHeight() {
        return boardHeight;
    }



    /**
     * Pobierz liczbę kolumn planszy
     *
     * @return liczba kolumn
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Zwiększ liczbę poprawnie oznaczonych min o 1.
     */
    public void addFoundMine() {
        this.minesFound++;
        if(minesFound == mines  && minesIncorrect==0) {
            this.gameController.winGame();
        }
    }

    /**
     * Zmniejsz liczbę poprawnie oznaczonych min o 1.
     */
    public void removeFoundMine() {
        this.minesFound--;
    }

    /**
     * Zwiększ liczbę poprawnie oznaczonych min o 1.
     */
    public void addIncorrectMine() {
        this.minesIncorrect++;

    }

    /**
     * Zmniejsz liczbę poprawnie oznaczonych min o 1.
     */
    public void removeIncorrectMine() {
        this.minesIncorrect--;
        if(minesFound == mines && minesIncorrect==0) {
            this.gameController.winGame();
        }
    }


}

