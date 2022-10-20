public class Cell {

    // Attributes of Cell

    private int x;

    private int y;

    private boolean mine;

    private boolean flag;

    private boolean visible;

    private int nearMines;

    public Cell() {
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
    public void setNearMines(int nearMines){
        this.nearMines = nearMines;
    }
    public int getNearMines(){
        return nearMines;

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
