package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public abstract class Component {
    protected Cave cave;
    protected Position position;

    public Component(Cave cave, Position position) {
        this.cave = cave;
        this.position = position;
        spawnSecondaryComponents();
    }

    protected void spawnSecondaryComponents() {};

    public Position getPosition() {
        return position;
    }

    public abstract ComponentType getType();

    public abstract String singleLetterCode();

    @Override
    public abstract String toString();
}
