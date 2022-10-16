package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.BomItem;
import uet.oop.bomberman.entities.Enemies.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int one_frame_bom = 15;
    public int frame_bom = -1;
    public int numberBom = 1;
    public int SizeBom = 1;

    public char[][] mapMatrix;

    public int Row;
    public int Col;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    BomItem[]Bom = new BomItem[numberBom];
    Brick [][]bricks = new Brick[HEIGHT][WIDTH];

    //private Bomber bomberman;

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

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        bomberman.updateMap(mapMatrix, Row, Col);
        for( int i = 0; i < numberBom; i++) {
            Bom[i] = new BomItem() {
                @Override
                public void update() {
                }
            };
            Bom[i].setSize(HEIGHT, WIDTH);
        }
        for(int i = 0; i < HEIGHT; i++) {
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
                    for( int i = 0; i < numberBom; i++) {
                        if(!Bom[i].status) {
                            int x = (bomberman.getX()+32)  / 32;
                            x = ( x*32 - bomberman.getX() ) > (bomberman.getX()+32 - 32* x) ? bomberman.getX() / 32 : x;
                            int y = (bomberman.getY()+ 32)  / 32;
                            y = ( y*32 - bomberman.getY() ) > (bomberman.getY()+32 - 32* y) ? bomberman.getY() / 32 : y;
                            Bom[i].setStillObjects(stillObjects);
                            Bom[i].setPos(x,y);
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
                    if(bomberman.checkStatus("right")){
                        bomberman.deleteMove("right");
                    }
                    break;
                }
                case LEFT: {
                    if(bomberman.checkStatus("left")){
                        bomberman.deleteMove("left");
                    }
                    break;
                }
                case UP: {
                    if(bomberman.checkStatus("up")){
                        bomberman.deleteMove("up");
                    }
                    break;
                }
                case DOWN: {
                    if(bomberman.checkStatus("down")){
                        bomberman.deleteMove("down");
                    }
                    break;
                }
            }
            if(bomberman.sizeMoving() == 0 ) {
                //System.out.println("Not moving");
                bomberman.setMoving(false);
            }

        });

        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for(int i = 0; i < numberBom; i++) {
                    if (Bom[i].getStatus()){
                        Bom[i].setStillObjects(stillObjects);
                        Bom[i].frameBom();
                        stillObjects = Bom[i].getStillObjects();
                        /*if(Bom[i].getFrame_bom() == 3*Bom[i].getOne_frame_bom()+1) {
                            if(Bom[i].checkRight() > 0) {
                            }
                        }*/
                    }
                    Bom[i].updateMap(mapMatrix, Row, Col);
                }

                for(int i = 0; i < entities.size(); i++) {
                    Entity object = entities.get(i);
                    object.updateMap(mapMatrix, Row, Col);

                    if(object instanceof Enemy) {
                        if(((Enemy) object).isCan_remove()) {
                            entities.remove(object);
                            i--;
                        }
                    }
                }
                //bomberman.updateMap(mapMatrix, Row, Col);
                //frameBom();
                render();
                update();
            }
        };
        timer.start();

        createMapAfter();

        Entity oneal = new Oneal(6, 7, Sprite.oneal_left1.getFxImage(), bomberman);
        entities.add(oneal);
        Entity kon = new Kondoria(10, 10, Sprite.kondoria_left1.getFxImage(), bomberman);
        entities.add(kon);
        Entity ghost = new Ghost(22, 9,Sprite.ghost_left1.getFxImage(), bomberman);
        entities.add(ghost);
        Entity doll = new Doll(22, 9 , Sprite.doll_left1.getFxImage());
        entities.add(doll);
        Entity minvo = new Minvo(13, 9, Sprite.minvo_left1.getFxImage(), bomberman);
        entities.add(minvo);
    }
    public void createMapAfter() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                char txt = mapMatrix[i][j];
                switch (txt) {
                    case '#' : {
                        Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case '*' : {
                        bricks[i][j] = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case 'f' : {
                        Entity object = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case '1' : {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Entity objects = new Ballon(j, i, Sprite.balloom_left3.getFxImage());
                        entities.add(objects);
                        break;
                    }
//                    case '2' : {
//                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
//                        stillObjects.add(object);
//                        Entity objects = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
//                        entities.add(objects);
//                        break;
//                    }
                    default: {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                }
            }
        }
    }

    // add Grass before add monster
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
            /*System.out.println(level);
            System.out.println(row);
            System.out.println(column);
            System.out.println(c);*/

            mapMatrix = new char[row][column];
            for (int i = 0; i < row; i++) {
                String rowText = bufferedReader.readLine();
                for (int j = 0; j < column; j++) {
                    mapMatrix[i][j] = rowText.charAt(j);
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
