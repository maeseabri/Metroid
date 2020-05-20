package donkeykong.scene;

import donkeykong.MetroidMain;
import donkeykong.level.Level1;
import donkeykong.sprite.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class GameScene extends MetroidScene
{
    public static final String JUMP_SOUND = "assets/sound/Jump.wav";
    public static final String DIE_SOUND = "assets/sound/DeathSound.mp3";
    public static final String SHOOT_SOUND = "assets/sound/Shoot.wav";
    public static final String DOOR_SOUND = "assets/sound/Door.wav";
    public static final String ItemDiscovered_SOUND =  "assets/sound/ItemDiscovered.wav";
    public static final String WELCOME_SOUND = "assets/sound/GameStart.wav";
    public static final String HIT_SOUND = "assets/sound/Hit.mp3";



    private Level1 level;
    private MainCharacter character;
    //private Image imgLife;
    //private Score score;
    private int highScore;

    private MediaPlayer mediaPlayerEffects;
    private Media soundEffects;

    public GameScene()
    {
        super();

        character = new MainCharacter();

        //score = new Score();
        //highScore = 0;
        level = new Level1(character);

        /*try
        {
            imgLife = new Image(Files.newInputStream(
                Paths.get(MARIO_IMAGE_LIFE)));
        } catch (Exception e) {
        }*/
    }

    public void playEffect(String path)
    {
        soundEffects = new Media(new File(path).toURI().toString());
        mediaPlayerEffects = new MediaPlayer(soundEffects);
        mediaPlayerEffects.play();
    }

    @Override
    public void draw()
    {

        playEffect(WELCOME_SOUND);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(6700),
                event -> {
                    playSound(level.getLevelSong());
                }));
        timeline.play();

        activeKeys.clear();

        character.resetLives();
        level.restore(character);



        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Black background
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

                if (activeKeys.contains(KeyCode.ESCAPE)) {
                    this.stop();
                    mediaPlayer.stop();
                    MetroidMain.setScene(
                            MetroidMain.WELCOME_SCENE);
                }

                if (activeKeys.contains(KeyCode.UP)) {
                    character.changeBallMode(false);
                    character.animate(AnimatedSprite.RIGHT);
                } else if (activeKeys.contains(KeyCode.DOWN)) {

                    character.changeBallMode(true);
                    character.animate(AnimatedSprite.BALL_LEFT);
                } else if (activeKeys.contains(KeyCode.LEFT)) {
                    if (!character.getBallMode())
                        character.move(AnimatedSprite.LEFT, level);

                    else
                        character.move(AnimatedSprite.BALL_LEFT, level);
                } else if (activeKeys.contains(KeyCode.RIGHT)) {
                        if (!character.getBallMode())
                            character.move(AnimatedSprite.RIGHT, level);
                        else
                            character.move(AnimatedSprite.BALL_RIGHT, level);
                }

                if (activeKeys.contains(KeyCode.SPACE))
                {
                    character.jump();
                    playEffect(JUMP_SOUND);
                } else {
                    character.fallOrJump(level);
                    level.move(character);
                }

                for (int i = 0; i < level.getEnemies().size(); i++)
                {
                    if(character.collidesWith(level.getEnemies().get(i)) && (character.getLives() > 0))
                    {
                        playEffect(HIT_SOUND);
                        character.decreaseLives();
                    }
                    if(character.getLives() == 0){
                        mediaPlayer.stop();
                        MetroidMain.setScene(MetroidMain.GAME_OVER_SCENE);
                    }
                    if (character.collidesWith(level.getDoor_Right()))
                    {
                        playEffect(DOOR_SOUND);
                        this.stop();
                        mediaPlayer.stop();
                        MetroidMain.setScene(MetroidMain.LEVEL_COMPLETE_SCENE);
                    }
                    else if (level.checkCollisionsWith(character)) {
                        playEffect(DIE_SOUND);
                        character.decreaseLives();
                        if (character.getLives() == 0) {
                            this.stop();
                            mediaPlayer.stop();
                            MetroidMain.setScene(MetroidMain.GAME_OVER_SCENE);
                        } else {
                            playSound(level.getLevelSong());
                            level.restore(character);
                        }
                        this.start();
                    }
                }

                level.draw(gc);
                character.draw(gc);
                //drawLives();
                //updateHighScore();
                //score.drawScore(character.getPoints(), gc, false);
                }
            }.

            start();
        }

   /* public void drawLives()
    {
        int initX = 20, y = 5;
        for (int i = 0; i < character.getLives(); i++)
        {
            gc.drawImage(imgLife, initX + i*50, y);
        }
    }

    public void updateHighScore()
    {
        if (character.getPoints() > highScore)
            highScore = character.getPoints();

        score.drawScore(highScore, gc, true);
    }*/
}