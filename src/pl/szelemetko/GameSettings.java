package pl.szelemetko;

public class GameSettings {

    public static GameSettings BEGINNER = new GameSettings(8,8,10);
    public static GameSettings ADVANCED = new GameSettings(16,16,40);
    public static GameSettings EXPERT = new GameSettings(16,30,99);

    private int boardHeight;
    private int boardWidth;
    private int mines;

    public GameSettings(int boardHeight, int boardWidth, int mines) {

        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.mines = mines;
    }


    public int getBoardHeight() {
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

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

}
