public class Player {


    // Attributes for the player

    String name;

    int score;

    int gamesWon;

    int gamesLost;

    // Constructors for the attributes

    public Player(String name, int score, int gamesWon, int gamesLost) {
        this.name = name;
        this.score = score;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
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

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    String name;
    int score;

}


