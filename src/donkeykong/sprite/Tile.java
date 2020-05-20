package donkeykong.sprite;

public class Tile extends AnimatedSprite
{
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 30;

    public Tile()
    {
        super(TILE_WIDTH, TILE_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 324 };
        spriteYCoordinates[RIGHT] = new int[] { 104 };

        updateSpriteCoordinates();
    }

}
