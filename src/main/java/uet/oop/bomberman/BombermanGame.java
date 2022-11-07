package uet.oop.bomberman;


import javafx.application.Application;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.Managers.MainSceneManager;

public class BombermanGame extends Application {
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        MainSceneManager manager = new MainSceneManager();
        stage = manager.getMainStage();
        stage.show();
    }
}