package uet.oop.bomberman.entities.MainMenu;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.GameplayManager;

public class MainSceneManager {
    private static final int HEIGHT = 448;
    private static final int WIDTH = 992;

    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private GameplayManager gameplayManager;

    private NewGameButton newGameButton;
    private HelpButton helpButton;
    private ResumeButton resumeButton;
    private ExitButton exitButton;
    private SubScene helpSubScene;

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public MainSceneManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        gameplayManager = new GameplayManager();
        createButtons();
        createBackgroundImage();
        createHelpPanel();
    }

    private void createBackgroundImage() {
        Image image = new Image("background_image.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(backgroundImage));
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
        newGameButton = new NewGameButton();
        mainPane.getChildren().add(newGameButton);
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                gameplayManager.startGame(mainStage);
            }
        };
        newGameButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    private void createResumeButton() {
        resumeButton = new ResumeButton();
        mainPane.getChildren().add(resumeButton);

        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {

            }
        };
        newGameButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    private void createHelpButton() {
        helpButton = new HelpButton();
        mainPane.getChildren().add(helpButton);

        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                showHelp();
            }
        };
        helpButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    private void createExitButton() {
        exitButton = new ExitButton();
        mainPane.getChildren().add(exitButton);
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                mainStage.close();
            }
        };
        exitButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    private void createHelpPanel() {
        helpSubScene = new SubScene(new AnchorPane(), 845, 381);
        helpSubScene.setLayoutX(75);
        helpSubScene.setLayoutY(-500);
        BackgroundImage image = new BackgroundImage(new Image("help_panel.png", 845, 381, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane pane = (AnchorPane) helpSubScene.getRoot();
        pane.setBackground(new Background(image));
        mainPane.getChildren().add(helpSubScene);
        mainPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    hideHelp();
                }
            }
        });
    }

    private void showHelp() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.35));
        transition.setNode(helpSubScene);
        transition.setToY(550);
        transition.play();
    }

    private void hideHelp() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.35));
        transition.setNode(helpSubScene);
        transition.setToY(-500);
        transition.play();
    }
}