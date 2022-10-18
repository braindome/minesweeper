public class Main {
    public static void main(String[] args) {

        System.out.println("          (_)                                                  ");
        System.out.println(" _ __ ___  _ _ __   ___  _____      _____  ___ _ __   ___ _ __ ");
        System.out.println("| '_ ` _ \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|");
        System.out.println("| | | | | | | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |   ");
        System.out.println("|_| |_| |_|_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|   ");
        System.out.println("                                              | |              ");
        System.out.println("                                              |_|              \n\n");


        System.out.println("Welcome to MineSweep! Try to find all the numbers in the board without hitting any bombs. Good luck!");
        Game game = new Game();
        game.startGame();



    }
}