package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Room;
import mc322.lab06.Position;

public class Hero extends Component {
    private int arrows;
    private boolean arrowEquipped;

    public Hero(Cave cave, Position position) {
        super(cave, position);
        this.arrows = 1;
        this.arrowEquipped = false;
    }

    public int getArrowCount() {
        return arrows;
    }

    public boolean isArrowEquipped() {
        return arrowEquipped;
    }

    public boolean equipArrow() {
        if (!arrowEquipped && arrows >= 1) {
            arrows--;
            arrowEquipped = true;
            return true;
        } else {
            return false;
        }
    }

    public void useArrow() {
        if (arrowEquipped) {
            arrowEquipped = false;
        }
    }

    /* Moves the Hero component to the specified position
     * @params: destination
     * @returns: true if the move was successful and false otherwise
     */
    public boolean move(Position destination) {
        // TODO: consider refactoring this
        // i think that the fact that we need to do this to move the Component
        // might mean that we probably don't have the right abstraction
        Position oldPos = position;
        position = destination;

        if (!cave.addComponent(this)) {
            position = oldPos;
            return false;
        } else {
            Room oldPlayerRoom = cave.roomAt(oldPos);
            oldPlayerRoom.removeComponent(this);
            return true;
        }
    }

    public boolean moveUp() {
        Position destination = position.up();
        return move(destination);
    }

    public boolean moveDown() {
        Position destination = position.down();
        return move(destination);
    }

    public boolean moveLeft() {
        Position destination = position.left();
        return move(destination);
    }

    public boolean moveRight() {
        Position destination = position.right();
        return move(destination);
    }

    /* Collects Gold from the Room the Hero is in
     * @returns: true if Gold could be collected, false otherwise
     */
    public boolean collectGold() {
        Room currentRoom = cave.roomAt(position);
        return currentRoom.removeComponentType(ComponentType.GOLD);
    }

    @Override
    public ComponentType getType() {
        return ComponentType.HERO;
    }

    @Override
    public String singleLetterCode() {
        return "P";
    }

    @Override
    public String toString() {
        return "Hero(position=" + position + ")";
    }
}
