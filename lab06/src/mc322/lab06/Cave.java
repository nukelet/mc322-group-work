package mc322.lab06;

import mc322.lab06.components.*;

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

    public boolean isPositionInside(Position position) {
        // TODO rename this method (?)
        return position.getRow() >= 1 && position.getRow() <= 4 &&
                position.getColumn() >= 1 && position.getColumn() <= 4;
    }

    // returns true only when component is successfully added
    public boolean addComponent(Component component) {
        if (!isPositionInside(component.getPosition())) {
            return false;
        }

        // TODO add checks about the component


        return true;
    }

    @Override
    public String toString() {
        // TODO finish implementing
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += (i + 1);
            for (int j = 0; j < 4; j++) {
                result += " ";
                Room room = rooms[i][j];
                if (!room.visited()) {
                    result += "-";
                } else {

                }
            }
            result += "\n";
        }
        result += "  1 2 3 4";
        return result;
    }
}
