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

            while (true) {
                String answer = sc.nextLine();
                if (answer.equals("yes")) {
                    //Creating new board
                    createBoard();
                    playGame = true;
                    break;

                } else if (answer.equals("no")) {
                    System.out.println("Thank you for playing!");
                    keepPlaying = false;

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
                    // board.startingAreaClear(rowNumber, colNumber);
                    firstMove = false;
                    board.mineGenerator();
                }

                board.minesAround(rowNumber, colNumber);

                if (board.hasHitMine(rowNumber, colNumber)) {
                    board.printHiddenBoard();
                    System.out.println("You lost");
                    keepPlaying = false;
                    break;
                }

                board.printVisibleBoard();


                //fixa till checkwin så att det stämmer på riktigt
                //denna metoden ska ta emot true eller false
                //när någon spelat och förlorat/vunnit så ska det bli false
                if(board.checkWin()){
                    board.printHiddenBoard();
                    player.increaseScore();
                }


            } catch (Exception e) {
                System.out.println("nu blev det fel");

                //ta bort sen men just nu - skriver man fel så avslutas omgången
                playGame=false;
            }
        }
    }
}
