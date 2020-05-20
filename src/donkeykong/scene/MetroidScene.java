package donkeykong.scene;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public abstract class MetroidScene extends Scene
{
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 600;

    private StackPane root = new StackPane();

    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;

    protected GraphicsContext gc;

    protected MediaPlayer mediaPlayer;
    protected Media sound;

    public MetroidScene()
    {
        // Call to Scene constructor to initialize it
        super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);

        // Change scene's root to our own stack pane
        root = new StackPane();
        this.setRoot(root);

        // Initialize canvas and graphics context
        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        // Initialize set of currently pressed and released keys
        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();
        this.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        this.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
            releasedKeys.add(e.getCode());
        });
    }

    public void playSound(String path)
    {
        sound = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public abstract void draw();
}