public class Main {
    public static void main(String[] args) {
        Board board = new Board(8);

        board.printBoard();

        System.out.println("Welcome to MineSweep! Try to find all the numbers in the board without hitting any bombs. Good luck!");
        Game game = new Game();
        game.startGame();




    }
}