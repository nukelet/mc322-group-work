package mc322.lab06.components;

import mc322.lab06.Cave;
import mc322.lab06.Position;

public class Hero extends Component {
    private int arrows;
    private boolean arrowEquiped;

    public Hero(Cave cave, Position position) {
        super(cave, position);
        this.arrows = 1;
        this.arrowEquiped = false;
    }

    public void equipArrow() {
        if (!arrowEquiped && arrows >= 1) {
            arrows--;
            arrowEquiped = true;
        }
    }

    public void useArrow() {
        // TODO finish implementing (if needed)
        if (arrowEquiped) {
            arrowEquiped = false;
        }
    }

    public void collectGold() {
        // TODO implement method
    }

    @Override
    protected void spawnSecondaryComponents() {}

    @Override
    public ComponentType getType() {
        return ComponentType.HERO;
    }

    @Override
    public String singleLetterCode() {
        return "P";
    }

    @Override
    public String toString() {
        return "Hero(position=" + position + ")";
    }
}
