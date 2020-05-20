package donkeykong.scene;

import donkeykong.MetroidMain;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.nio.file.Files;
import java.nio.file.Paths;

public class WelcomeScene extends MetroidScene
{
    public static final String WELCOME_SCREEN_PATH =
            "assets/img/WelcomeScene.png";

    public static final String WELCOME_SONG =
            "assets/sound/welcome.mp3";



    private Image imgBackground;

    public WelcomeScene()
    {
        super();

        try
        {
            imgBackground = new Image(Files.newInputStream(
                    Paths.get(WELCOME_SCREEN_PATH)));
        } catch (Exception e) {
        }
    }

    @Override
    public void draw()
    {
        activeKeys.clear();

        playSound(WELCOME_SONG);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if(activeKeys.contains(KeyCode.SPACE))
                {
                    this.stop();
                    mediaPlayer.stop();

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(5),
                            event -> {
                                MetroidMain.setScene(MetroidMain.GAME_SCENE);
                            }));
                    timeline.play();
                } else if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    mediaPlayer.stop();
                    MetroidMain.exit();
                }
                gc.drawImage(imgBackground, 0, 0);
            }
        }.start();
    }
}