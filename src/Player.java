public class Player {

    // Attributes for the player

    private String name;

    private int score;

    private int gamesWon;

    private String winner;


    // Constructors for the attributes

    public Player(){

    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void increaseScore(){
        score++;
        System.out.println(name + " has won" + score + " times!");
    }






}


