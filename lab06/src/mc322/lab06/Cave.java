package mc322.lab06;

import mc322.lab06.components.Component;

public class Cave {
    private Room rooms[][];

    public Cave() {
        this.rooms = new Room[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.rooms[i][j] = new Room();
            }
        }
    }

    public boolean isWithinBounds(Position position) {
        return position.getRow() >= 0 && position.getRow() < 4 &&
                position.getColumn() >= 0 && position.getColumn() < 4;
    }

    public Room roomAt(Position position) {
        if (isWithinBounds(position)) {
            return rooms[position.getRow()][position.getColumn()];
        } else {
            return null;
        }
    }

    // returns true only when component is successfully added
    public boolean addComponent(Component component) {
        if (component == null) {
            return false;
        }

        if (!isWithinBounds(component.getPosition())) {
            return false;
        }

        ErrorType err = roomAt(component.getPosition()).addComponent(component);
        if (err != ErrorType.NONE) {
            System.out.println("error");
        }

        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += (i + 1);
            for (int j = 0; j < 4; j++) {
                result += " ";
                Room room = rooms[i][j];
                if (!room.visited()) {
                    result += "-";
                } else {
                    result += room.getComponentString();
                }
            }
            result += "\n";
        }
        result += "  1 2 3 4";
        return result;
    }
}
