package donkeykong.level;

import donkeykong.sprite.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level1 extends Level
{
    public static String LEVEL1_BACKGROUND = "assets/img/level1.jpg";
    public static String LEVEL1_SONG = "assets/sound/level1.mp3";

    public Level1(MainCharacter character)
    {
        super(LEVEL1_BACKGROUND);

        this.levelSong = LEVEL1_SONG;

        //-----------RELLENO MAPA DE TILES-------------
        levelData = new String[] {
                "                    ",
                "     F         F    ",
                "                    ",
                "                    ",
                "                    ",
                "                    ",
                "                    ",
                "          B         ",
                "   T                ",
                "                    ",
                "         TTT        ",
                "T                  T",
                "TT                TT",
                "L    T             R",
                "                    ",
                "TT      T         TT",
                "TTT              TTT",
                "       T E  E   TTTT",
                "TTTTTTTTTTTTTTTTTTTT",
                "TTTTTTTTTTTTTTTTTTTT",
        };
        //------------------------------------------------

        restore(character);
    }

    @Override
    public void move(MainCharacter character)
    {

        super.move(character);

        //LÓGICA DE LOS BARRILES (Comentada para que no aparezcan)

        /*long time = System.currentTimeMillis();
        if (time - previousTimestamp >= currentInterval)
        {
            newBarrel();
            previousTimestamp = time;
        }

        int count = 0;
        while (count < barrels.size())
        {
            Barrel barrel = barrels.get(count);
            if (isAtEndPoint(barrel))
            {
                barrels.remove(count);
                character.increasePoints(BARREL_DESTROYED_POINTS);
            }
            else
            {
                // Generate a random number between 0 and 1000
                // If it is lower than 100, attach.
                int stairRandom = random.nextInt(1000);
                if (stairRandom < 100)
                {
                    attachStairToSprite(barrel);
                    if (barrel.getCurrentStair() != null &&
                        barrel.getCurrentStair().getYHigh() < barrel.getY())
                        barrel.setCurrentStair(null);
                }

                attachPlatformToSprite(barrel);
                barrel.checkGravity();
                barrel.move();
                count++;
            }
        }*/
    }

    @Override
    public void draw(GraphicsContext gc)
    {
        super.draw(gc);
        //for (Barrel barrel: barrels)
          //  barrel.draw(gc);

        //Dibujo los tiles, enemigos, etc
        for (Tile tile: tiles)
            tile.draw(gc);

        for (Enemy enemy: enemies)
        {
            if(enemy.getSpeed()>0)
                enemy.move(AnimatedSprite.RIGHT);
            if(enemy.getSpeed()<0)
                enemy.move(AnimatedSprite.LEFT);
            enemy.draw(gc);
        }

        for (FlyEnemy flyEnemy: fly_Enemies)
        {
            flyEnemy.draw(gc);
            flyEnemy.move();
        }

        boss.draw(gc);
        boss.move();
        door_Left.draw(gc);
        door_Left.move();
        door_Right.draw(gc);
        door_Right.move();

    }

    @Override
    public void restore(MainCharacter character)
    {
        character.moveTo(14, 202);
        character.resetJumpForce();
        character.resetPoints();
        character.animate(AnimatedSprite.RIGHT);
        double incrementSpeed = 1;

        //------------LÓGICA DE TILESETS------------------
        for (int row = 0; row < heightMap; row++)
        {
            for (int col = 0; col < widthMap; col++)
            {
                int posX = col * widhTile;
                int posY = row * heightTile;
                switch (levelData[row].charAt(col))
                {
                    case 'T':
                        Tile t = new Tile();
                        t.moveTo(posX, posY);
                        tiles.add(t); break;

                    case 'F':
                        FlyEnemy f = new FlyEnemy();
                        f.moveTo(posX, posY);
                        fly_Enemies.add(f); break;

                    case 'E':
                        Enemy e = new Enemy(incrementSpeed,posX,posY);
                        incrementSpeed+=0.5;
                        e.moveTo(posX, posY);
                        enemies.add(e); break;

                    case 'B':
                        boss.moveTo(posX, posY); break;

                    case 'L':
                        door_Left.moveTo(posX, posY); break;

                    case 'R':
                        door_Right.moveTo(posX, posY); break;
                }
            }
        }
        //------------------------------------------------
    }

    @Override
    public boolean checkCollisionsWith(AnimatedSprite sprite)
    {
        boolean result = false;
        int i = 0;

        return result;
    }

    public boolean CheckIfCanMove (MainCharacter character, float incX, float incY)
    {
        MainCharacter c = new MainCharacter();
        c.moveTo((int)(character.getX() + incX), (int)(character.getY() + incY));
        boolean cantMove = false;
        for (int i = 0; i < tiles.size(); i++) {
            if (c.collidesWith(tiles.get(i)))
                cantMove = true;
        }

        return !cantMove;
    }

}