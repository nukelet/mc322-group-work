package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public class Stink extends Component {
    public Stink(Cave cave, Position position) {
        super(cave, position);
    }

    @Override
    public ComponentType getType() {
        return ComponentType.STINK;
    }

    @Override
    public String singleLetterCode() {
        return "f";
    }

    @Override
    public String toString() {
        return "Stink(position=" + position + ")";
    }
}
