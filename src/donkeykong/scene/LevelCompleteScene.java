package donkeykong.scene;

import donkeykong.MetroidMain;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LevelCompleteScene extends MetroidScene
{
    private String name;
    private Label label;
    private int startY;
    boolean checkRanking = true;
    private List<String> playersRanking;
    public LevelCompleteScene()
    {
        TextInputDialog dialog = new TextInputDialog("Player");
        dialog.setTitle("Player name");
        dialog.setHeaderText("Please, write your player's name before start playing");
        dialog.setContentText("NAME:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(named -> name = named);
    }

    @Override
    public void draw()
    {

        if (checkRanking)
        {
            checkRanking = false;
            playersRanking = loadRanking();
            saveRanking();
        }
        activeKeys.clear();

        // Black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        // "Level complete" text
        Font myFont = Font.font("Courier New", FontWeight.NORMAL, 24);
        gc.setFont(myFont);
        gc.setFill(Color.WHITE);
        gc.fillText("LEVEL COMPLETE!", 225, 250);

        startY = 310;
        for (int i = 0; i < playersRanking.size(); i++) {
            gc.fillText(playersRanking.get(i), 225, startY);
            startY += 30;
        }

        gc.fillText(name, 225, 280);

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

    public List<String> loadRanking()
    {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(
                    new File("ranking.txt")));

            String line;
            do
            {
                line = input.readLine();
                if (line != null)
                {
                    lines.add(line);
                }
            } while (line != null);
            input.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveRanking() {
        try {
            PrintWriter output = new PrintWriter("ranking.txt");

            //Ese '000' serían los puntos
            output.println(name + ";000") ;
            for (int i = 0; i < playersRanking.size(); i++) {
                output.println(playersRanking.get(i));
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

