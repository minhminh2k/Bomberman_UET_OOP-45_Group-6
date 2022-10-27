package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemies.*;
import uet.oop.bomberman.entities.Item.BomItem;
import uet.oop.bomberman.entities.Item.FlameItem;
import uet.oop.bomberman.entities.TitleMap.Brick;
import uet.oop.bomberman.entities.TitleMap.Grass;
import uet.oop.bomberman.entities.TitleMap.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public Sound sound = new Sound();
    public static final int WIDTH = 31;
    public static final int HEIGHT = 14;
    public int frame_bom = -1;
    public int numberBom = 1;
    public int SizeBom = 1;

    public char[][] mapMatrix;
    public int Row;
    public int Col;

    private GraphicsContext gc;
    private Canvas canvas;
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

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        getMap();

        bomberman.updateMap(mapMatrix, Row, Col);
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

        // Tao scene
        Scene scene = new Scene(root);

        // Add scene vao stage
        stage.setScene(scene);
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();

            switch (keyCode) {
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
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
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
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy object = enemies.get(i);
                    object.updateMap(mapMatrix, Row, Col);
                }
                sound.soundMoving(bomberman);
                sound.playBackground();
                checkCollision();
                render();
                update();
            }
        };
        timer.start();
        createMapAfter();
        setEnemies();
    }


    public void createMapAfter() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                char txt = mapMatrix[i][j];
                switch (txt) {
                    case '#': {
                        Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case '*': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case 'f': {
                        Entity object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case '1': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Ballon(j, i, Sprite.balloom_left3.getFxImage());
                        enemies.add(objects);
                        break;
                    }
                    case 'b': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.powerup_bombs.getFxImage());
                        bricks[i][j].setPower("bomb");
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case 'e': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.powerup_flames.getFxImage());
                        bricks[i][j].setPower("explode");
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case 's': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.powerup_speed.getFxImage());
                        bricks[i][j].setPower("speed");
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    default: {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                }
            }
        }
    }

    public void getMap() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            Reader reader = new FileReader("res/levels/Level1.txt");
            bufferedReader = new BufferedReader(reader);

            int level, row, column;
            String c = bufferedReader.readLine();
            String[] tokens = c.split("\\s");
            level = Integer.parseInt(tokens[0]);
            Row = row = Integer.parseInt(tokens[1]);
            Col = column = Integer.parseInt(tokens[2]);

            mapMatrix = new char[row][column];
            for (int i = 0; i < row; i++) {
                String rowText = bufferedReader.readLine();
                for (int j = 0; j < column; j++) {
                    mapMatrix[i][j] = rowText.charAt(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update() {
        enemies.forEach(Entity::update);
        bomberman.update();
        stillObjects.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        Bom0.forEach(g -> g.render(gc));
        Bom1.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }

    public void handleBrickBom(int i) {
        int rowY = (Bom[i].getY() + 16) / 32;
        int colX = (Bom[i].getX() + 16) / 32;
        if (Bom[i].getFrame_bom() > 3 * Bom[i].getOne_frame_bom() + 1) {
            mapMatrix[rowY][colX] = 'p';
            bomberman.checkOutBomb(Bom[i]);
        }
        if (Bom[i].getFrame_bom() == 3 * Bom[i].getOne_frame_bom()+1) {
            mapMatrix[rowY][colX] = ' ';
            if(Bom[i].getDistanceRight() > 0) {
                bricks[rowY][colX + Bom[i].getDistanceRight()].brickStartexploded();
            }
            if(Bom[i].getDistanceLeft() > 0) {
                bricks[rowY][colX - Bom[i].getDistanceLeft()].brickStartexploded();
            }
            if(Bom[i].getDistanceUp() > 0) {
                bricks[rowY - Bom[i].getDistanceUp()][colX].brickStartexploded();
            }
            if(Bom[i].getDistanceDown() > 0) {
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

    public void setEnemies() {
        Enemy oneal = new Oneal(6, 7, Sprite.oneal_left1.getFxImage(), bomberman);
        enemies.add(oneal);
        Enemy kon = new Kondoria(10, 10, Sprite.kondoria_left1.getFxImage(), bomberman);
        enemies.add(kon);
        Enemy ghost = new Ghost(22, 10, Sprite.ghost_left1.getFxImage(), bomberman);
        enemies.add(ghost);
        Enemy doll = new Doll(22, 9, Sprite.doll_left1.getFxImage());
        enemies.add(doll);
        Enemy minvo = new Minvo(13, 9, Sprite.minvo_left1.getFxImage(), bomberman);
        enemies.add(minvo);
    }

    public void checkCollision() {
        for (int i = 0; i < numberBom; i++) {
            if (Bom[i].getStatus()) {
                for (Enemy j : enemies) {
                    if (check.checkCollisionWithBomb(Bom[i], j, Bom[i].getDistanceRight(), Bom[i].getDistanceLeft(),
                            Bom[i].getDistanceUp(), Bom[i].getDistanceDown()) && !j.isAdd_to_remove()) {
                        j.setAdd_to_remove(true);
                        deleteEnemies.add(j);
                    }
                }
                if (check.checkCollisionWithBomb(Bom[i], bomberman)) {
                    bomberman.setPos(Sprite.SCALED_SIZE, 2 * Sprite.SCALED_SIZE, true);
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
            }
        }

    }

}
