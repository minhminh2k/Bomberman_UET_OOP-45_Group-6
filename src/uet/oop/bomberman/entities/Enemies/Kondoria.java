package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.charset.MalformedInputException;

public class Kondoria extends Enemy {
    private int long_distance;
    private int moveHori;
    private int moveVerti;
    private Entity check_Bomber;

    public Kondoria(int xUnit, int yUnit, Image img, Entity entity) {
        super(xUnit, yUnit, img);
        this.currentDirection = Move.LEFT;
        check_Bomber = entity;
        this.can_remove = false;
    }

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setSpeed(2);
        //setIntMap();
        this.currentDirection = Move.LEFT;
    }

    @Override
    public void update() {
        if (this.isSurvive()) {
            animate();
            //canMove();
            moveEnemy();
            this.chooseSprite();
//            if(long_distance >= 320) {
//                this.setSurvive(false);
//                this.setAnimate(0);
//            }
        }
        if (!this.isSurvive()) {
            animate();
            this.chooseSprite();
            if (this.animate == MAX_ANIMATE) {
                can_remove = true;
            }
        }

    }

    public void chooseSprite() {
        if (this.isSurvive()) {
            if (currentDirection == Move.LEFT) {
                sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.RIGHT) {
                sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.UP) {
                sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.DOWN) {
                sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, animate, MAX_ANIMATE);
            }
        } else {
            if (!this.isSurvive()) {
                this.setMAX_ANIMATE(72);
                sprite = Sprite.movingSprite(Sprite.kondoria_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, MAX_ANIMATE);
            }
        }
        this.setImg(sprite.getFxImage());
    }

    public void moveEnemy() {
        if (this.isSurvive()) {
            int j_kondoria = ((x + 12) / 32);
            int i_kondoria = ((y + 16) / 32);
            int j_bomber = (check_Bomber.getX() + 16) / 32;
            int i_bomber = (check_Bomber.getY() + 16) / 32;
            if (j_bomber < j_kondoria) {
                this.currentDirection = Move.LEFT;
            } else {
                this.currentDirection = Move.RIGHT;
            }
            if ((j_kondoria * 32 == x && i_kondoria * 32 == y)) {
                moveHori = 0;
                moveVerti = 0;
                if (j_bomber == j_kondoria) {
                    moveHori = 0;
                } else if (j_bomber < j_kondoria) {
                    moveHori = -speed;
                } else {
                    moveHori = speed;
                }
                if (i_bomber == i_kondoria) {
                    moveVerti = 0;
                } else if (i_bomber < i_kondoria) {
                    moveVerti = -speed;
                } else {
                    moveVerti = speed;
                }
                if (moveVerti != 0 && moveHori != 0) {
                    if (Math.abs(i_bomber - i_kondoria) > Math.abs(j_bomber - j_kondoria)) {
                        moveHori = 0;
                    } else {
                        moveVerti = 0;
                    }
                }
            }
            x += moveHori;
            y += moveVerti;
            if (moveHori != 0 || moveVerti != 0) {
                move_distance += speed;
                long_distance += speed;
            }
        }
    }
}
