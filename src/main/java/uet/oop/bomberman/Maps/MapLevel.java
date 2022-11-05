package uet.oop.bomberman.Maps;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Enemies.*;
import uet.oop.bomberman.entities.Item.*;
import uet.oop.bomberman.entities.Lives.Life;
import uet.oop.bomberman.entities.TitleMap.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapLevel {
    public static int End_x;
    public static int End_y;
    private static final int HEIGHT = 14;
    private static final int WIDTH = 31;
    private char[][] mapMatrix = new char[HEIGHT][WIDTH];

    public char[][] createMap(int levels) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            Reader reader = new FileReader("res/levels/Level" + levels + ".txt");
            bufferedReader = new BufferedReader(reader);

            int level, row, column;
            String c = bufferedReader.readLine();
            String[] tokens = c.split("\\s");
            level = Integer.parseInt(tokens[0]);
            row = Integer.parseInt(tokens[1]);
            column = Integer.parseInt(tokens[2]);
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
        return mapMatrix;
    }


    public void addEntity_map1(List<Entity> stillObjects,
                               Brick[][] bricks, List<Enemy> enemies, Bomber bomberman, List<Entity> lifes) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                char txt = mapMatrix[i][j];
                switch (txt) {
                    case 'x': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.portal.getFxImage());
                        bricks[i][j].setPower("end");
                        stillObjects.add(bricks[i][j]);
                        End_x = j*32;
                        End_y = i*32;
                        break;
                    }
                    case 'o': {
                        Entity object = new Grass(j, i, Sprite.white.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case 'l': {
                        Entity object = new Grass(j, i, Sprite.white.getFxImage());
                        stillObjects.add(object);
                        Entity objects = new Life(j, i, Sprite.heart.getFxImage());
                        lifes.add(objects);
                        break;
                    }
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
                    case '1': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Ballon(j, i, Sprite.balloom_left3.getFxImage());
                        //enemies.add(objects);
                        break;
                    }
                    case '2': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Oneal(j, i, Sprite.oneal_left1.getFxImage(), bomberman);
                        //enemies.add(objects);
                        break;
                    }
                    case '3': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Doll(j, i, Sprite.doll_left1.getFxImage());
                        //enemies.add(objects);
                        break;
                    }
                    case '4': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Ghost(j, i, Sprite.ghost_left1.getFxImage(), bomberman);
                        //enemies.add(objects);
                        break;
                    }
                    case '5': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Kondoria(j, i, Sprite.kondoria_left1.getFxImage(), bomberman);
                        //enemies.add(objects);
                        break;
                    }
                    case '6': {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Minvo(j, i, Sprite.minvo_left1.getFxImage(), bomberman);
                        //enemies.add(objects);
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

    public void addEntity_map2(List<Entity> stillObjects, Brick[][] bricks,
                               List<Enemy> enemies, Bomber bomberman, List<Entity> lifes) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                char txt = mapMatrix[i][j];
                switch (txt) {
                    case 'x': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick_map2.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.portal.getFxImage());
                        bricks[i][j].setPower("end");
                        stillObjects.add(bricks[i][j]);
                        End_x = j*32;
                        End_y = i*32;
                        break;
                    }
                    case 'o': {
                        Entity object = new Grass(j, i, Sprite.white.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case 'l': {
                        Entity object = new Grass(j, i, Sprite.white.getFxImage());
                        stillObjects.add(object);
                        Entity objects = new Life(j, i, Sprite.heart.getFxImage());
                        lifes.add(objects);
                        break;
                    }
                    case '#': {
                        Entity object = new Wall(j, i, Sprite.wall_map2.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                    case '*': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick_map2.getFxImage());
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case '1': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Ballon(j, i, Sprite.balloom_left3.getFxImage());
                        enemies.add(objects);
                        break;
                    }
                    case '2': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Oneal(j, i, Sprite.oneal_left1.getFxImage(), bomberman);
                        enemies.add(objects);
                        break;
                    }
                    case '3': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Doll(j, i, Sprite.doll_left1.getFxImage());
                        enemies.add(objects);
                        break;
                    }
                    case '4': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Ghost(j, i, Sprite.ghost_left1.getFxImage(), bomberman);
                        enemies.add(objects);
                        break;
                    }
                    case '5': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Kondoria(j, i, Sprite.kondoria_left1.getFxImage(), bomberman);
                        enemies.add(objects);
                        break;
                    }
                    case '6': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        Enemy objects = new Minvo(j, i, Sprite.minvo_left1.getFxImage(), bomberman);
                        enemies.add(objects);
                        break;
                    }
                    case 'b': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick_map2.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.powerup_bombs.getFxImage());
                        bricks[i][j].setPower("bomb");
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case 'e': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick_map2.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.powerup_flames.getFxImage());
                        bricks[i][j].setPower("explode");
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    case 's': {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        bricks[i][j] = new Brick(j, i, Sprite.brick_map2.getFxImage());
                        bricks[i][j].setFlameItem(Sprite.powerup_speed.getFxImage());
                        bricks[i][j].setPower("speed");
                        stillObjects.add(bricks[i][j]);
                        break;
                    }
                    default: {
                        Entity object = new Grass(j, i, Sprite.grass_map2.getFxImage());
                        stillObjects.add(object);
                        break;
                    }
                }
            }
        }
    }

    public void printArray() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(mapMatrix[i][j]);
            }
            System.out.println("");
        }
    }

}

