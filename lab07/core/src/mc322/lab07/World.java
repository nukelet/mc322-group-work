package mc322.lab07;

import com.badlogic.gdx.math.Vector2;

import mc322.lab07.blocks.BlockEntity;
import mc322.lab07.blocks.Sand;
import mc322.lab07.blocks.Rock;


public class World {
    // blocks[height][width]
    private final int width = 20;
    private final int height = 20;
    private BlockEntity[][] blocks = new BlockEntity[height][width];

    private static Vector2 up = new Vector2(0, 1);
    private static Vector2 down = new Vector2(0, -1);
    private static Vector2 left = new Vector2(-1, 0);
    private static Vector2 right = new Vector2(1, 0);

    public World() {
        for (int i = 0; i < 5; i++) {
            Vector2 pos = new Vector2(10, 19 - i);
            Sand sandBlock = new Sand(pos, this);
            setBlock(sandBlock);
        }

        for (int i = 0; i < width; i++) {
            Vector2 pos = new Vector2(i, 0);
            Rock rockBlock = new Rock(pos, this);
            setBlock(rockBlock);
        }
    }

    // make sure that no blocks are overwritten?
    public void setBlock(BlockEntity block) {
        if (isValidPosition(block.getPosition())) {
            int i = (int) block.getPosition().x;
            int j = (int) block.getPosition().y;
            blocks[i][j] = block;
        }
    }

    private boolean isValidPosition(Vector2 position) {
        return position.x >= 0 && position.y >= 0 &&
            (int) position.x < width && (int) position.y < height;
    }

    public BlockEntity getBlockAt(Vector2 position) {
        // potentially bug-inducing
        int i = (int) position.x;
        int j = (int) position.y;
        return blocks[i][j];
    }

    public BlockEntity getBlockDown(Vector2 position, int distance) {
        return getBlockAt(position.add(down.scl(distance)));
    }

    public BlockEntity getBlockUp(Vector2 position, int distance) {
        return getBlockAt(position.add(up.scl(distance)));
    }

    public BlockEntity getBlockLeft(Vector2 position, int distance) {
        return getBlockAt(position.add(left.scl(distance)));
    }

    public BlockEntity getBlockRight(Vector2 position, int distance) {
        return getBlockAt(position.add(right.scl(distance)));
    }
}
