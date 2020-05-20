package donkeykong.sprite;

public class Door_Right extends AnimatedSprite
{
    public static final int DOOR_R_WIDTH = 38;
    public static final int DOOR_R_HEIGHT = 62;

    public Door_Right()
    {
        super(DOOR_R_WIDTH, DOOR_R_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 1240, 1240, 1240, 1300, 1300, 1300, 1360, 1360, 1360 };
        spriteYCoordinates[RIGHT] = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20 };

        updateSpriteCoordinates();
    }

    public void move()
    {
        animate(RIGHT);
    }
}
