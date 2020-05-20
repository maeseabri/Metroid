package donkeykong.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class Sprite
{
    public static final String SPRITE_SHEET_PATH =
            "assets/img/sprites.png";

    protected static Image spriteSheet;
    protected int width, height;
    protected int x, y;
    protected int spriteX, spriteY;

    public Sprite(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public static void initSpriteSheet()
    {
        try
        {
            spriteSheet = new Image(Files.newInputStream(
                Paths.get(SPRITE_SHEET_PATH)));
        } catch (Exception e) { }
    }

    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public static Image getSpriteSheet()
    {
        return spriteSheet;
    }

    public void draw(GraphicsContext gc)
    {
        gc.drawImage(spriteSheet, spriteX, spriteY,
            width, height, x, y, width, height);
    }

}
