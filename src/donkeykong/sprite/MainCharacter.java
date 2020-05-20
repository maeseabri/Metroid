package donkeykong.sprite;

import donkeykong.level.Level1;
import donkeykong.scene.MetroidScene;
import javafx.scene.canvas.GraphicsContext;

public class MainCharacter extends AnimatedSprite
{
    public static final int MAIN_CHARACTER_WIDTH = 40;
    public static int MAIN_CHARACTER_HEIGHT = 40;
    public static final int MAX_LIVES = 3;

    public static final int CHARACTER_JUMP_SPRITE_X = 980;
    public static final int CHARACTER_JUMP_SPRITE_Y = 20;

    public static final int MAX_JUMP_FORCE = 120;

    protected int points;
    protected int lives;
    protected long imortalTime;
    protected boolean vulnerable = true;
    private int jumpForce;
    private boolean ballMode;
    private boolean canFall;

    public MainCharacter()
    {
        super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
        points = 0;
        lives = MAX_LIVES;
        ballMode = false;
        canFall = true;

        spriteXCoordinates[RIGHT] = new int[] { 380, 440, 500 };
        spriteYCoordinates[RIGHT] = new int[] { 20, 20, 20 };

        spriteXCoordinates[LEFT] = new int[] { 380, 440, 500 };
        spriteYCoordinates[LEFT] = new int[] { 20, 20, 20 };

        spriteXCoordinates[BALL_RIGHT] = new int[] { 680, 800, 855 };
        spriteYCoordinates[BALL_RIGHT] = new int[] { 20, 20, 20 };

        spriteXCoordinates[BALL_LEFT] = new int[] { 680, 800, 855 };
        spriteYCoordinates[BALL_LEFT] = new int[] { 20, 20, 20 };

        updateSpriteCoordinates();
    }

    public void move(int movement, Level1 level)
    {
        int newX = x, oldX = x, newY = y, oldY = y;
        int incrementX = 5;

        if (movement == LEFT ||movement == BALL_LEFT)
        {
            newX--;
            incrementX = -5;
            if (jumpForce > 0)
                newX--;
        }
        else if (movement == RIGHT || movement == BALL_RIGHT)
        {
            newX++;
            if (jumpForce > 0)
                newX++;
        }

        if (level.CheckIfCanMove(this,  incrementX, 0))
        {
            moveTo(newX, newY);
            if (!checkBoundaries())
                moveTo(oldX, oldY);
        }

        animate(movement);
    }

    public void fallOrJump(Level1 level)
    {
        int incrementY;
        if (canFall)
        {
            incrementY = 5;
            if (level.CheckIfCanMove(this, 0, incrementY))
                y += GRAVITY;
        }
        else
        {
            incrementY = -5;
            if (level.CheckIfCanMove(this, 0, incrementY)
                    && jumpForce > 0)
            {
                y -= GRAVITY;
                jumpForce -= GRAVITY;
            }
            else
            {
                jumpForce = 0;
                canFall = true;
            }
        }
    }

    public void jump()
    {
        canFall = false;
        jumpForce = MAX_JUMP_FORCE;
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        if (jumpForce > 0)
        {
            spriteX = CHARACTER_JUMP_SPRITE_X;
            spriteY = CHARACTER_JUMP_SPRITE_Y;
        }

        if (currentDirection == LEFT)
            gc.drawImage(spriteSheet, spriteX, spriteY,
                    width, height, x + width, y, -width, height);
        else
            super.draw(gc);
    }

    public boolean checkBoundaries()
    {
        return x >= 0 && x + width <= MetroidScene.GAME_WIDTH &&
                y >= 0 && y + height <= MetroidScene.GAME_HEIGHT;
    }

    public int getLives()
    {
        return lives;
    }

    public void resetLives()
    {
        lives = MAX_LIVES;
    }

    public void decreaseLives()
    {
        if(vulnerable){
            lives--;
            imortalTime = System.currentTimeMillis();
            vulnerable = false;
        }
        else if(System.currentTimeMillis() >= imortalTime + 2000){
            vulnerable = true;
        }
    }

    public int getJumpForce() {
        return jumpForce;
    }

    public void resetJumpForce()
    {
        jumpForce = 0;
    }

    public boolean getBallMode()
    {
        return ballMode;
    }

    public void changeBallMode(boolean mode) {
        if (mode) {
            ballMode = true;
            MAIN_CHARACTER_HEIGHT = 25;
        }
        else{
            ballMode = false;
            MAIN_CHARACTER_HEIGHT = 40;
            y-=3;
        }
    }

    public void increasePoints(int amount)
    {
        points += amount;
    }

    public void resetPoints()
    {
        points = 0;
    }

    public int getPoints()
    {
        return points;
    }
}
