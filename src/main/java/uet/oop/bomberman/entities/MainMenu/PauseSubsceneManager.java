package uet.oop.bomberman.entities.MainMenu;

import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class PauseSubsceneManager extends SubScene {

    private final static String PAUSE_PANEL = "pause_panel2.png";

    public PauseSubsceneManager() {
        super(new AnchorPane(),601 , 400);
        BackgroundImage image = new BackgroundImage(new Image(PAUSE_PANEL, 601, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        AnchorPane pane = (AnchorPane) this.getRoot();

        pane.setBackground(new Background(image));
        setLayoutX(170);
        setLayoutY(-1000);
    }

    public void showPausedMenu() {
        setLayoutY(15);
    }

    public void hidePausedMenu() {
        setLayoutY(-1000);
    }
}
