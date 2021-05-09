package mc322.lab06;

import java.util.Scanner;

import mc322.lab06.components.*;

public class AppMundoWumpus {
    public static void runGame() {
        while (true) {
            System.out.print("Next action [w, s, a, d, k, c, q, ?]: ");
            Scanner input = new Scanner(System.in);
            String nextAction = input.nextLine();
            switch (nextAction) {
            case "w":
                System.out.println("Moving up...");
                break;
            case "s":
                System.out.println("Moving down...");
                break;
            case "a":
                System.out.println("Moving left...");
                break;
            case "d":
                System.out.println("Moving right...");
                break;
            case "k":
                System.out.println("Equipping arrow...");
                break;
            case "c":
                System.out.println("Capturing gold...");
                break;
            case "q":
                System.out.println("Exiting...");
                input.close();
                return;
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
        }
    }

    public static void main(String[] args) {
        Cave cave = new Cave();
        System.out.println("# Initial cave state");
        System.out.println(cave);
        System.out.println();

        Game game = new Game("Gabriel", cave);
        System.out.println("# Initial game state");
        System.out.println(game);
        System.out.println();

        System.out.println("# Single letter code and textual representation of components");
        Component[] allComponents = new Component[6];
        allComponents[0] = new Wumpus(cave, new Position(2, 3));
        allComponents[1] = new Breeze(cave, new Position(3, 3));
        allComponents[2] = new Gold(cave, new Position(2, 1));
        allComponents[3] = new Hero(cave, new Position(1, 1));
        allComponents[4] = new Stink(cave, new Position(4, 4));
        allComponents[5] = new Hole(cave, new Position(3, 4));
        for (Component component : allComponents) {
            System.out.println(component.singleLetterCode() + " - " + component);
        }
        System.out.println();

        System.out.println("# Action control");
        runGame();
        System.out.println();
    }
}
