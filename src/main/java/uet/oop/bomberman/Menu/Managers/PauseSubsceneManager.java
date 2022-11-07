package uet.oop.bomberman.Menu.Managers;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.Menu.Buttons.RectExitButton;
import uet.oop.bomberman.Menu.Buttons.RectResumeButton;
import uet.oop.bomberman.Menu.Buttons.RectReturnMainMenuButton;
import uet.oop.bomberman.Menu.Buttons.CircleSoundButton;

public class PauseSubsceneManager extends SubsceneManager {

    //CHILDREN
    private RectResumeButton rectResumeButton;
    private RectExitButton rectExitButton;
    private RectReturnMainMenuButton rectReturnMainMenuButton;
    private CircleSoundButton circleSoundButton;

    public PauseSubsceneManager() {
        super(new AnchorPane(),601 , 400);
        BackgroundImage image = new BackgroundImage(new Image(PANEL, 601, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        AnchorPane pane = (AnchorPane) this.getRoot();
        initChildren();
        addChildren(pane);
        pane.setBackground(new Background(image));
        setLayoutX(190);
        setLayoutY(-1000);
    }

    public RectResumeButton getResumeButton() {
        return rectResumeButton;
    }
    public RectExitButton getExitButton() {
        return rectExitButton;
    }
    public RectReturnMainMenuButton getReturnMainMenuButton() {
        return rectReturnMainMenuButton;
    }
    public CircleSoundButton getSoundButton() {
        return circleSoundButton;
    }

    /**
     * This function initializes the subscene's children.
     */
    @Override
    protected void initChildren() {
        circleSoundButton = new CircleSoundButton();
        rectResumeButton = new RectResumeButton(200,180);
        rectReturnMainMenuButton = new RectReturnMainMenuButton(200, 250);
        rectExitButton = new RectExitButton(200, 320);
        title = new Text();
        title.setFont(font);
        title.setText("GAME   PAUSED !");
        title.setFill(Color.ORANGERED);
        title.setX(180);
        title.setY(100);
    }

    /**
     * This function adds all the children to the main pane.
     */
    @Override
    protected void addChildren(AnchorPane pane){
        pane.getChildren().add(circleSoundButton);
        pane.getChildren().add(rectResumeButton);
        pane.getChildren().add(rectExitButton);
        pane.getChildren().add(rectReturnMainMenuButton);
        pane.getChildren().add(title);
    }
}
