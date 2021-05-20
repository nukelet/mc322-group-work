package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public class Hole extends Component {
    public Hole(Cave cave, Position position) {
        super(cave, position);
    }

    @Override
    public void spawnSecondaryComponents() {
        cave.addComponent(new Breeze(cave, position.up()));
        cave.addComponent(new Breeze(cave, position.down()));
        cave.addComponent(new Breeze(cave, position.left()));
        cave.addComponent(new Breeze(cave, position.right()));
    }

    @Override
    public ComponentType getType() {
        return ComponentType.HOLE;
    }

    @Override
    public String singleLetterCode() {
        return "B";
    }

    @Override
    public String toString() {
        return "Hole(position=" + position + ")";
    }
}
