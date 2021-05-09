package mc322.lab06;

import java.util.ArrayList;

import mc322.lab06.components.*;

public class Room {
    private ArrayList<Component> components;
    private boolean visited;

    public Room() {
        this.components = new ArrayList<Component>();
        this.visited = false;
    }

    public boolean visited() {
        return visited;
    }

    public void setAsVisited() {
        visited = true;
    }

    public ErrorType addComponent(Component component) {
        ComponentType[] incompatibleTypes = 
            {
                    ComponentType.GOLD,
                    ComponentType.HOLE,
                    ComponentType.WUMPUS
            };

        switch (component.getType()) { 
        case GOLD:
        case HOLE:
        case WUMPUS:
            if (hasComponentType(incompatibleTypes)) {
                return ErrorType.INVALID_COMPONENTS;
            }
            break;
        default:
            components.add(component);
        }

        return ErrorType.NONE;
    }

    public boolean hasComponentType(ComponentType type) {
        for (Component c : components) {
            if (c.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public boolean hasComponentType(ComponentType[] componentList) {
        for (ComponentType c : componentList) {
            if (hasComponentType(c)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void removeComponent(Component component) {

    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    @Override
    public String toString() {
        // TODO finish implementing the components part
        return "Room(components=" + ", visited=" + visited + ")";
    }
}
