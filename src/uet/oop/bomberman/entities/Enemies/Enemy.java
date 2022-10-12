package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int animate = 0;
    protected int move_distance = 0;
    protected final int MAX_ANIMATE = 30;
    protected int speed = 1;
    protected Move currentDirection;
    public static final Random rand = new Random();
    public int[][] mapGame = new int[100][100];
    protected int Di;
    protected boolean canMoveL;
    protected boolean canMoveR;
    protected boolean canMoveU;
    protected boolean canMoveD;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        this.randomDirection();
        this.setSpeed(1);
        Di = 0;
        setIntMap();
        canMove();
    }

    @Override
    public abstract void update();

    public abstract void change_direction();
    public abstract void moveEnemy();
    public abstract void chooseSprite();
    protected void animate() {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
    }
    public void setIntMap() {
        mapGame = new int[][]{
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                            {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                            {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                            {1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                            {1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    }

    public void randomDirection() {
        Random rd = new Random();
        int randomDi = rd.nextInt(4);
        if (randomDi % 4 == 0) currentDirection = Move.LEFT;
        if (randomDi % 4 == 1) currentDirection = Move.RIGHT;
        if (randomDi % 4 == 2) currentDirection = Move.UP;
        if (randomDi % 4 == 3) currentDirection = Move.DOWN;
    }

    public void canMove() {
        if (this.getX() % 32 == 0 && this.getY() % 32 == 0) {
            int x_pos = this.getX() / 32;
            int y_pos = this.getY() / 32;
            if (mapGame[y_pos][x_pos - 1] == 1) {
                canMoveL = false;
            }
            if (mapGame[y_pos][x_pos + 1] == 1) {
                canMoveR = false;
            }
            if (mapGame[y_pos - 1][x_pos] == 1) {
                canMoveU = false;
            }
            if (mapGame[y_pos + 1][x_pos] == 1) {
                canMoveD = false;
            }
            if (mapGame[y_pos][x_pos - 1] == 0) {
                canMoveL = true;
            }
            if (mapGame[y_pos][x_pos + 1] == 0) {
                canMoveR = true;
            }
            if (mapGame[y_pos - 1][x_pos] == 0) {
                canMoveU = true;
            }
            if (mapGame[y_pos + 1][x_pos] == 0) {
                canMoveD = true;
            }
        }
    }

    public int getMove_distance() {
        return move_distance;
    }

    public void setMove_distance(int move_distance) {
        this.move_distance = move_distance;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Move getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Move currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getSpeed() {
        return speed;
    }
}
