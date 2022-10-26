import java.util.Scanner;

public class Game {

    private Player player = new Player();

    private Board board;

    //playGame == true as long as the round is going
    Boolean playGame = true;

    //keepPlaying == true as long as the player wants to play
    Boolean keepPlaying = true;
    Boolean firstMove = true;

    public Game (){
        //create player
        createPlayer();

        //create board
        createBoard();
    }

    public void createPlayer() {
            //create players
            Scanner sc = new Scanner(System.in);

            System.out.println("Player, please write your name");
            String name = sc.nextLine();
            player.setName(name);

            System.out.println(player.getName());
    }

    public void createBoard(){
        try {

        Scanner sc = new Scanner(System.in);
        System.out.println("How big do you want the board to be?");
        int size = sc.nextInt();

        board = new Board(size);
        board.printVisibleBoard();

        board.mineGenerator();

        } catch (Exception e) {
            System.out.println("Invalid input; try again.");
        }
    }

    public void startGame(){

        while (keepPlaying){
            //playing game while keepPlaying is true

            play();

            System.out.println("Do you want to play again? Write yes or no!");
            Scanner sc = new Scanner(System.in);

            if (keepPlaying = true) {
                String answer = sc.nextLine();
                if (answer.equals("yes")) {
                    //Creating new board
                    createBoard();
                    playGame = true;
                    keepPlaying = true;
                    firstMove = true;

                    //break;

                } else if (answer.equals("no")) {
                    System.out.println("Thank you for playing!");
                    keepPlaying = false;
                    firstMove = false;
                    break;
                } else {
                    System.out.println("Please write yes or no.");
                }
            }
        }
    }

    public void play(){
            //here we put in what we want to play
        while (playGame){
            try {
                int rowNumber;
                int colNumber;

                System.out.println(player.getName() +   ", choose a row ");
                Scanner sc = new Scanner(System.in);
                rowNumber = sc.nextInt() - 1;
                System.out.println(player.getName() +  ", choose a col ");
                colNumber = sc.nextInt() - 1;

                board.changePlace(rowNumber, colNumber);
                if(firstMove){
                    board.startingAreaClear(rowNumber, colNumber);
                    board.mineGenerator();
                    firstMove = false;
                }

                board.minesAround(rowNumber, colNumber);

                if (board.hasHitMine(rowNumber, colNumber)) {
                    board.printHiddenBoard();
                    System.out.println("You lost");
                    keepPlaying = false;
                    break;
                } else if(board.checkWin()){
                    board.printHiddenBoard();
                    player.increaseScore();
                }

                board.printVisibleBoard();

            } catch (Exception e) {
                System.out.println("Wrong input, try again");
            }
        }
    }
}
