package mc322.lab06;

import java.util.Scanner;

public class AppMundoWumpus {
    private static Game game;
    public static Scanner input = new Scanner(System.in);

    public static void initializeGame(String csvPath) {
        System.out.println("Insert player name:");
        String playerName = input.nextLine();
        System.out.println();
        game = new Game(playerName, csvPath);
    }

    public static void runGame() {
        while (!game.isOver()) {
            System.out.println(game);
            System.out.print("Next action [w, s, a, d, k, c, q, ?]: ");
            String nextAction = input.nextLine();
            handleInput(nextAction);
            game.updateGameState();
        }
    }

    private static void handleInput(String action) {
        CommandType command = null;
        switch (action) {
        case "w":
            System.out.println("Moving up...");
            command = CommandType.MOVE_UP;
            break;
        case "s":
            System.out.println("Moving down...");
            command = CommandType.MOVE_DOWN;
            break;
        case "a":
            System.out.println("Moving left...");
            command = CommandType.MOVE_LEFT;
            break;
        case "d":
            System.out.println("Moving right...");
            command = CommandType.MOVE_RIGHT;
            break;
        case "k":
            System.out.println("Equipping arrow...");
            command = CommandType.EQUIP_ARROW;
            break;
        case "c":
            System.out.println("Capturing gold...");
            command = CommandType.COLLECT_GOLD;
            break;
        case "q":
            System.out.println("Exiting...");
            command = CommandType.EXIT_GAME;
            break;
        case "?":
            System.out.println("w - move up");
            System.out.println("s - move down");
            System.out.println("a - move left");
            System.out.println("d - move right");
            System.out.println("k - equip arrow");
            System.out.println("c - capture gold");
            System.out.println("q - quit game");
            System.out.println("? - print help");
            break;
        }

        if (command != null) {
            game.doMove(command);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("args: <path-to-csv>");
            System.exit(1);
        }

        initializeGame(args[0]);
        runGame();

        input.close();
    }
}
