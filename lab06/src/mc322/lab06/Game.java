package mc322.lab06;

import mc322.lab06.components.*;

import java.lang.Math;

enum GameState {
    GAME_ONGOING,
    GAME_OVER,
}

public class Game {
    private String playerName;
    private int score;
    private GameState state;

    private boolean isGoldCaptured;
    private boolean isShotPrepared;

    private Hero player;

    private Cave cave;

    public Game(String playerName, String csvPath) {
        this.playerName = playerName;
        this.score = 0;
        this.state = GameState.GAME_ONGOING;

        this.isGoldCaptured = false;
        this.isShotPrepared = false;

        this.cave = CaveGenerator.generateCaveFromCsv(csvPath);
        if (this.cave == null) {
            System.exit(1);
        }
        this.player = CaveGenerator.getHero();
    }

    public boolean doMove(CommandType move) {
        switch (move) {
            case MOVE_UP:
                return player.moveUp();
            case MOVE_DOWN:
                return player.moveDown();
            case MOVE_LEFT:
                return player.moveLeft();
            case MOVE_RIGHT:
                return player.moveRight();
            case COLLECT_GOLD:
                if (player.collectGold()) {
                    isGoldCaptured = true;
                    return true;
                } else {
                    return false;
                }
            case EQUIP_ARROW:
                return player.equipArrow();
            case EXIT_GAME:
                setState(GameState.GAME_OVER);
                return true;
            default:
                return false;
        }
    }

    public void updateGameState() {
        Room playerRoom = cave.roomAt(player.getPosition());
        if (playerRoom.hasComponentType(ComponentType.HOLE)) {
            score -= 1000;
            setState(GameState.GAME_OVER);
            System.out.println(toString());
            System.out.println("You fell in a hole! Game over :(");
            return;
        } else if (playerRoom.hasComponentType(ComponentType.WUMPUS)) {
            if (isShotPrepared) {
                if (Math.random() < 0.5) {
                    playerRoom.removeComponentType(ComponentType.WUMPUS);
                    score += 500;
                    System.out.println("You killed the Wumpus!");
                    return;
                } else {
                    score -= 1000;
                    setState(GameState.GAME_OVER);
                    System.out.println(toString());
                    System.out.println("The Wumpus killed you! Game over :(");
                    return;
                }
            } else {
                score -= 1000;
                setState(GameState.GAME_OVER);
                System.out.println(toString());
                System.out.println("The Wumpus killed you! Game over :(");
                return;
            }
        }

        if (playerRoom.hasComponentType(ComponentType.BREEZE)) {
            System.out.println("You feel a cold breeze running through the room...");
        } 

        if (playerRoom.hasComponentType(ComponentType.STINK)) {
            System.out.println("The room is filled with a horrid stench...");
        }

        if (isShotPrepared) {
            player.useArrow();
            score -= 100;
        }
        
        // triggers in turns where the player is equipping the arrow;
        if (player.isArrowEquipped()) {
            isShotPrepared = true;
            return;
        } else {
            isShotPrepared = false;
        }

        // movement penalty
        score -= 15;

        if (player.getPosition().isEqual(0, 0)) {
            if (isGoldCaptured) {
                score += 1000;
                setState(GameState.GAME_OVER);
                System.out.println(toString());
                System.out.println("You won!");
                return;
            } else {
                System.out.println("You must get the gold before leaving!");
            }
        }
    }

    private void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public boolean isOver() {
        return state == GameState.GAME_OVER;
    }

    @Override
    public String toString() {
        String result = cave.toString() + "\n\n";
        result += "Player: " + playerName + "\n";
        result += "Score: " + score + "\n";
        result += "Arrows: " + player.getArrowCount() + "\n";
        result += "Has gold: " + (isGoldCaptured ? "yes" : "no") + "\n";
        return result;
    }
}
