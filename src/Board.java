import java.util.Random;

public class Board {

    int size;


    private int mineCells;
    private String[][] board;
    private Cell[][] cells;

    public Board(int size) {

        //create a new board
        this.size = size;
        this.board = new String[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }

      //  setMines();
    }

    public void printBoard() {

        //print board
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + i + "  ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(" " + i + " ");
            for (int j = 0; j < size; j++) {
                String position = board[i][j];
                System.out.print(position.isEmpty() ? "   " : position);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public Boolean changePlace(int row, int col){
        //change place of marker
          String position = board[row][col];
        if (position.isEmpty()) {
            board[row][col] = "x";
            return true;
        }
        return true;

    }


   // private void setMines() { //ranodm placement of mines
    //    Random rnd = new Random();

     //    for(int i = 0; i < mineCells; i++){
        //     int x = rnd.nextInt(//row kanske?);
         //    int y = rnd.nextInt(//col kanske?);

          //   if (!cells[x][y].isMine())
             //    cells[x][y].setMine(true);
           //  else

              //   i--;
       //  }

    }

}
