package donkeykong.scene;

import donkeykong.MetroidMain;
import donkeykong.level.Level1;
import donkeykong.sprite.*;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;

public class GameOverScene extends MetroidScene
{
    public static final String GAME_OVER_SOUND = "assets/sound/GameOverSong.wav";


    private MediaPlayer mediaPlayerEffects;
    private Media soundEffects;

    public void playEffect(String path)
    {
        soundEffects = new Media(new File(path).toURI().toString());
        mediaPlayerEffects = new MediaPlayer(soundEffects);
        mediaPlayerEffects.play();
    }
    @Override
    public void draw()
    {

        activeKeys.clear();
        playSound(GAME_OVER_SOUND);

        // Black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // "Game over" text
        Font myFont = Font.font("Courier New", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("GAME OVER", 250, 250);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (activeKeys.contains(KeyCode.ENTER))
                {
                    this.stop();
                    MetroidMain.setScene(MetroidMain.WELCOME_SCENE);
                }
            }
        }.start();
    }
}
