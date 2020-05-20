package donkeykong.level;

import com.sun.tools.javac.Main;
import donkeykong.sprite.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javax.swing.text.html.ListView;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class Level
{
    protected Image imgBackground;
    protected String levelSong;
    protected Tile tile;

    //---------------------------------
    protected int widhTile = 32;
    protected int heightTile = 30;
    protected int heightMap = 20;
    protected int widthMap = 20;
    protected List<Tile> tiles = new ArrayList<>();
    protected String[] levelData;
    //--------------------------------

    protected List<Enemy> enemies = new ArrayList<>();
    protected List<FlyEnemy> fly_Enemies = new ArrayList<>();
    protected Boss boss = new Boss(1,20);
    protected Door_Left door_Left = new Door_Left();
    protected Door_Right door_Right = new Door_Right();

    public Level(String backgroundPath)
    {

    //------INFORMACIÓN DE TILES-------
        levelData = new String[heightMap];
    //---------------------------------

        try
        {
            imgBackground = new Image(Files.newInputStream(
                    Paths.get(backgroundPath)));
        } catch (Exception e) {
        }
    }


    public String getLevelSong()
    {
        return levelSong;
    }
    public List<Tile> getTiles() { return tiles; }

    public List<Enemy> getEnemies()
    {
        return enemies;
    }

    public List<FlyEnemy> getFlyEnemies()
    {
        return fly_Enemies;
    }

    public Door_Left getDoor_Left(){return door_Left; }

    public Door_Right getDoor_Right(){return door_Right; }

    public void move(MainCharacter character) { }

    public void draw(GraphicsContext gc) {
        gc.drawImage(imgBackground, 0, 0);
        for(int i = 0; i <enemies.size();i++){
            enemies.get(i).draw(gc);
        }
    }

    public abstract void restore(MainCharacter character);
    public abstract boolean checkCollisionsWith(AnimatedSprite sprite);
}