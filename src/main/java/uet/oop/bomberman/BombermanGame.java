package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 35;
    public static final int HEIGHT = 15;

    public char[][] mapMatrix;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


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

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
        CreateMap_Test();
    }

    // add Grass before add monster
    public void CreateMap_Test() {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            Reader reader = new FileReader("res/levels/Level1.txt");
            bufferedReader = new BufferedReader(reader);

            int level = 0;
            int row = 0;
            int column = 0;
            String c = bufferedReader.readLine();
            String[] tokens = c.split("\\s");
            level = Integer.parseInt(tokens[0]);
            row = Integer.parseInt(tokens[1]);
            column = Integer.parseInt(tokens[2]);
            System.out.println(level);
            System.out.println(row);
            System.out.println(column);
            System.out.println(c);

            mapMatrix = new char[row][column];
            for (int i = 0; i < row; i++) {
                String rowText = bufferedReader.readLine();
                for (int j = 0; j < column; j++) {
                    mapMatrix[i][j] = rowText.charAt(j);
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    char txt = mapMatrix[i][j];
                    switch (txt) {
                        case '#' : {
                            Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
//                        case ' ' : {
//                            Entity object = new Grass(j, i, Sprite.grass.getFxImage());
//                            stillObjects.add(object);
//                            break;
//                        }
                        case '*' : {
                            Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                        case '1' : {
                            Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            Entity objects = new Balloon(j, i, Sprite.balloom_left3.getFxImage());
                            stillObjects.add(objects);
                            break;
                        }
                        case '2' : {
                            Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            Entity objects = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            stillObjects.add(objects);
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



//           int c;
//            while ((c = bufferedReader.read()) != -1) {
//                bufferedWriter.write(c);
//            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (bufferedReader != null) {
               try{
                   bufferedReader.close();
            } catch (IOException e) {
                   e.printStackTrace();
               }
//            if (bufferedWriter != null) {
//                bufferedWriter.close();
//            }
        }
    }
}

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
