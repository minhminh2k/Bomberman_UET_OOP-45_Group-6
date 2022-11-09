package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Maps.MapLevel;
import uet.oop.bomberman.Menu.Buttons.CirclePauseButton;
import uet.oop.bomberman.Menu.Managers.GameOverSubsceneManager;
import uet.oop.bomberman.Menu.Managers.PauseSubsceneManager;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemies.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Item.BomItem;
import uet.oop.bomberman.entities.TitleMap.Brick;

import java.util.ArrayList;
import java.util.List;

public class GameplayManager {
    //APPLICATION
    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;
    private Group root;
    private Stage stage;

    //SUBSCENE
    private PauseSubsceneManager pauseScene;
    private GameOverSubsceneManager gameOverScene;
    private CirclePauseButton circlePauseButton;
    private boolean isPaused = false;

    //IN GAME ENTITIES AND LOGIC VARIABLES
    public int level = 1;
    private AnimationTimer timer;
    private final MapLevel map = new MapLevel();
    private int score = 0;
    private int new_score = 0;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;
    public int frame_bom = -1;
    public int numberBom = 1;
    public int SizeBom = 1;
    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];
    public int Row = HEIGHT;
    public int Col = WIDTH;
    private final List<Entity> lives = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();
    private final List<Brick> listFlameItem = new ArrayList<>();
    private final List<Brick> deleteFlameItem = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<Enemy> deleteEnemies = new ArrayList<>();
    private List<Entity> Bom0 = new ArrayList<>();
    private List<Entity> Bom1 = new ArrayList<>();
    CheckCollision check = new CheckCollision();
    BomItem[] Bom = new BomItem[3];
    private final Brick[][] bricks = new Brick[HEIGHT][WIDTH];
    Bomber bomberman = new Bomber(1, 2, Sprite.player_right.getFxImage());

    //TEXT
    private final Font font = Font.loadFont("file:res/Fonts/BreathFire.ttf", 25);
    private final Text scoreText = new Text();
    private final Text text = new Text();

    //SOUND
    private final Sound sound = new Sound();
    private boolean isMuted;

    /**
     * This function initializes the application structure.
     */
    public void init() {
        gameOverScene = new GameOverSubsceneManager();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        pauseScene = new PauseSubsceneManager();
        stage = new Stage();
        root = new Group();
        circlePauseButton = new CirclePauseButton();
        root.getChildren().add(canvas);
        root.getChildren().add(gameOverScene);
        canvas.setMouseTransparent(true);
        scene = new Scene(root);
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.show();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (int i = 0; i < numberBom; i++) {
                    Bom[i].setSizeBom(SizeBom);
                    if (Bom[i].getStatus()) {
                        if (i == 0) {
                            Bom[0].setStillObjects(Bom0);
                        } else {
                            Bom[1].setStillObjects(Bom1);
                        }
                        Bom[i].frameBom();
                        handleBrickBom(i);

                        if (i == 0) {
                            Bom0 = Bom[0].getStillObjects();
                        } else {
                            Bom1 = Bom[1].getStillObjects();
                        }
                    }

                    Bom[i].updateMap(mapMatrix, Row, Col);
                }

                if (score != new_score) {
                    new_score = score;
                    scoreText.setText(Integer.toString(score));
                }

                sound.soundMoving(bomberman);
                sound.playBackground();
                checkCollision();
                render();
                update();
            }
        };
        isMuted = false;
        sound.unmute();
        timer.start();
        addText();
    }

    /**
     * This function starts the game.
     */
    public void startGame(Stage menuStage) {
        isPaused = false;
        menuStage.hide();
        if (root != null && root.getChildren() != null) {
            clearAll();
        }

        init();
        mapMatrix = map.createMap(level);
        bomberman.setPos(32,32 * 2);
        for (int i = 0; i < 3; i++) {
            Bom[i] = new BomItem() {
                @Override
                public void update() {
                }
            };
            Bom[i].setSize(HEIGHT, WIDTH);
        }
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                bricks[i][j] = new Brick() {
                    public void update() {
                    }
                };
            }
        }
        keyEventHandling(scene);
        pauseSubSceneEventHandling(menuStage);
        gameOverSubSceneEventHandling(menuStage);
        map.addEntity_map1(stillObjects, bricks, enemies, bomberman, lives);
    }

    /**
     * This function handles the game over subscene.
     */
    private void gameOverSubSceneEventHandling(Stage menuStage) {
        EventHandler<MouseEvent> newGame = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                clearAll();
                startGame(menuStage);
            }
        };

        EventHandler<MouseEvent> returnMain = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                clearAll();
                returnMainMenu(stage, menuStage);
            }
        };

        EventHandler<MouseEvent> exit = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                stage.close();
            }
        };
        gameOverScene.getReturnMainMenuButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, returnMain);
        gameOverScene.getNewGameButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, newGame);
        gameOverScene.getExitButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, exit);
    }

    /**
     * This function handles the pause subscene.
     */
    private void pauseSubSceneEventHandling(Stage menuStage) {
        EventHandler<MouseEvent> resume = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                if (isPaused && bomberman != null) {
                    resumeGame();
                }
            }
        };

        EventHandler<MouseEvent> muteSound = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                if (sound.isMuted()) {
                    pauseScene.getSoundButton().setUNMUTED();
                    sound.unmute();
                } else {
                    pauseScene.getSoundButton().setMUTED();
                    sound.mute();
                }
            }
        };

        EventHandler<MouseEvent> returnMain = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                returnMainMenu(stage, menuStage);
            }
        };

        EventHandler<MouseEvent> pause = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                pauseGame();
            }
        };

        EventHandler<MouseEvent> exit = new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                stage.close();
            }
        };

        circlePauseButton.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, pause);
        pauseScene.getReturnMainMenuButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, returnMain);
        pauseScene.getResumeButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, resume);
        pauseScene.getSoundButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, muteSound);
        pauseScene.getExitButton().addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, exit);
    }

    /**
     * This function handles the in-game pressed keys.
     */
    private void keyEventHandling(Scene scene){
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();

            switch (keyCode) {
                case ESCAPE: {
                    if (bomberman != null && !isPaused) {
                        pauseGame();
                    }
                    break;
                }
                case RIGHT: {
                    if (bomberman != null) {
                        bomberman.setMoving(true);
                        bomberman.addMove("right");
                        bomberman.updateLastStatus("right");
                    }

                    break;
                }
                case LEFT: {
                    if (bomberman != null) {
                        bomberman.setMoving(true);
                        bomberman.addMove("left");
                        bomberman.updateLastStatus("left");
                        break;
                    }

                }
                case UP: {
                    if (bomberman != null) {
                        bomberman.setMoving(true);
                        bomberman.addMove("up");
                        bomberman.updateLastStatus("up");
                    }

                    break;
                }
                case DOWN: {
                    if (bomberman != null) {
                        bomberman.setMoving(true);
                        bomberman.addMove("down");
                        bomberman.updateLastStatus("down");
                    }

                    break;
                }
                case SPACE: {
                    if (bomberman != null) {
                        for (int i = 0; i < numberBom; i++) {
                            if (!Bom[i].getStatus()) {
                                int x = (bomberman.getX() + 31) / 32;
                                x = (x * 32 - bomberman.getX()) > (bomberman.getX() + 32 - 32 * x) ? bomberman.getX() / 32 : x;
                                int y = (bomberman.getY() + 31) / 32;
                                y = (y * 32 - bomberman.getY()) > (bomberman.getY() + 32 - 32 * y) ? bomberman.getY() / 32 : y;
                                if (i == 0) {
                                    Bom[0].setStillObjects(Bom0);
                                } else {
                                    Bom[1].setStillObjects(Bom1);
                                }
                                bomberman.setOutBomb(false);
                                Bom[i].setPos(x, y);
                                break;
                            }
                        }
                    }

                }

            }

        });
        scene.setOnKeyReleased(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            switch (keyCode) {
                case RIGHT -> {
                    if (bomberman != null) {
                        if (bomberman.checkStatus("right")) {
                            bomberman.deleteMove("right");
                        }
                        bomberman.updateLastStatus();
                    }
                }
                case LEFT -> {
                    if (bomberman != null) {
                        if (bomberman.checkStatus("left")) {
                            bomberman.deleteMove("left");
                        }
                        bomberman.updateLastStatus();
                    }
                }
                case UP -> {
                    if (bomberman != null) {
                        if (bomberman.checkStatus("up")) {
                            bomberman.deleteMove("up");
                        }
                        bomberman.updateLastStatus();
                    }
                }
                case DOWN -> {
                    if (bomberman != null) {
                        if (bomberman.checkStatus("down")) {
                            bomberman.deleteMove("down");
                        }
                        bomberman.updateLastStatus();
                    }
                }
            }
            if (bomberman != null) {
                if (bomberman.sizeMoving() == 0) {
                    bomberman.setMoving(false);
                }
            }

        });
    }

    /**
     * This function shows the main menu stage.
     */
    public void returnMainMenu(Stage gameStage, Stage menuStage) {
        gameStage.hide();
        menuStage.show();
        timer.stop();
        Sound.stopBackground();
        pauseScene.hideSubscene();
    }

    /**
     * This function pauses the game.
     */
    public void pauseGame() {
        root.getChildren().add(pauseScene);
        pauseScene.showSubscene();
        isPaused = true;
        timer.stop();
        Sound.pauseBackground();
    }

    /**
     * This function continues the game.
     */
    public void resumeGame() {
        root.getChildren().remove(pauseScene);
        pauseScene.hideSubscene();
        isPaused = false;
        timer.start();
        if (!isMuted){
            sound.playBackground();
        }
    }

    /**
     * This function updates in-game entities.
     */
    public void update() {
        enemies.forEach(Entity::update);
        if (bomberman != null)
            bomberman.update();
        stillObjects.forEach(Entity::update);
        lives.forEach(Entity::update);
    }

    /**
     * This function renders in-game entities.
     */
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        Bom0.forEach(g -> g.render(gc));
        Bom1.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        lives.forEach(g -> g.render(gc));
        if (bomberman != null) {
            bomberman.render(gc);
        }
    }

    /**
     * This function handles when bricks are exploded.
     */
    public void handleBrickBom(int i) {
        int rowY = (Bom[i].getY() + 16) / 32;
        int colX = (Bom[i].getX() + 16) / 32;
        if (Bom[i].getFrame_bom() > 3 * Bom[i].getOne_frame_bom() + 1) {
            mapMatrix[rowY][colX] = 'p';
            if(bomberman != null ) {
                bomberman.checkOutBomb(Bom[i]);
            }
        }
        if (Bom[i].getFrame_bom() == 3 * Bom[i].getOne_frame_bom() + 1) {
            mapMatrix[rowY][colX] = ' ';
            if (Bom[i].getDistanceRight() > 0) {
                bricks[rowY][colX + Bom[i].getDistanceRight()].brickStartexploded();
            }
            if (Bom[i].getDistanceLeft() > 0) {
                bricks[rowY][colX - Bom[i].getDistanceLeft()].brickStartexploded();
            }
            if (Bom[i].getDistanceUp() > 0) {
                bricks[rowY - Bom[i].getDistanceUp()][colX].brickStartexploded();
            }
            if (Bom[i].getDistanceDown() > 0) {
                bricks[rowY + Bom[i].getDistanceDown()][colX].brickStartexploded();
            }
        }
        if (Bom[i].getFrame_bom() == 1) {
            if (Bom[i].getDistanceRight() > 0) {
                if (mapMatrix[rowY][colX + Bom[i].getDistanceRight()] != 'x') {
                    mapMatrix[rowY][colX + Bom[i].getDistanceRight()] = ' ';
                    Bom[i].setCharAtMap(' ', rowY, colX + Bom[i].getDistanceRight());
                } else {
                    mapMatrix[rowY][colX + Bom[i].getDistanceRight()] = 'y';
                }
                if (bricks[rowY][colX + Bom[i].getDistanceRight()].getSetItem()) {
                    bricks[rowY][colX + Bom[i].getDistanceRight()].setImg(bricks[rowY][colX + Bom[i].getDistanceRight()].getFlameItem());
                    if (mapMatrix[rowY][colX + Bom[i].getDistanceRight()] != 'y') {
                        listFlameItem.add(bricks[rowY][colX + Bom[i].getDistanceRight()]);
                    }

                } else {
                    stillObjects.remove(bricks[rowY][colX + Bom[i].getDistanceRight()]);
                }
            }
            if (Bom[i].getDistanceLeft() > 0) {
                if (mapMatrix[rowY][colX - Bom[i].getDistanceLeft()] != 'x') {
                    mapMatrix[rowY][colX - Bom[i].getDistanceLeft()] = ' ';
                    Bom[i].setCharAtMap(' ', rowY, colX - Bom[i].getDistanceLeft());
                } else {
                    mapMatrix[rowY][colX - Bom[i].getDistanceLeft()] = 'y';
                }
                if (bricks[rowY][colX - Bom[i].getDistanceLeft()].getSetItem()) {
                    bricks[rowY][colX - Bom[i].getDistanceLeft()].setImg(bricks[rowY][colX - Bom[i].getDistanceLeft()].getFlameItem());
                    if (mapMatrix[rowY][colX - Bom[i].getDistanceLeft()] != 'y') {
                        listFlameItem.add(bricks[rowY][colX - Bom[i].getDistanceLeft()]);
                    }
                } else {
                    stillObjects.remove(bricks[rowY][colX - Bom[i].getDistanceLeft()]);
                }
            }
            if (Bom[i].getDistanceUp() > 0) {
                if (mapMatrix[rowY - Bom[i].getDistanceUp()][colX] != 'x') {
                    mapMatrix[rowY - Bom[i].getDistanceUp()][colX] = ' ';
                    Bom[i].setCharAtMap(' ', rowY - Bom[i].getDistanceUp(), colX);
                } else {
                    mapMatrix[rowY - Bom[i].getDistanceUp()][colX] = 'y';
                }
                if (bricks[rowY - Bom[i].getDistanceUp()][colX].getSetItem()) {
                    if (mapMatrix[rowY - Bom[i].getDistanceUp()][colX] != 'y') {
                        listFlameItem.add(bricks[rowY - Bom[i].getDistanceUp()][colX]);
                    }
                    bricks[rowY - Bom[i].getDistanceUp()][colX].setImg(bricks[rowY - Bom[i].getDistanceUp()][colX].getFlameItem());

                } else {
                    stillObjects.remove(bricks[rowY - Bom[i].getDistanceUp()][colX]);
                }
            }
            if (Bom[i].getDistanceDown() > 0) {
                if (mapMatrix[rowY + Bom[i].getDistanceDown()][colX] != 'x') {
                    mapMatrix[rowY + Bom[i].getDistanceDown()][colX] = ' ';
                    Bom[i].setCharAtMap(' ', rowY + Bom[i].getDistanceDown(), colX);
                } else {
                    mapMatrix[rowY + Bom[i].getDistanceDown()][colX] = 'y';
                }
                if (bricks[rowY + Bom[i].getDistanceDown()][colX].getSetItem()) {
                    bricks[rowY + Bom[i].getDistanceDown()][colX].setImg(bricks[rowY + Bom[i].getDistanceDown()][colX].getFlameItem());
                    if (mapMatrix[rowY + Bom[i].getDistanceDown()][colX] != 'y') {
                        listFlameItem.add(bricks[rowY + Bom[i].getDistanceDown()][colX]);
                    }
                } else {
                    stillObjects.remove(bricks[rowY + Bom[i].getDistanceDown()][colX]);
                }
            }
        }
    }

    /**
     * This function handles entities' collision.
     */
    public void checkCollision() {
        for (int i = 0; i < numberBom; i++) {
            if (Bom[i].getStatus()) {
                for (Enemy j : enemies) {
                    if (bomberman != null) {
                        if (check.checkCollisionWithBomb(Bom[i], j, Bom[i].getExplodeRight(), Bom[i].getExplodeLeft(),
                                Bom[i].getExplodeUp(), Bom[i].getExplodeDown()) && !j.isAdd_to_remove()) {
                            score += 200;
                            j.setAdd_to_remove(true);
                            deleteEnemies.add(j);
                        }
                    }
                }
                if (bomberman != null) {
                    if (check.checkCollisionWithBomb(Bom[i], bomberman)) {
                        bomberman.setPos(Sprite.SCALED_SIZE, 2 * Sprite.SCALED_SIZE, true);
                        if (lives.size() != 0) {
                            lives.remove(lives.size() - 1);
                        }
                    }
                }
            }
        }
        for (Brick i : listFlameItem) {
            if(bomberman != null ) {
                String s = check.checkCollisionWithFlameItem(bomberman, i);
                if (s.length() > 0) {
                    deleteFlameItem.add(i);
                }
                switch (s) {
                    case "speed" -> bomberman.setSpeed(bomberman.getSpeed() + 1);
                    case "bomb" -> numberBom++;
                    case "explode" -> SizeBom++;
                }
            }
        }
        for (Brick i : deleteFlameItem) {
            stillObjects.remove(i);
            listFlameItem.remove(i);
        }
        deleteFlameItem.clear();
        for (int i = 0; i < deleteEnemies.size(); i++) {
            Enemy object = deleteEnemies.get(i);
            if (object.isCan_remove()) {
                enemies.remove(object);
                deleteEnemies.remove(object);
            }
        }
        for (Enemy i : enemies) {
            if(bomberman != null) {
                if (check.checkCollisionWithEnemy(bomberman, i)) {
                    bomberman.setPos(Sprite.SCALED_SIZE, Sprite.SCALED_SIZE * 2, true);
                    if (lives.size() != 0) {
                        lives.remove(lives.size() - 1);
                    }
                }
            }
        }
        if (lives.isEmpty()) {
            isPaused = true;
            gameOverScene.getStatus().setText("YOU LOSE :(");
            gameOverScene.setScoreNumber(new_score);
            gameOverScene.getScore().setText("YOUR SCORE: " + new_score);
            gameOverScene.showSubscene();
            level = 1;
            timer.stop();
            Sound.stopBackground();
            Sound.playSound(Sound.loseGame);
        }
        if(bomberman != null) {
            if(check.checkCollisionWithPortal(bomberman) && enemies.size() == 0) {
                uplevel();
            }
        }
    }

    /**
     * This function clears the game stage.
     */
    public void clearAll() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().removeAll(text, scoreText);
        score = 0;
        new_score = 0;
        scoreText.setText("0");
        frame_bom = -1;
        numberBom = 1;
        SizeBom = 1;
        lives.clear();
        stillObjects.clear();
        listFlameItem.clear();
        deleteFlameItem.clear();
        enemies.clear();
        deleteEnemies.clear();
        Bom0.clear();
        Bom1.clear();
        bomberman.setSpeed(2);
        Sound.stopBackground();
        stage.hide();
    }

    /**
     * This function handles when player has finished a map.
     */
    public void uplevel() {
        if (level == 1) {
            clearAll();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Sound.playSound(Sound.changeMap);
            level++;
            sound.playBackground();
            mapMatrix = map.createMap(level);
            root.getChildren().addAll(scoreText, text);
            bomberman = new Bomber(1, 2, Sprite.player_right.getFxImage());
            map.addEntity_map2(stillObjects, bricks, enemies, bomberman, lives);
            stage.show();
        } else {
            Sound.playSound(Sound.winGame);
            Sound.stopBackground();
            level = 1;
            gameOverScene.getStatus().setText("YOU WIN ^^");
            gameOverScene.setScoreNumber(score);
            gameOverScene.showSubscene();
            timer.stop();

        }
    }

    /**
     * This function adds in-game texts.
     */
    public void addText() {
        text.setFont(font);
        text.setFill(Color.ORANGERED);
        scoreText.setFont(font);
        scoreText.setFill(Color.ORANGERED);
        scoreText.setX(100);
        scoreText.setY(25);
        scoreText.setText(Integer.toString(score));

        text.setX(25);
        text.setY(25);
        text.setText("Score: ");
        root.getChildren().addAll(text, scoreText);
    }
}
