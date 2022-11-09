package uet.oop.bomberman.Menu.Managers;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import uet.oop.bomberman.Menu.Buttons.RectReturnMainMenuButton;

public class HelpSubsceneManager extends SubsceneManager {
    //CHILDREN
    private RectReturnMainMenuButton rectReturnMainMenuButton;
    private String PANEL = "file:res/menu/help_panel.png";

    public HelpSubsceneManager() {
        super(new AnchorPane(),851 , 384);
        pane = (AnchorPane) this.getRoot();
        initChildren();
        addChildren();
        setLayoutX(75);
        setLayoutY(50);
    }

    @Override
    public void createBackgroundImage() {
        BackgroundImage image = new BackgroundImage(new Image(PANEL, 851, 384, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);

        pane.setBackground(new Background(image));
    }

    /**
     * This function initializes the helpSubscene's children.
     */
    @Override
    public void initChildren() {
        rectReturnMainMenuButton = new RectReturnMainMenuButton(640,320);
        createBackgroundImage();
    }

    /**
     * This function adds all the helpSubscene's children to the main pane.
     */
    @Override
    public void addChildren() {
        pane.getChildren().add(rectReturnMainMenuButton);
    }

    public RectReturnMainMenuButton getReturnMainMenuButton() {
        return rectReturnMainMenuButton;
    }
}
