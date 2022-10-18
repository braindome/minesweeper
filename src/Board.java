public class Board {

    int size;
    private String[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "";
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String position = board[i][j];
                System.out.print(position.isEmpty() ? "__" : position);
                System.out.print("|");
            }
            System.out.println();
        }
    }
}
