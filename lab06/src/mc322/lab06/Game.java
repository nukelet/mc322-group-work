package mc322.lab06;

public class Game {
    private String playerName;
    private int score;
    private Cave cave;

    public Game(String playerName, Cave cave) {
        this.playerName = playerName;
        this.score = 0;
        this.cave = cave;
    }

    @Override
    public String toString() {
        String result = cave.toString() + "\n\n";
        result += "Player: " + playerName + "\n";
        result += "Score: " + score;
        return result;
    }
}
