package mc322.lab07.blocks;

import com.badlogic.gdx.math.Vector2;

import mc322.lab07.World;

public abstract class BlockEntity {
    protected Vector2 position;
    protected World world;
    public boolean isUpdated;

    public BlockEntity(Vector2 position, World world) {
        this.position = position;
        this.world = world;
    }
    
    public Vector2 getPosition() {
        return this.position;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public abstract void updateState();

}
