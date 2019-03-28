package pl.szelemetko.gui;

import pl.szelemetko.game.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {

    private GameController gameController;
    private int boardHeight;
    private int boardWidth;
    private int mines;
    private Field[][] fields;
    private Random random = new Random();

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

    public void revealAll() {
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                fields[x][y].reveal();
            }
        }
    }

    public void revealMines() {
        this.gameController.endGame();
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                if(fields[x][y].hasMine()) {
                    fields[x][y].reveal();
                }
            }
        }
    }

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

    public void initBoard() {
        populateBoard();
        addMines(mines);
    }

    private void populateBoard() {
        this.fields = new Field[this.boardHeight][this.boardWidth];
        for (int x = 0; x < this.boardHeight; x++) {
            for (int y = 0; y < this.boardWidth; y++) {
                Field field = new Field(x,y, this);
                fields[x][y] = field;
                this.add(field);
            }
        }
    }

    int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

}

