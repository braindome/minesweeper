import java.util.Scanner;

public class Game {

    Boolean playGame = true;

    public Game (){
        //create player
        //createPlayer();

// create board
         createBoard();
    }

    public void createPlayer() {

            //create players
            Scanner sc = new Scanner(System.in);


            System.out.println("Player, please write your name");
            String name = sc.nextLine();



    }


    public void createBoard(){

        Scanner sc = new Scanner(System.in);
        System.out.println("How big do you want the board to be?");
        int size = sc.nextInt();


        Board board = new Board(size);

        board.printBoard();



    }


    public void startGame(){
        while (playGame){

            //playing game while playGame is true

            play();

            System.out.println("Do you want to play again? Write yes or no!");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();

            if (answer.equals("no")){
                playGame = false;
            }


        }
    }



    public void play(){

        //here we put in what we want to play

        int rowNumber;
        int colNumber;

        System.out.println(/*player.getName() + */  ", choose a row ");
        Scanner sc = new Scanner(System.in);
        rowNumber = sc.nextInt() - 1;
        System.out.println(/*player.getName() + */  ", choose a col ");
        colNumber = sc.nextInt() - 1;

    }


}
