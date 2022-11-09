package uet.oop.bomberman.Menu.Managers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import uet.oop.bomberman.GameplayManager;
import uet.oop.bomberman.Menu.Buttons.RectExitButton;
import uet.oop.bomberman.Menu.Buttons.RectHelpButton;
import uet.oop.bomberman.Menu.Buttons.RectNewGameButton;
import uet.oop.bomberman.Menu.Buttons.RectResumeButton;

public class MainSceneManager implements Manager{
    private static final int HEIGHT = 448;
    private static final int WIDTH = 992;
    protected final String PANEL = "file:res/menu/background_image.png";

    //MAIN STAGE'S STRUCTURE AND PARENTS
    private final AnchorPane pane;
    private final Stage mainStage;
    private final GameplayManager gameplayManager;

    //CHILDREN
    private RectNewGameButton rectNewGameButton;
    private RectHelpButton rectHelpButton;
    private RectResumeButton rectResumeButton;
    private RectExitButton rectExitButton;
    private HelpSubsceneManager helpSubScene;

    public MainSceneManager() {
        pane = new AnchorPane();
        Scene mainScene = new Scene(pane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        gameplayManager = new GameplayManager();
        initChildren();
        addChildren();
        createBackgroundImage();
        createHelpSubscene();
    }

    /**
     * This function creates the main menu's background image.
     */
    public void createBackgroundImage() {
        BackgroundImage image = new BackgroundImage(new Image(PANEL, WIDTH, HEIGHT, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        pane.setBackground(new Background(image));
    }

    /**
     * This function adds all the children to the mainPain.
     */
    public void addChildren() {
        this.pane.getChildren().add(rectNewGameButton);
        this.pane.getChildren().add(rectResumeButton);
        this.pane.getChildren().add(rectHelpButton);
        this.pane.getChildren().add(rectExitButton);
    }

    /**
     * This function calls all the children's initializing function.
     */
    public void initChildren() {
        createNewGameButton();
        createHelpButton();
        createResumeButton();
        createExitButton();
        createHelpSubscene();
    }

    /**
     * This function initializes the new game button.
     */
    private void createNewGameButton() {
        rectNewGameButton = new RectNewGameButton();
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                gameplayManager.startGame(mainStage);
            }
        };
        rectNewGameButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    /**
     * This function initializes the resume button.
     */
    private void createResumeButton() {
        rectResumeButton = new RectResumeButton();
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                // TODO
            }
        };
        rectNewGameButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    /**
     * This function initializes the help button.
     */
    private void createHelpButton() {
        rectHelpButton = new RectHelpButton();
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                pane.getChildren().add(helpSubScene);
            }
        };
        rectHelpButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    /**
     * This function initializes the exit button.
     */
    private void createExitButton() {
        rectExitButton = new RectExitButton();
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                mainStage.close();
            }
        };
        rectExitButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    /**
     * This function initializes the help subscene.
     */
    private void createHelpSubscene() {
        helpSubScene = new HelpSubsceneManager();
        EventHandler<javafx.scene.input.MouseEvent> eventHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                pane.getChildren().remove(helpSubScene);
            }
        };
        helpSubScene.getReturnMainMenuButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public Stage getMainStage() {
        return mainStage;
    }
}