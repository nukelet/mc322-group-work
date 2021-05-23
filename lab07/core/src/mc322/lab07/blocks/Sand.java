package mc322.lab07.blocks;

import com.badlogic.gdx.math.Vector2;
import mc322.lab07.World;

public class Sand extends BlockEntity {
    public Sand(Vector2 position, World world) {
        super(position, world);
    }

    public void updateState() {
        if (world.getBlockDown(position, 1) == null) {

        }
    }
}
