package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public class Gold extends Component {
    public Gold(Cave cave, Position position) {
        super(cave, position);
    }

    @Override
    protected void spawnSecondaryComponents() {}

    @Override
    public ComponentType getType() {
        return ComponentType.GOLD;
    }

    @Override
    public String singleLetterCode() {
        return "O";
    }

    @Override
    public String toString() {
        return "Gold(position=" + position + ")";
    }
}
