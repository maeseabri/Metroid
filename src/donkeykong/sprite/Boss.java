package donkeykong.sprite;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class Boss extends AnimatedSprite
{
    public static final int BOSS_WIDTH = 100;
    public static final int BOSS_HEIGHT = 90;

    public static int speed;
    public static int health;
    public static int Maxhealth;

    public Boss(int speed,int health)
    {
        super(BOSS_WIDTH, BOSS_HEIGHT);

        spriteXCoordinates[RIGHT] = new int[] { 880, 880, 880, 1047, 1047, 1047, 1215, 1215, 1215, 1375, 1375, 1375 };
        spriteYCoordinates[RIGHT] = new int[] { 190, 190, 190, 190, 190, 190, 190, 190, 190, 190, 190, 190 };

        Boss.speed = speed;
        Boss.health = health;
        Boss.Maxhealth = health;

        updateSpriteCoordinates();
    }

    public void move() {
        Random random = new Random();
        if(random.nextInt(75) == 1){
            speed *= -1;
        }

        if(y<100)
            speed *= -1;

        if(y > 350)
            speed = -1;


        if(health == Maxhealth/2 && Math.abs(speed) < 2){
            speed=2;
        }

        if(health == Maxhealth/4 && Math.abs(speed) < 4){
            speed*=2;
        }

        y -= speed;
        moveTo(x,y);
        animate(RIGHT);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(spriteSheet, spriteX, spriteY,
                width, height, x + width, y, -width, height);
    }

    public void shoot(){

    }
}