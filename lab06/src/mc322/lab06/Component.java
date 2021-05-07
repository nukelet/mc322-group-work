package mc322.lab06;

class Component {
    private Cave cave;
    private Position position;
    private ComponentType type = null;

    public Component(Cave cave, Position position) {
        this.cave = cave;
        this.position = position;
        spawnSecondaryComponents();
    }

    // this method is supposed to be overridden
    public void spawnSecondaryComponents() {

    }

    public Position getPosition() {
        return this.position;
    }

    public ComponentType getType() {
        return this.type;
    }

    // this method is supposed to be overridden
    public String toString() {
        return null;
    }
}
