package mc322.lab07.blocks;

import com.badlogic.gdx.math.Vector2;
import mc322.lab07.World;

public class Rock extends BlockEntity {
    public Rock(Vector2 position, World world) {
        super(position, world);
    }

    public void updateState() {
        this.isUpdated = true;
    }
}
