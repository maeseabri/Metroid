package donkeykong.sprite;

public abstract class AnimatedSprite extends Sprite
{
    public static final int GRAVITY = 3;

    public static final int TOTAL_MOVEMENTS = 6;
    public static final byte SPRITE_CHANGE = 5;

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int BALL_RIGHT = 4;
    public static final int BALL_LEFT = 5;

    protected int currentDirection;
    protected byte currentSprite;
    protected byte currentSpriteChange;

    public int[][] spriteXCoordinates = new int[TOTAL_MOVEMENTS][];
    public int[][] spriteYCoordinates = new int[TOTAL_MOVEMENTS][];

    public AnimatedSprite(int width, int height)
    {
        super(width, height);
        currentDirection = RIGHT;
        currentSprite = 0;
        currentSpriteChange = 0;
    }

    public void animate(int movement)
    {
        if (movement != currentDirection)
        {
            currentDirection = movement;
            currentSprite = 0;
            currentSpriteChange = 0;
        } else {
            currentSpriteChange++;
            if (currentSpriteChange >= SPRITE_CHANGE)
            {
                currentSpriteChange = 0;
                currentSprite = (byte)((currentSprite + 1) %
                        spriteXCoordinates[currentDirection].length);
            }
        }
        updateSpriteCoordinates();
    }

    protected void updateSpriteCoordinates()
    {
        spriteX = spriteXCoordinates[currentDirection][currentSprite];
        spriteY = spriteYCoordinates[currentDirection][currentSprite];
    }

    public boolean collidesWith(AnimatedSprite sp)
    {
        return (x + width  > sp.x && x < sp.x + sp.width  &&
                y + height  > sp.y && y < sp.y + sp.height );
        /*return (x + width / 2 > sp.x && x < sp.x + sp.width / 2 &&
                y + height / 2 > sp.y && y < sp.y + sp.height / 2);*/
    }
}
