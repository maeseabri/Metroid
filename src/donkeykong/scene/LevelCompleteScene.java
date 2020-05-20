package donkeykong.scene;

import donkeykong.MetroidMain;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LevelCompleteScene extends MetroidScene
{
    @Override
    public void draw()
    {
        activeKeys.clear();

        // Black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // "Level complete" text
        Font myFont = Font.font("Courier New", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("LEVEL COMPLETE!", 225, 250);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (activeKeys.contains(KeyCode.ENTER))
                {
                    this.stop();
                    MetroidMain.setScene(MetroidMain.GAME_SCENE);
                }
            }
        }.start();
    }
}
