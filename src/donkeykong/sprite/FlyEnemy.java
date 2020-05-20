package donkeykong.sprite;

public class FlyEnemy extends AnimatedSprite
{
    public static final int FLY_ENEMY_WIDTH = 32;
    public static final int FLY_ENEMY_HEIGHT = 27;

    public FlyEnemy()
    {
        super(FLY_ENEMY_WIDTH, FLY_ENEMY_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 800, 800, 378, 378, 526, 526, 662, 662 };
        spriteYCoordinates[RIGHT] = new int[] { 200, 200, 200, 200, 200, 200, 200, 200 };

        updateSpriteCoordinates();
    }

    public void move()
    {
        animate(RIGHT);
    }
}
