package donkeykong.sprite;

public class Enemy extends AnimatedSprite
{
    public static final int ENEMY_WIDTH = 27;
    public static final int ENEMY_HEIGHT = 23;

    protected int maxX ,posX,posY;
    protected double speed;

    public Enemy(double speed,int posX,int posY)
    {
        super(ENEMY_WIDTH, ENEMY_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 140, 140, 140, 140, 200, 200, 200, 200};
        spriteYCoordinates[RIGHT] = new int[] { 84, 84, 84, 84, 84, 84, 84, 84 };

        spriteXCoordinates[LEFT] = new int[] { 140, 140, 140, 140, 200, 200, 200, 200};
        spriteYCoordinates[LEFT] = new int[] { 84, 84, 84, 84, 84, 84, 84, 84 };

        this.maxX = 100+posX;
        this.speed = speed;
        this.posX = posX;
        this.posY = posY;
        updateSpriteCoordinates();
    }

    public int getMaxX() {
        return maxX;
    }

    public double getSpeed() {
        return speed;
    }

    public int getPosX() {
        return posX;
    }

    public void move(int movement)
    {
        if(getX()>getMaxX())
            speed*=-1;
        if(getX()<getPosX())
            speed*=-1;

        x += speed;
        moveTo(x,posY);
        animate(movement);
    }
}
