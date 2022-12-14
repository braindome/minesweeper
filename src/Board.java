import java.util.Random;

public class Board {
    int size;

    final String[][] visibleBoard;
    private final String[][] hiddenBoard;
    private int minesAmount = 0;
    Random random = new Random();
    private static final String BOMB = "\uD83D\uDCA3";

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
    }

    //Displays the public game board.
    public void printVisibleBoard() {
        //print board
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i+1) + "  ");
            }
            if (i > 9) {
                System.out.print(" " + (i+1) + " ");
            }
        }

        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i+1) + " ");
            }
            if (i > 9) {
                System.out.print(" " + (i+1));
            }
            for (int j = 0; j < size; j++) {
                String position = visibleBoard[i][j];
                System.out.print(position.isEmpty() ? "XXX" : position);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public void printHiddenBoard() {
        //print hidden board when the user looses or wins
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i+1) + "  ");
            }
            if (i > 9) {
                System.out.print(" " + (i+1) + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i+1) + " ");
            }
            if (i > 9) {
                System.out.print(" " + (i+1));
            }
            for (int j = 0; j < size; j++) {
                String position = hiddenBoard[i][j];
                System.out.print(position.isEmpty() ? "   " : BOMB + " ");
                System.out.print("|");
            }
            System.out.println();
        }
    }

    //Generates mines across the game board. Fixed proportion at 20% of the game area. UPDATE: mines are not generated within the 3x3 starting area.
    public void mineGenerator() {

        int numOfMines = (size * size) / 5;

        for (int i = 0; i <= numOfMines; i++) {
            int mineRow = random.nextInt(size);
            int mineCol = random.nextInt(size);

            if (!visibleBoard[mineRow][mineCol].equals(" X ")) {
                hiddenBoard[mineRow][mineCol] = " * ";
                minesAmount++;
            }

        }
    }

    public Boolean hasHitMine(int row, int col) {
        return hiddenBoard[row][col] == " * ";
    }

    //To call after the player's first move. It generates a 3x3 square around the first position entered by the player.
    //This square is always mine-free, and it starts the game.
    public void startingAreaClear(int row, int col) {

        visibleBoard[row][col] = " X ";
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j <2; j++) {
                try {
                    visibleBoard[row+i][col+j] = " X ";
                } catch (Exception e) {}
            }
        }
    }


    //Shows number of mines around each square in the starting safe area.
    //To be called right after minefield generator.
    public void startingAreaHints(int row, int col) {
        visibleBoard[row][col] = " X ";
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j <2; j++) {
                try {
                    int x = row+i;
                    int y = col+j;
                    visibleBoard[row+i][col+j] = " " + minesAround(x, y) + " ";
                } catch (Exception e) {}
            }
        }
    }

    public void revealNearbyTiles(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            visibleBoard[row][col] = " " + minesAround(row, col) + " ";
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((i == 0 || j == 0) && !(i == 0 && j == 0)) {
                        try {
                            if (minesAround(row+i, col+j) == 0 && visibleBoard[row+i][col+j].equals("XXX") && !hiddenBoard[row+i][col+j].equals(" * ")) {
                                System.out.println("second if");
                                visibleBoard[row+i][col+j] = " " + minesAround(row+i, col+j) + " ";
                                revealNearbyTiles(row+i, col+j);
                            }
                        } catch (Exception e) {}
                    }
                }
            }
        }
    }

    //IMPROVED minesAround using more loops
    public int minesAround(int row, int col) {
        int mineCounter = 0;
        //Counters go through a 3x3 grid surrounding the chosen square.
        for (int i = -1; i < 2; i++ ) {
            for (int j = -1; j < 2; j++) {
                //Try-catch block if counters try to access forbidden indexes.
                try {
                    //Adds 1 to counter if nearby square has a mine.
                    if (hiddenBoard[row+i][col+j].equals(" * ")) {
                        mineCounter = mineCounter + 1;
                    }
                } catch (Exception e) {}
            }
        }
        return mineCounter;
    }

    public Boolean changePlace(int row, int col) {
        //change place of marker
        String position = visibleBoard[row][col];
        if (position.isEmpty() || position.equals(" e ")) {
            visibleBoard[row][col] = "x";

            return true;
        } else if (position.contains(position)) {
            System.out.println("Position taken. Try again:");
        }
        return true;
    }

    public boolean checkWin() {
        //Calculate how many "cells" there are and also subtract the mine "cells"
        //When user explore a "cell" substract from how many emptyCellsLeft
        //When we hit 0 amount of empty "cells", that means there is no more "cells" to open and user won

        int emptyCellsLeft = (size * size) - minesAmount;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String cellInfo = visibleBoard[row][col];
                if (!cellInfo.isEmpty() && !cellInfo.equals(" e ") && Integer.parseInt(cellInfo.replaceAll("\\s+", "")) >= 0) {
                    emptyCellsLeft--;
                }
            }
        }
        return emptyCellsLeft == 0;
    }
}
