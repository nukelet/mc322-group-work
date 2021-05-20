package mc322.lab06;

import mc322.lab06.components.*;

public class CaveGenerator {
    private static Hero hero;

    /**
     * Generates a cave from a CSV and checks its validness. A cave is valid
     * if it has 1 Hero at (1,1), 1 Wumpus, 1 Gold and 2 or 3 Holes, with none
     * of them sharing the same position.
     *
     * @param csvPath
     * @return correctly generated cave or null in case of failure
     */
    public static Cave generateCaveFromCsv(String csvPath) {
        hero = null;
        Cave cave = new Cave();

        CSVHandling csvHandling = new CSVHandling();
        csvHandling.setDataSource(csvPath);
        String[][] commands = csvHandling.requestCommands();

        int heroCount = 0;
        int goldCount = 0;
        int wumpusCount = 0;
        int holeCount = 0;

        for (String[] line : commands) {
            String[] strPosition = line[0].split(":");
            int row = Integer.parseInt(strPosition[0]) - 1;
            int column = Integer.parseInt(strPosition[1]) - 1;
            Position position = new Position(row, column);

            String strComponent = line[1];
            switch (strComponent) {
            case "P":
                heroCount++;
                if (position.getRow() != 0 || position.getColumn() != 0) {
                    System.err.println("Hero can only start at cave entrance");
                    return null;
                }
                hero = new Hero(cave, position);
                if (!cave.addComponent(hero)) {
                    System.err.println("Failed adding Hero to " + position);
                    return null;
                }
                break;
            case "W":
                wumpusCount++;
                if (!cave.addComponent(new Wumpus(cave, position))) {
                    System.err.println("Failed adding Wumpus to " + position);
                    return null;
                }
                break;
            case "O":
                goldCount++;
                if (!cave.addComponent(new Gold(cave, position))) {
                    System.err.println("Failed adding Gold to " + position);
                    return null;
                }
                break;
            case "B":
                holeCount++;
                if (!cave.addComponent(new Hole(cave, position))) {
                    System.err.println("Failed adding Hole to " + position);
                    return null;
                }
                break;
            case "_":
                break;
            default:
                System.err.println("Error generating cave from CSV; invalid token: " + strComponent);
                return null;
            }
        }

        if (heroCount != 1 || goldCount != 1 || wumpusCount != 1 || (holeCount != 2 && holeCount != 3)) {
            System.err.println("Error generating cave from CSV: invalid component count");
            return null;
        }

        return cave;
    }

    /**
     * Gets the hero created during the last generation of a cave. This hero
     * only represents the correct hero if the generation method didn't return
     * null.
     *
     * @return hero created during the last generation of a cave.
     */
    public static Hero getHero() {
        return hero;
    }

    public static void main(String[] args) {
        Cave cave = generateCaveFromCsv("lab06/data/cave1.csv");
        System.out.println(cave);
    }
}
