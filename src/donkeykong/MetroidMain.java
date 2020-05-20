
package donkeykong;

import donkeykong.scene.*;
import donkeykong.sprite.Sprite;
import javafx.application.Application;
import javafx.stage.Stage;

public class MetroidMain extends Application
{
    public static final int MAX_SCENES = 4;

    public static final int WELCOME_SCENE = 0;
    public static final int GAME_SCENE = 1;
    public static final int GAME_OVER_SCENE = 2;
    public static final int LEVEL_COMPLETE_SCENE = 3;

    public static final MetroidScene[] scenes =
            new MetroidScene[MAX_SCENES];

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;

        scenes[WELCOME_SCENE] = new WelcomeScene();
        scenes[GAME_SCENE] = new GameScene();
        scenes[GAME_OVER_SCENE] = new GameOverScene();
        scenes[LEVEL_COMPLETE_SCENE] = new LevelCompleteScene();

        Sprite.initSpriteSheet();

        primaryStage.setTitle("Metroid");
        setScene(WELCOME_SCENE);
        primaryStage.show();
    }

    public static void setScene(int numScene)
    {
        primaryStage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }

    public static void exit()
    {
        primaryStage.hide();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
