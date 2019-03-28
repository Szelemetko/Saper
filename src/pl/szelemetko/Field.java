package pl.szelemetko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Field extends JButton {

    private int x;
    private int y;
    private boolean mine;
    private boolean marked;
    private boolean maybe;
    private boolean revealed = false;
    private int minesAround;
    private int markedAround = 0;
    private Board board;

    public Field(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setSize(10, 10);
        this.setFocusPainted(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    reveal();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    mark();
                }
            }
        });
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void reveal() {
        if (this.revealed && (this.minesAround - this.markedAround == 0)) {
            this.board.revealNeighbours(x, y);
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

    void showMine() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setText("X");
        this.revealed = true;
        this.board.revealMines();
    }

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

    void showEmpty() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.revealed = true;
        this.board.revealNeighbours(this.x, this.y);
    }

    public void mark() {
        if (this.revealed) {
            return;
        }
        if (this.marked) {
            this.maybe = true;
            this.marked = false;
            this.setText("?");
            this.board.removeMarkedNeighbour(x, y);
        } else if (this.maybe) {
            this.maybe = false;
            this.setText("");
        } else {
            this.marked = true;
            this.setText("F");
            this.board.addMarkedNeighbour(x, y);
        }
    }

    public boolean hasMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMaybe() {
        return maybe;
    }

    public void setMaybe(boolean maybe) {
        this.maybe = maybe;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public void incrementMarkedNeighbourCount() {
        this.markedAround++;
    }

    public void decrementMarkedNeighbourCount() {
        this.markedAround--;
    }
}
