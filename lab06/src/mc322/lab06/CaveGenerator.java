package mc322.lab06;
import mc322.lab06.components.*;

import java.util.HashMap;

public class CaveGenerator {
    private static HashMap<ComponentType, Integer> typeCount =
        new HashMap<ComponentType, Integer>() {{ 
            put(ComponentType.HOLE, 0);
            put(ComponentType.WUMPUS, 0);
            put(ComponentType.GOLD, 0);
        }};

    public static Cave generateCaveFromCsv(String csvPath) {
        Cave cave = new Cave();

        CSVHandling csv = new CSVHandling();
        csv.setDataSource(csvPath);
        String[][] commands = csv.requestCommands();
        for (String[] line : commands) {
            String[] position = line[0].split(":");
            int posX = Integer.parseInt(position[0]) - 1;
            int posY = Integer.parseInt(position[1]) - 1;
            Position pos = new Position(posX, posY);

            String componentString = line[1];
            Component component = getComponent(componentString, cave, pos);
            if (component != null) {
                cave.addComponent(component);
                int count = typeCount.get(component.getType());
                typeCount.put(component.getType(), count + 1);
            }
        }
        
        if (checkComponentTypeCount()) {
            return cave;
        } else {
            System.err.println("Error generating cave from csv: invalid component count");
            System.exit(1);
        }

        return null;
    }

    /* Checks the number of components to be added to the cave
     * Wumpus: 1
     * Gold: 1
     * Hole: 2 or 3
     *
     * @returns true if the component count is valid, false otherwise
     */
    private static boolean checkComponentTypeCount() {
        if (typeCount.get(ComponentType.WUMPUS) != 1) {
            return false;
        }

        if (typeCount.get(ComponentType.GOLD) != 1) {
            return false;
        }

        int holeCount = typeCount.get(ComponentType.HOLE);
        if (holeCount < 2 || holeCount > 3) {
            return false;
        }

        return true;
    }

    private static Component getComponent(String componentString, Cave cave, Position position) {
        Component component = null;
        switch (componentString) {
            case "P":
                // TODO: discuss removing this
                // the responsibility of creating a Hero component should
                // be delegated to the Game class, i think
                // component = new Hero(cave, position);
                break;
            case "O":
                component = new Gold(cave, position);
                break;
            case "B":
                component = new Hole(cave, position);
                break;
            case "W":
                component = new Wumpus(cave, position);
                break;
            case "_":
                component = null;
                break;
            default:
                System.err.println("Error generating cave from csv; invalid token: " + componentString);
        }

        return component;
    }

    public static void main(String[] args) {
        Cave cave = generateCaveFromCsv("data/cave1.csv");
        System.out.println(cave);
    }
}
