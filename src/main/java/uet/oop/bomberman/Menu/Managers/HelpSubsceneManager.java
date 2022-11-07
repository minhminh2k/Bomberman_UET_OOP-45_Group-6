package uet.oop.bomberman.Menu.Managers;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import uet.oop.bomberman.Menu.Buttons.RectReturnMainMenuButton;

public class HelpSubsceneManager extends SubsceneManager {
    //CHILDREN
    private RectReturnMainMenuButton rectReturnMainMenuButton;

    public RectReturnMainMenuButton getReturnMainMenuButton() {
        return rectReturnMainMenuButton;
    }

    public HelpSubsceneManager() {
        super(new AnchorPane(),851 , 384);
        BackgroundImage image = new BackgroundImage(new Image("help_panel.png", 851, 384, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        AnchorPane pane = (AnchorPane) this.getRoot();
        initChildren();
        addChildren(pane);
        pane.setBackground(new Background(image));
        setLayoutX(75);
        setLayoutY(50);
    }

    /**
     * This function initializes the helpSubscene's children.
     */
    @Override
    protected void initChildren() {
        rectReturnMainMenuButton = new RectReturnMainMenuButton(640,320);
    }

    /**
     * This function adds all the helpSubscene's children to the main pane.
     */
    @Override
    protected void addChildren(AnchorPane pane) {
        pane.getChildren().add(rectReturnMainMenuButton);
    }
}
