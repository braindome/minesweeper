import java.util.Random;

public class Board {
    int size;

    final String[][] visibleBoard;                  //Visible board.
    private final String[][] hiddenBoard;
    private int minesAmount = 0;

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
    }

    //Displays the public game board.
    public void printVisibleBoard() {
        //print board
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i + 1) + "  ");
            }
            if (i > 9) {
                System.out.print(" " + (i + 1) + " ");
            }
        }

        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i + 1) + " ");
            }
            if (i > 9) {
                System.out.print(" " + (i + 1));
            }
            for (int j = 0; j < size; j++) {
                String position = visibleBoard[i][j];
                System.out.print(position.isEmpty() ? "   " : position);
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
                System.out.print(" " + (i + 1) + "  ");
            }
            if (i > 9) {
                System.out.print(" " + (i + 1) + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(" " + (i + 1) + " ");
            }
            if (i > 9) {
                System.out.print(" " + (i + 1));
            }
            for (int j = 0; j < size; j++) {
                String position = hiddenBoard[i][j];
                System.out.print(position.isEmpty() ? "   " : position);
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

            if (!visibleBoard[mineRow][mineCol].equals(" e ")) {
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
    //FIXED: Out of Bounds exception handling. Needs more testing.
    public void startingAreaClear(int row, int col) {
        //Bytte ut X mot e för tydlighet, tyckte det var svårt att se

       visibleBoard[row][col] = " e ";
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    visibleBoard[row + i][col + j] = " e ";
                } catch (Exception e) {
                }
            }
        }
    }

    //Ska vi ha kvar denna eller ta bort den?

    //Checks EVERY SQUARE for proximity to mines, adds them to a counter and returns the value.
    public int boardHints() {
        int mineCounter = 0;
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                if (!hiddenBoard[i][j].equals(" * ")) {
                    //Diagonal cells next to mine.
                    if ((i - 1) >= 0 && (j - 1) >= 0) {
                        if (hiddenBoard[i - 1][j - 1].equals(" * ")) mineCounter++;
                    }
                    if ((i - 1) >= 0 && (j + 1) <= size) {
                        if (hiddenBoard[i - 1][j + 1].equals(" * ")) mineCounter++;
                    }
                    if ((i + 1) <= size && (j - 1) >= 0) {
                        if (hiddenBoard[i + 1][j - 1].equals(" * ")) mineCounter++;
                    }
                    if ((i - 1) >= 0 && (j + 1) <= size) {
                        if (hiddenBoard[i - 1][j + 1].equals(" * ")) mineCounter++;
                    }
                    //Above, below and to the sides of mine.
                    if ((j - 1) >= 0) {
                        if (hiddenBoard[i][j - 1].equals(" * ")) mineCounter++;
                    }
                    if ((i - 1) >= 0) {
                        if (hiddenBoard[i - 1][j].equals(" * ")) mineCounter++;
                    }
                    if ((j + 1) <= size) {
                        if (hiddenBoard[i][j + 1].equals(" * ")) mineCounter++;
                    }
                    if ((i + 1) <= size) {
                        if (hiddenBoard[i + 1][j].equals(" * ")) mineCounter++;
                    }
                }
            }
        }
        return mineCounter;
    }

    //Reveals amount of mines surrounding given square and replaces empty square with number of mines.
    //FIXED: Out of Bounds Exception handling. Needs more testing.
    public void minesAround(int row, int col) {
        int mineCounter = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    if (hiddenBoard[row + i][col + j].equals(" * ")) {
                        mineCounter = mineCounter + 1;
                    }
                } catch (Exception e) {
                }
            }
        }
        visibleBoard[row][col] = " " + mineCounter + " ";
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

        public boolean checkWin () {
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

        //flood fill algorithm to find the safe "cells"
        public void floodFill ( int row, int col){
//ska vi ha denna?

        }
    }
