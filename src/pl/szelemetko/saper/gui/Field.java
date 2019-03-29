package pl.szelemetko.saper.gui;

import pl.szelemetko.saper.game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Logiczna oraz graficzna reprezentacja pola na planszy gry.
 */
public class Field extends JButton {

    private int xIndex;
    private int yIndex;
    private boolean mine;
    private boolean marked;
    private boolean maybe;
    private boolean revealed = false;
    private int minesAround;
    private int markedAround = 0;
    private Board board;
    private GameController gameController;
    private MouseAdapter mouseAdapter;

    /**
     * Stwórz nowe pole gry.
     *
     * @param xIndex         indeks x
     * @param yIndex         indeks y
     * @param board          plansza gry
     * @param gameController kontroler gry
     */
    public Field(int xIndex, int yIndex, Board board, GameController gameController) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.gameController = gameController;
        this.board = board;
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setSize(10, 10);
        this.setFocusPainted(false);
        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!gameController.isStarted()) {
                    gameController.startGame();
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    reveal();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    mark();
                }
            }
        };
        this.addMouseListener(this.mouseAdapter);
        this.setBackground(Color.LIGHT_GRAY);
    }

    /**
     * Wyłącz możliwość kliknięciea w to pole.
     */
    public void disableClicking() {
        this.removeMouseListener(this.mouseAdapter);
    }

    /**
     * Odkryj to pole.
     */
    public void reveal() {
        if (this.revealed && (this.minesAround - this.markedAround == 0)) {
            this.board.revealNeighbours(xIndex, yIndex);
            return;
        }
        if (this.revealed || this.marked || this.maybe) {
            return;
        }

        if (this.mine) {
            showMine();
        } else if (minesAround > 0) {
            showNumber();
        } else if (minesAround == 0){
            showEmpty();
        }
    }

    /**
     * Pokaż pole, jako minę.
     */
    void showMine() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setText("X");
        this.revealed = true;
        this.gameController.loseGame();
    }

    /**
     * Pokaż pole, jako informację o liczbie min w sąsiedztwie..
     */
    void showNumber() {
        switch (minesAround) {
            case 1:
                this.setForeground(new Color(0x00,0x00,0xFF));
                break;
            case 2:
                this.setForeground(new Color(0x00,0x7B,0x00));
                break;
            case 3:
                this.setForeground(new Color(0xFF,0x00,0x00));
                break;
            case 4:
                this.setForeground(new Color(0x00,0x00,0x7B));
                break;
            case 5:
                this.setForeground(new Color(0x7B,0x00,0x00));
                break;
            case 6:
                this.setForeground(new Color(0x00,0x7B,0x7B));
                break;
            case 7:
                this.setForeground(new Color(0x00,0x00,0x00));
                break;
            case 8:
                this.setForeground(new Color(0x7B,0x7B,0x7B));
                break;
            default:
                break;
        }
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setText(String.valueOf(minesAround));
        this.revealed = true;
    }

    /**
     * Pokaż pole jako puste i każ planszy odkryć sąsiednie pola.
     */
    void showEmpty() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.revealed = true;
        this.board.revealNeighbours(this.xIndex, this.yIndex);
    }

    /**
     * Pokaż pole, jako błędnie oznaczoną minę.
     */
    public void showError() {
        this.setBackground(Color.RED);
    }

    /**
     * Oznacz pole jako mina lub podejrzane, o posiadanie miny.
     */
    public void mark() {
        if (this.revealed) {
            return;
        }
        if (this.marked) {
            this.maybe = true;
            this.marked = false;
            this.setText("?");
            if(this.mine) {
                this.board.removeFoundMine();
            } else {
                this.board.removeIncorrectMine();
            }
            this.board.removeMarkedNeighbour(xIndex, yIndex);
        } else if (this.maybe) {
            this.maybe = false;
            this.setText("");
        } else {
            this.marked = true;
            this.setText("M");
            if(this.mine) {
                this.board.addFoundMine();
            } else {
                this.board.addIncorrectMine();
            }
            this.board.addMarkedNeighbour(xIndex, yIndex);
        }
    }

    /**
     * Pobierz infromacje, czy pole zawiera minę.
     *
     * @return prawda lub fałsz
     */
    public boolean hasMine() {
        return mine;
    }

    /**
     * Pobierz minę na polu.
     *
     * @param mine prawda lub fałsz
     */
    public void setMine(boolean mine) {
        this.mine = mine;
    }

    /**
     * Pobierz liczbę min w sąsiedztwie pola.
     *
     * @return liczba min
     */
    public int getMinesAround() {
        return minesAround;
    }

    /**
     * Ustaw liczbę min w sąsiedztwie pola.
     *
     * @param minesAround liczba min
     */
    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    /**
     * Sprawdź, czy pole jest oznaczone przez użytkownika, jako mina.
     *
     * @return  prawda lub fałsz
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Ustawe, że pole jest oznaczone przez użytkownika jako mina.
     *
     * @param marked  prawda lub fałsz
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    /**
     * Sprawdź, czy pole jest oznaczone przez użytkownika, jako podejrzane o minę.
     *
     * @return prawda lub fałsz
     */
    public boolean isMaybe() {
        return maybe;
    }

    /**
     * Ustaw, czy pole jest oznaczone przez użytkownika, jako podejrzane o minę.
     *
     * @param maybe prawda lub fałsz
     */
    public void setMaybe(boolean maybe) {
        this.maybe = maybe;
    }

    /**
     * Sprawdź, czy pole jest już odkryte.
     *
     * @return prawda lub fałsz
     */
    public boolean isRevealed() {
        return revealed;
    }

    /**
     * Ustawe, czy pole jest już odkryte.
     *
     * @param revealed prawda lub fałsz
     */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    /**
     * Zwiększ liczbę sąsiadów oznaczonych jako mina o 1.
     */
    public void incrementMarkedNeighbourCount() {
        this.markedAround++;
    }

    /**
     * Zmniejsz liczbę sąsiadów oznaczonych jako mina o 1.
     */
    public void decrementMarkedNeighbourCount() {
        this.markedAround--;
    }


}
