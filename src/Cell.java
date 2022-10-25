public class Cell {

    // Attributes of Cell

    private int x; // X coordinate

    private int y; // coordinate

    private boolean isMine;  // will be true if cell has a mine

    private boolean isFlag; // will be true if cell is flagged

    private boolean isVisible;

    private int nearMines;

    public Cell(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
        this.isFlag = false;
        this.isVisible = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getNearMines() {
        return nearMines;
    }

    public void setNearMines(int nearMines) {
        this.nearMines = nearMines;
    }
}
