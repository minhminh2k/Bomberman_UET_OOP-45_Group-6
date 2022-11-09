package uet.oop.bomberman.Menu.Managers;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Menu.Buttons.RectExitButton;
import uet.oop.bomberman.Menu.Buttons.RectPlayAgainButton;
import uet.oop.bomberman.Menu.Buttons.RectReturnMainMenuButton;

public class GameOverSubsceneManager extends SubsceneManager {
    //CHILDREN
    private RectPlayAgainButton rectPlayAgainButton;
    private RectExitButton rectExitButton;
    private RectReturnMainMenuButton rectReturnMainMenuButton;
    private Text status;
    private Text score;
    private int scoreNumber;
    private final Font font2 = Font.loadFont("file:res/Fonts/EightBitDragon.ttf", 25);

    public GameOverSubsceneManager() {
        super(new AnchorPane(),601 , 400);
        pane = (AnchorPane) this.getRoot();
        initChildren();
        addChildren();
        setLayoutX(190);
        setLayoutY(-1000);
    }

    /**
     * This function initializes the GameOverSubscene's children.
     */
    @Override
    public void initChildren() {
        createButtons();
        initTitle();
        initStatus();
        initScore();
        createBackgroundImage();
    }

    private void createButtons() {
        rectPlayAgainButton = new RectPlayAgainButton();
        rectReturnMainMenuButton = new RectReturnMainMenuButton(200, 250);
        rectExitButton = new RectExitButton(200, 320);
    }

    private void initTitle() {
        title = new Text();
        title.setFont(font);
        title.setText("GAME OVER !");
        title.setFill(Color.ORANGERED);
        title.setX(200);
        title.setY(50);
    }

    private void initStatus() {
        status = new Text();
        status.setFont(font2);
        status.setFill(Color.ORANGERED);
        status.setX(215);
        status.setY(100);
    }

    private void initScore() {
        score = new Text();
        score.setFont(font2);
        score.setFill(Color.ORANGERED);
        score.setX(200);
        score.setY(150);
    }

    /**
     * This function adds all the GameOverSubscene's children to the main pane.
     */
    @Override
    public void addChildren(){
        pane.getChildren().add(rectPlayAgainButton);
        pane.getChildren().add(rectReturnMainMenuButton);
        pane.getChildren().add(rectExitButton);
        pane.getChildren().add(score);
        pane.getChildren().add(title);
        pane.getChildren().add(status);
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public RectPlayAgainButton getNewGameButton() {
        return rectPlayAgainButton;
    }

    public RectExitButton getExitButton() {
        return rectExitButton;
    }

    public RectReturnMainMenuButton getReturnMainMenuButton() {
        return rectReturnMainMenuButton;
    }

    public Text getStatus() {
        return status;
    }

    public Text getScore() {
        return score;
    }
}
