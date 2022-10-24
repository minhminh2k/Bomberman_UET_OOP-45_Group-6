package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Random;

public abstract class Enemy extends Entity {
    protected int animate = 0;
    protected int move_distance = 0;
    protected int MAX_ANIMATE = 30;
    protected int speed = 1;
    protected Move currentDirection;
    public static final Random rand = new Random();
    public int[][] mapGame = new int[100][100];
    protected int Di;
    protected boolean canMoveL;
    protected boolean canMoveR;
    protected boolean canMoveU;
    protected boolean canMoveD;
    protected boolean survive = true;
    protected boolean can_remove;
    protected boolean add_to_remove;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        this.randomDirection();
        this.setSpeed(1);
        Di = 0;
        canMove();
        this.survive = true;
        this.can_remove = false;
        add_to_remove = false;
    }

    public boolean isAdd_to_remove() {
        return add_to_remove;
    }

    public void setAdd_to_remove(boolean add_to_remove) {
        this.add_to_remove = add_to_remove;
    }

    @Override
    public abstract void update();

    public abstract void moveEnemy();

    public abstract void chooseSprite();

    public void setDead() {
        if (this.survive) {
            this.setSurvive(false);
            this.setAnimate(0);
            Sound.playSound(Sound.soundEnemy);
        }
    }

    public void Dead_Animation() {
        animate();
        this.chooseSprite();
        if (this.animate == MAX_ANIMATE) {
            can_remove = true;
        }
    }

    protected void animate() {
        if (animate < MAX_ANIMATE) animate++;
        else animate = 0;
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
            canMoveL = map[y_pos][x_pos - 1] == ' ' || map[y_pos][x_pos - 1] == '1' || map[y_pos][x_pos - 1] == '2';
            canMoveR = map[y_pos][x_pos + 1] == ' ' || map[y_pos][x_pos + 1] == '1' || map[y_pos][x_pos + 1] == '2';
            canMoveU = map[y_pos - 1][x_pos] == ' ' || map[y_pos - 1][x_pos] == '1' || map[y_pos - 1][x_pos] == '2';
            canMoveD = map[y_pos + 1][x_pos] == ' ' || map[y_pos + 1][x_pos] == '1' || map[y_pos + 1][x_pos] == '2';
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

    public boolean isSurvive() {
        return survive;
    }

    public void setSurvive(boolean survive) {
        this.survive = survive;
    }

    public void setMAX_ANIMATE(int MAX_ANIMATE) {
        this.MAX_ANIMATE = MAX_ANIMATE;
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public boolean isCan_remove() {
        return can_remove;
    }

    public void setCan_remove(boolean can_remove) {
        this.can_remove = can_remove;
    }
}
