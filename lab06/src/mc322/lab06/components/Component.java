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

    // this method is supposed to be overridden
    protected void spawnSecondaryComponents() {};

    public Position getPosition() {
        return this.position;
    }

    // this method is supposed to be overridden
    public abstract ComponentType getType();

    // this method is supposed to be overridden
    public abstract String singleLetterCode();

    // this method is supposed to be overridden
    public abstract String toString();
}
