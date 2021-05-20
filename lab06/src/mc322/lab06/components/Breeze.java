package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public class Breeze extends Component {
    public Breeze(Cave cave, Position position) {
        super(cave, position);
    }

    @Override
    public ComponentType getType() {
        return ComponentType.BREEZE;
    }

    @Override
    public String singleLetterCode() {
        return "b";
    }

    @Override
    public String toString() {
        return "Breeze(position=" + position + ")";
    }
}
