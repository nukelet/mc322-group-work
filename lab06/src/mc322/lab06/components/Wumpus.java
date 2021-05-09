package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public class Wumpus extends Component {
    public Wumpus(Cave cave, Position position) {
        super(cave, position);
    }

    @Override
    public void spawnSecondaryComponents() {
        cave.addComponent(new Stink(cave, position.up()));
        cave.addComponent(new Stink(cave, position.down()));
        cave.addComponent(new Stink(cave, position.left()));
        cave.addComponent(new Stink(cave, position.right()));
    }

    @Override
    public ComponentType getType() {
        return ComponentType.WUMPUS;
    }

    @Override
    public String singleLetterCode() {
        return "W";
    }

    @Override
    public String toString() {
        return "Wumpus(position=" + position + ")";
    }
}
