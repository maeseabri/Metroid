package donkeykong.level;

import donkeykong.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;

public class Score
{
    public static final int NUMBER_WIDTH = 20;
    public static final int NUMBER_HEIGHT = 20;
    public static final int NUMBER_GAP = 20;
    public static final int NUMBER_STARTING_X = 688;
    public static final int NUMBER_Y = 160;

    public static final int SCENE_STARTING_X = 178;
    public static final int SCENE_HIGHSCORE_STARTING_X = 420;
    public static final int SCENE_Y = 60;

    public Score()
    {
    }

    public void drawScore(int score, GraphicsContext gc, boolean highScore)
    {
        String scoreText = "" + score;

        int currentX = highScore? SCENE_HIGHSCORE_STARTING_X: SCENE_STARTING_X;

        for (int i = scoreText.length() - 1; i >= 0; i--)
        {
            int digit = scoreText.charAt(i) - '0';
            gc.drawImage(Sprite.getSpriteSheet(),
            NUMBER_STARTING_X + (NUMBER_WIDTH + NUMBER_GAP) * digit,
                NUMBER_Y, NUMBER_WIDTH, NUMBER_HEIGHT,
            currentX - (scoreText.length() - 1 - i) * NUMBER_WIDTH, SCENE_Y,
                NUMBER_WIDTH, NUMBER_HEIGHT);
        }
    }
}
