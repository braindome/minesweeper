public class Cell {

    // Attributes of Cell

    private int x;

    private int y;

    private boolean mine;

    private boolean flag;

    private boolean visible;

    private int nearBombs;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setVisible (boolean visible){
        this.visible = visible;
    }
    public boolean isVisible(){
        return visible;
    }
    public void setMine(boolean mine){
        this.mine = mine;
    }
    public boolean isMine(){
        return mine;
    }
    public void setNearBombs(int nearBombs){
        this.nearBombs = nearBombs;
    }
    public int getNearMines(){
        return nearBombs;
    }

    // Method for placing a flag

    public void placeFlag() {

        if(flag) flag  = false;
        else {
            if(!visible) flag = true;
        }
   }


    public boolean isFlag(){
        return flag;
    }














}
