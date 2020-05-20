package donkeykong.sprite;

public class Door_Left extends AnimatedSprite
{
    public static final int DOOR_L_WIDTH = 38;
    public static final int DOOR_L_HEIGHT = 62;

    public Door_Left()
    {
        super(DOOR_L_WIDTH, DOOR_L_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 1070, 1070, 1070, 1130, 1130, 1130, 1190, 1190, 1190 };
        spriteYCoordinates[RIGHT] = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20 };

        updateSpriteCoordinates();
    }

    public void move()
    {
        animate(RIGHT);
    }
}
