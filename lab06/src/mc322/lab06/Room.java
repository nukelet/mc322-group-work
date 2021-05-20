package mc322.lab06;

import java.util.ArrayList;
import java.util.HashMap;

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
        ComponentType[] incompatibleTypes = {
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
            } else {
                components.add(component);
            }
            break;
        default:
            if (component.getType() == ComponentType.HERO) {
                setAsVisited();
            }
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
        return components;
    }

    public boolean removeComponent(Component component) {
        return components.remove(component);
    }

    public boolean removeComponentType(ComponentType type) {
        return components.removeIf(c -> c.getType() == type);
    }

    public boolean isEmpty() {
        return components.isEmpty();
    }

    public String getComponentString() {
        HashMap<ComponentType, Integer> priority = new HashMap<ComponentType, Integer>() {{
            put(ComponentType.GOLD, 3);
            put(ComponentType.WUMPUS, 3);
            put(ComponentType.HOLE, 3);
            put(ComponentType.HERO, 2);
            put(ComponentType.STINK, 1);
            put(ComponentType.BREEZE, 0);
        }};

        Component maxPriority = null;
        for (Component c : components) {
            if (maxPriority == null) {
                maxPriority = c;
            } else if (priority.get(c.getType()) > priority.get(maxPriority.getType())) {
                maxPriority = c;
            }
        }

        String str = maxPriority == null ? "#" : maxPriority.singleLetterCode();
        return str;
    }

    @Override
    public String toString() {
        String componentsListString = "[";
        for (int i = 0; i < components.size(); i++) {
            componentsListString += components.get(i).singleLetterCode();
            componentsListString += (i == components.size() - 1) ? "]" : ", ";
        }
        return "Room(components=" + componentsListString + ", visited=" + visited + ")";
    }
}
