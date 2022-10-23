import java.util.Random;

public class Board {

    int size;

    private int mineCells;

    //private final String[][] visibleBoard;
    final String[][] visibleBoard;                  //Visible board.
    private final String[][] hiddenBoard;

    Random random = new Random();

    public Board(int size) {

        //create a new board
        this.size = size;
        this.visibleBoard = new String[size][size];
        this.hiddenBoard = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                visibleBoard[i][j] = "";
                hiddenBoard[i][j] = "";
            }
        }
      //  setMines();
    }

    //Displays the public game board.
    public void printVisibleBoard() {
        //print board
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + i + "  ");
            }
            if (i > 9) {
                System.out.print(" " + i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + i + " ");
            }
            if (i > 9) {
                System.out.print(" " + i );
            }
            for (int j = 0; j < size; j++) {
                String position = visibleBoard[i][j];
                System.out.print(position.isEmpty() ? "   " : position);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    //Generates mines across the game board. Fixed proportion at 20% of the game area. UPDATE: mines are not generated within the 3x3 starting area.
    public void mineGenerator() {
        int numOfMines = (size*size)/5;
        //System.out.println("N of mines: " + numOfMines);
        for (int i = 0; i <= numOfMines; i++) {
            int mineRow = random.nextInt(size);
            int mineCol = random.nextInt(size);

            if (!visibleBoard[mineRow][mineCol].equals(" X ")) {
                hiddenBoard[mineRow][mineCol] = " * ";

            }
//            if (hiddenBoard[mineRow][mineCol].equals(" * ")) {                          //Prints mine coordinates (for testing purposes).
//                System.out.println("Mine coordinates: " + mineRow + ", " + mineCol);
//            }
        }
    }



    //To call after the player's first move. It generates a 3x3 square around the first position entered by the player.
    //This square is always mine-free, and it starts the game.
    //FIXED: Out of Bounds exception handling. Needs more testing.
    public void startingAreaClear(int row, int col) {

        visibleBoard[row][col] = " X ";
        System.out.println("start ok");
        if ((row-1) >= 0 && ((col-1) >= 0)) {
            //System.out.println("inside if 1");
            visibleBoard[row-1][col-1] = " X ";
            //System.out.println("pos 1 ok");
        }
        if ((row-1) >= 0 && (col+1) < size) {
            visibleBoard[row-1][col+1] = " X ";
        }
        if ((row+1) < size && (col-1) >= 0) {
            visibleBoard[row+1][col-1] = " X ";
        }
        if ((row+1) < size && (col+1) < size) {
            visibleBoard[row+1][col+1] = " X ";
        }
        if ((col-1) >= 0) {
            visibleBoard[row][col-1] = " X ";
        }
        if ((row-1) >= 0) {
            visibleBoard[row-1][col] = " X ";
        }
        if ((col+1) < size) {
            visibleBoard[row][col+1] = " X ";
        }
        if ((row+1) < size) {
            visibleBoard[row+1][col] = " X ";
        }


    }


    //Checks EVERY SQUARE for proximity to mines, adds them to a counter and returns the value.
    public int boardHints() {
        int mineCounter = 0;
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (!hiddenBoard[i][j].equals(" * ")) {
                    //Diagonal cells next to mine.
                    if ((i-1) >= 0 && (j-1) >= 0) {
                        if (hiddenBoard[i-1][j-1].equals(" * ")) mineCounter++;
                    }
                    if ((i-1) >= 0 && (j+1) <= size) {
                        if (hiddenBoard[i-1][j+1].equals(" * ")) mineCounter++;
                    }
                    if ((i+1) <= size && (j-1) >= 0) {
                        if (hiddenBoard[i+1][j-1].equals(" * ")) mineCounter++;
                    }
                    if ((i-1) >= 0 && (j+1) <= size) {
                        if (hiddenBoard[i-1][j+1].equals(" * ")) mineCounter++;
                    }
                    //Above, below and to the sides of mine.
                    if ((j-1) >= 0) {
                        if (hiddenBoard[i][j-1].equals(" * ")) mineCounter++;
                    }
                    if ((i-1) >= 0) {
                        if (hiddenBoard[i-1][j].equals(" * ")) mineCounter++;
                    }
                    if ((j+1) <= size) {
                        if (hiddenBoard[i][j+1].equals(" * ")) mineCounter++;
                    }
                    if ((i+1) <= size) {
                        if (hiddenBoard[i+1][j].equals(" * ")) mineCounter++;
                    }
                }
            }
        }
        return mineCounter;
    }

    //Reveals amount of mines surrounding given square and replaces empty square with number of mines.
    //FIXED: Out of Bounds Exception handling. Needs more testing.
    public int minesAround(int row, int col) {
        int mineCounter = 0;
        if (!hiddenBoard[row][col].equals(" * ")) {
            //Diagonal cells next to mine.
            if ((row-1) >= 0 && (col-1) >= 0) {
                if (hiddenBoard[row-1][col-1].equals(" * ")) mineCounter++;
            }
            if ((row-1) >= 0 && (col+1) < size) {
                if (hiddenBoard[row-1][col+1].equals(" * ")) mineCounter++;
            }
            if ((row+1) < size && (col-1) >= 0) {
                if (hiddenBoard[row+1][col-1].equals(" * ")) mineCounter++;
            }
            if ((row-1) >= 0 && (col+1) < size) {
                if (hiddenBoard[row-1][col+1].equals(" * ")) mineCounter++;
            }
            //Above, below and to the sides of mine.
            if ((col-1) >= 0) {
                if (hiddenBoard[row][col-1].equals(" * ")) mineCounter++;
            }
            if ((row-1) >= 0) {
                if (hiddenBoard[row-1][col].equals(" * ")) mineCounter++;
            }
            if ((col+1) < size) {
                if (hiddenBoard[row][col+1].equals(" * ")) mineCounter++;
            }
            if ((row+1) < size) {
                if (hiddenBoard[row+1][col].equals(" * ")) mineCounter++;
            }
        }
        visibleBoard[row][col] = " " + mineCounter + " ";
        return mineCounter;
    }

    //These methods place or remove a flag from the board.
    public void placeFlag(int row, int col) {
        visibleBoard[row][col] = " ? ";
    }

    public void removeFlag(int row, int col) {
        visibleBoard[row][col] = "   ";
    }

    public Boolean changePlace(int row, int col){
        //change place of marker
          String position = visibleBoard[row][col];
        if (position.isEmpty()) {
            visibleBoard[row][col] = "x";
            return true;
        }
        return true;
    }


    public boolean checkWin()
            //not working right now

            //if returns true the game is over and player won
            //if returns false the game isn't over
            //should maybe return also if the player lost? Or maybe nothing
    {
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                if(visibleBoard[i][j].equals("0") /*==0*/)
                {
                    if(!hiddenBoard[i][j].equals("100") /*!=100*/)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }




 /*  private void setMines() {
        //ranodm placement of mines

        Random rnd = new Random();

         for(int i = 0; i < mineCells; i++){
             int x = rnd.nextInt(/*row kanske?);
             int y = rnd.nextInt(/*col kanske);

             if (!cells[x][y].isMine())
                 cells[x][y].setMine(true);
            else
                 i--;
        }
}*/

}
