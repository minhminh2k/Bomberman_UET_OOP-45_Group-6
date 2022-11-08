package uet.oop.bomberman.Menu.Managers;

import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class SubsceneManager extends SubScene {
    protected final static String PANEL = "file:res/menu/subscene_panel.png";
    protected Text title;
    protected Font font = Font.loadFont("file:res/Fonts/BreathFire.ttf", 35);
    public SubsceneManager(Parent parent, double v, double v1) {
        super(parent, v, v1);
    }

    /**
     * This function initializes the subscene's children.
     */
    protected abstract void initChildren();

    /**
     * This function adds all the children to the main pane.
     */
    protected abstract void addChildren(AnchorPane pane);

    /**
     * This function shows the subscene.
     */
    public void showSubscene() {
        setLayoutY(15);
    }

    /**
     * This function hide the subscene.
     */
    public void hideSubscene() {
        setLayoutY(-1000);
    }
}
