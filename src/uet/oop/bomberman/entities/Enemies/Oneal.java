package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.charset.MalformedInputException;

public class Oneal extends Enemy {

    private int moveHori;
    private int moveVerti;
    private Entity check_Bomber;

    public Oneal(int xUnit, int yUnit, Image img, Entity entity) {
        super(xUnit, yUnit, img);
        this.setSpeed(2);
        setIntMap();
        canMove();
        this.currentDirection = Move.LEFT;
        check_Bomber = entity;
    }

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setSpeed(2);
        setIntMap();
        canMove();
        this.currentDirection = Move.LEFT;
    }

    @Override
    public void update() {
        animate();
        canMove();
        moveEnemy();
        this.chooseSprite();
        this.setImg(sprite.getFxImage());
        this.update_Speed();
    }

    @Override
    public void change_direction() {

    }

    public void update_Speed() {
        if(move_distance >= 32) {
            this.setMove_distance(0);
            int new_speed = rand.nextInt(2);
            this.setSpeed(new_speed + 1);
        }
    }
    public void chooseSprite() {
        if (currentDirection == Move.LEFT) {
            sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 30);
        }
        if (currentDirection == Move.RIGHT) {
            sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 30);
        }
        if (currentDirection == Move.UP) {
            sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 30);
        }
        if (currentDirection == Move.DOWN) {
            sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 30);
        }
    }
    public void moveEnemy() {
        int j_oneal = (int) ((x + 12) / 32);
        int i_oneal = (int) ((y + 16) / 32);
        int j_bomber = (int) (check_Bomber.getX() + 16) / 32;
        int i_bomber = (int) (check_Bomber.getY() + 16) / 32;
        if (j_bomber < j_oneal) {
            this.currentDirection = Move.LEFT;
        } else {
            this.currentDirection = Move.RIGHT;
        }
        if ((j_oneal * 32 == x && i_oneal * 32 == y)) {
            moveHori = 0;
            moveVerti = 0;
            canMove();
            if (j_bomber == j_oneal) {
                moveHori = 0;
            } else if (j_bomber < j_oneal && canMoveL) {
                moveHori = -speed;
            } else if (j_bomber > j_oneal && canMoveR) {
                moveHori = speed;
            }
            if (i_bomber == i_oneal) {
                moveVerti = 0;
            } else if (i_bomber < i_oneal && canMoveU) {
                moveVerti = -speed;
            } else if (i_bomber > i_oneal && canMoveD) {
                moveVerti = speed;
            }
            if (moveVerti != 0 && moveHori != 0) {
                if (Math.abs(i_bomber - i_oneal) > Math.abs(j_bomber - j_oneal)) {
                    moveHori = 0;
                } else {
                    moveVerti = 0;
                }
            }
        }
        x += moveHori;
        y += moveVerti;
        if(moveHori !=0 || moveVerti != 0) {
            move_distance += speed;
        }
    }
}
