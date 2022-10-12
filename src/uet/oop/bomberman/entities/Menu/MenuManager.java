package uet.oop.bomberman.entities.Menu;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Stack;

public class MenuManager {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 500;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    public MenuManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
    }

    public Stage getMainStage() {
        return mainStage;
    }
    private void createButtons() {
        createNewGameButton();
        createHelpButton();
        createResumeButton();
        createExitButton();
    }
    private void createNewGameButton() {
        MenuButton button = new MenuButton("NEW GAME");
        mainPane.getChildren().add(button);
        button.setLayoutX(160);
        button.setLayoutY(0);
    }

    private void createResumeButton() {
        MenuButton button = new MenuButton("RESUME");
        mainPane.getChildren().add(button);
        button.setLayoutX(160);
        button.setLayoutY(51);
    }

    private void createHelpButton() {
        MenuButton button = new MenuButton("HELP");
        mainPane.getChildren().add(button);
        button.setLayoutX(160);
        button.setLayoutY(102);
    }

    private void createExitButton() {
        MenuButton button = new MenuButton("EXIT");
        mainPane.getChildren().add(button);
        button.setLayoutX(160);
        button.setLayoutY(153);
    }
}