package uet.oop.bomberman.Menu.Managers;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class SubsceneManager extends SubScene implements Manager{
    protected String PANEL = "file:res/menu/subscene_panel.png";
    protected AnchorPane pane;
    protected Text title;
    protected Font font = Font.loadFont("file:res/Fonts/BreathFire.ttf", 35);
    public SubsceneManager(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    public void createBackgroundImage() {
        BackgroundImage image = new BackgroundImage(new Image(PANEL, 601, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        pane.setBackground(new Background(image));
    }

    /**
     * This function initializes the subscene's children.
     */
    public abstract void initChildren();

    /**
     * This function adds all the children to the main pane.
     */
    public abstract void addChildren();

    /**
     * This function shows the subscene.
     */
    public void showSubscene() {
        setLayoutY(15);
    }

    /**
     * This function hides the subscene.
     */
    public void hideSubscene() {
        setLayoutY(-1000);
    }
}
