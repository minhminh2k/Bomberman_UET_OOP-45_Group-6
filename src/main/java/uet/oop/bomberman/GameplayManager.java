package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.Maps.MapLevel;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemies.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Item.BomItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.TitleMap.Brick;
import uet.oop.bomberman.entities.TitleMap.Grass;
import uet.oop.bomberman.entities.TitleMap.Wall;
import uet.oop.bomberman.entities.MainMenu.*;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GameplayManager {
    public int level = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private Scene scene;
    private Group root;
    private Stage stage;

    private PauseSubsceneManager pauseScene = new PauseSubsceneManager();
    private boolean isPaused = false;

    private AnimationTimer timer;
    private MapLevel map = new MapLevel();
    private Text scoreText = new Text();
    Text text = new Text();
    private int score = 0;
    private int new_score = 0;
    private Font font = Font.loadFont("file:res/BreathFire.ttf", 25);
    public Sound sound = new Sound();
    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;
    public int frame_bom = -1;
    public int numberBom = 1;
    public int SizeBom = 1;

    public static char[][] mapMatrix = new char[HEIGHT][WIDTH];
    public int Row = HEIGHT;
    public int Col = WIDTH;
    private List<Entity> lifes = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private List<Brick> listFlameItem = new ArrayList<>();
    private List<Brick> deleteFlameItem = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Enemy> deleteEnemies = new ArrayList<>();
    private List<Entity> Bom0 = new ArrayList<>();
    private List<Entity> Bom1 = new ArrayList<>();
    CheckCollision check = new CheckCollision();
    BomItem[] Bom = new BomItem[3];
    Brick[][] bricks = new Brick[HEIGHT][WIDTH];
    public int p = 0;
    Bomber bomberman = new Bomber(1, 2, Sprite.player_right.getFxImage());

    public void startGame(Stage menuStage) {

        //Hiding the menuStage
        menuStage.hide();

        //Initializing and handling Stage/Scenes
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        stage = new Stage();
        root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(pauseScene);
        scene = new Scene(root);
        stage.setTitle("Bomberman");
        stage.setScene(scene);
        stage.show();

        addText();

        if(bomberman != null) {
            mapMatrix = map.createMap(level);

            //bomberman.updateMap(mapMatrix, Row, Col);
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
            scene.setOnKeyPressed(keyEvent -> {
                KeyCode keyCode = keyEvent.getCode();

                switch (keyCode) {
                    case BACK_SPACE: {
                        if (!isPaused) {
                            pauseGame();
                            break;
                        } else {
                            resumeGame();
                            break;
                        }
                    }
                    case RIGHT: {
                        bomberman.setMoving(true);
                        bomberman.addMove("right");
                        bomberman.updateLastStatus("right");
                        break;
                    }
                    case LEFT: {
                        bomberman.setMoving(true);
                        bomberman.addMove("left");
                        bomberman.updateLastStatus("left");
                        break;
                    }
                    case UP: {
                        bomberman.setMoving(true);
                        bomberman.addMove("up");
                        bomberman.updateLastStatus("up");
                        break;
                    }
                    case DOWN: {
                        bomberman.setMoving(true);
                        bomberman.addMove("down");
                        bomberman.updateLastStatus("down");
                        break;
                    }
                    case SPACE: {
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
                //bomberman.updateLastStatus();

            });
            scene.setOnKeyReleased(keyEvent -> {
                KeyCode keyCode = keyEvent.getCode();
                switch (keyCode) {

                    case RIGHT: {
                        if (bomberman.checkStatus("right")) {
                            bomberman.deleteMove("right");
                        }
                        bomberman.updateLastStatus();
                        break;
                    }
                    case LEFT: {
                        if (bomberman.checkStatus("left")) {
                            bomberman.deleteMove("left");
                        }
                        bomberman.updateLastStatus();
                        break;
                    }
                    case UP: {
                        if (bomberman.checkStatus("up")) {
                            bomberman.deleteMove("up");
                        }
                        bomberman.updateLastStatus();
                        break;
                    }
                    case DOWN: {
                        if (bomberman.checkStatus("down")) {
                            bomberman.deleteMove("down");
                        }
                        bomberman.updateLastStatus();
                        break;
                    }
                }
                if (bomberman.sizeMoving() == 0) {
                    bomberman.setMoving(false);
                }

            });

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

//                    for (int i = 0; i < enemies.size(); i++) {
//                        Enemy object = enemies.get(i);
//                        object.updateMap(mapMatrix, Row, Col);
//                    }
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
            timer.start();
            map.addEntity_map1(stillObjects, bricks, enemies, bomberman, lifes);
        }
    }

    public void pauseGame() {
        pauseScene.showPausedMenu();
        isPaused = true;
        timer.stop();
        Sound.pauseBackground();
    }

    public void resumeGame() {
        pauseScene.hidePausedMenu();
        isPaused = false;
        timer.start();
        sound.playBackground();
    }

    public void update() {
        enemies.forEach(Entity::update);
        if(bomberman != null)
        bomberman.update();
        stillObjects.forEach(Entity::update);
        lifes.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        Bom0.forEach(g -> g.render(gc));
        Bom1.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        lifes.forEach(g -> g.render(gc));
        if(bomberman != null) {
            bomberman.render(gc);
        }
    }

    public void handleBrickBom(int i) {
        int rowY = (Bom[i].getY() + 16) / 32;
        int colX = (Bom[i].getX() + 16) / 32;
        if (Bom[i].getFrame_bom() > 3 * Bom[i].getOne_frame_bom() + 1) {
            mapMatrix[rowY][colX] = 'p';
            bomberman.checkOutBomb(Bom[i]);
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
                mapMatrix[rowY][colX + Bom[i].getDistanceRight()] = ' ';
                Bom[i].setCharAtMap(' ', rowY, colX + Bom[i].getDistanceRight());
                if (bricks[rowY][colX + Bom[i].getDistanceRight()].getSetItem()) {
                    bricks[rowY][colX + Bom[i].getDistanceRight()].setImg(bricks[rowY][colX + Bom[i].getDistanceRight()].getFlameItem());
                    listFlameItem.add(bricks[rowY][colX + Bom[i].getDistanceRight()]);
                } else {
                    stillObjects.remove(bricks[rowY][colX + Bom[i].getDistanceRight()]);
                }
            }
            if (Bom[i].getDistanceLeft() > 0) {
                mapMatrix[rowY][colX - Bom[i].getDistanceLeft()] = ' ';
                Bom[i].setCharAtMap(' ', rowY, colX - Bom[i].getDistanceLeft());
                if (bricks[rowY][colX - Bom[i].getDistanceLeft()].getSetItem()) {
                    bricks[rowY][colX - Bom[i].getDistanceLeft()].setImg(bricks[rowY][colX - Bom[i].getDistanceLeft()].getFlameItem());
                    listFlameItem.add(bricks[rowY][colX - Bom[i].getDistanceLeft()]);
                } else {
                    stillObjects.remove(bricks[rowY][colX - Bom[i].getDistanceLeft()]);
                }
            }
            if (Bom[i].getDistanceUp() > 0) {
                mapMatrix[rowY - Bom[i].getDistanceUp()][colX] = ' ';
                Bom[i].setCharAtMap(' ', rowY - Bom[i].getDistanceUp(), colX);
                if (bricks[rowY - Bom[i].getDistanceUp()][colX].getSetItem()) {
                    bricks[rowY - Bom[i].getDistanceUp()][colX].setImg(bricks[rowY - Bom[i].getDistanceUp()][colX].getFlameItem());
                    listFlameItem.add(bricks[rowY - Bom[i].getDistanceUp()][colX]);
                } else {
                    stillObjects.remove(bricks[rowY - Bom[i].getDistanceUp()][colX]);
                }
            }
            if (Bom[i].getDistanceDown() > 0) {
                mapMatrix[rowY + Bom[i].getDistanceDown()][colX] = ' ';
                Bom[i].setCharAtMap(' ', rowY + Bom[i].getDistanceDown(), colX);
                if (bricks[rowY + Bom[i].getDistanceDown()][colX].getSetItem()) {
                    bricks[rowY + Bom[i].getDistanceDown()][colX].setImg(bricks[rowY + Bom[i].getDistanceDown()][colX].getFlameItem());
                    listFlameItem.add(bricks[rowY + Bom[i].getDistanceDown()][colX]);
                } else {
                    stillObjects.remove(bricks[rowY + Bom[i].getDistanceDown()][colX]);
                }
            }
        }
    }

    public void checkCollision() {
        for (int i = 0; i < numberBom; i++) {
            if (Bom[i].getStatus()) {
                for (Enemy j : enemies) {
                    if (check.checkCollisionWithBomb(Bom[i], j, Bom[i].getDistanceRight(), Bom[i].getDistanceLeft(),
                            Bom[i].getDistanceUp(), Bom[i].getDistanceDown()) && !j.isAdd_to_remove()) {
                        score += 200;
                        j.setAdd_to_remove(true);
                        deleteEnemies.add(j);
                    }
                }
                if (check.checkCollisionWithBomb(Bom[i], bomberman)) {
                    bomberman.setPos(Sprite.SCALED_SIZE, 2 * Sprite.SCALED_SIZE, true);
                    if (lifes.size() != 0) {
                        lifes.remove(lifes.size() - 1);
                    }
                }
            }
        }
        for (Brick i : listFlameItem) {
            String s = check.checkCollisionWithFlameItem(bomberman, i);
            if (s.length() > 0) {
                deleteFlameItem.add(i);
            }
            if (s == "speed") {
                bomberman.setSpeed(bomberman.getSpeed() + 1);
            } else if (s == "bomb") {
                numberBom++;
            } else if (s == "explode") {
                SizeBom++;
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
            if (check.checkCollisionWithEnemy(bomberman, i) ) {
                bomberman.setPos(Sprite.SCALED_SIZE, Sprite.SCALED_SIZE * 2, true);
                if (lifes.size() != 0) {
                    lifes.remove(lifes.size() - 1);
                }
            }
        }
        if(lifes.isEmpty()) {
            clearAll();
            uplevel();
        }
    }

    public void clearAll() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().removeAll(text,scoreText);
        score = 0;
        new_score = 0;
        scoreText.setText("0");
        frame_bom = -1;
        numberBom = 1;
        SizeBom = 1;
        lifes.clear();
        stillObjects.clear();
        listFlameItem.clear();
        deleteFlameItem.clear();
        enemies.clear();
        deleteEnemies.clear();
        Bom0.clear();
        Bom1.clear();
        p = 0;
        Sound.stopBackground();
        //bomberman = null;
    }
    public void uplevel() {
        if (level == 1) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            level++;
            sound.playBackground();
            mapMatrix = map.createMap(level);
            root.getChildren().addAll(scoreText, text);
            bomberman = new Bomber(1, 2, Sprite.player_right.getFxImage());
            map.addEntity_map2(stillObjects, bricks, enemies, bomberman, lifes);
        }
        else {
            pauseGame();
            System.out.println("u win");
        }
    }
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
