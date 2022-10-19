public class Board {

    int size;
    private String[][] board;

    public Board(int size) {

        //create a new board
        this.size = size;
        this.board = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
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
}
