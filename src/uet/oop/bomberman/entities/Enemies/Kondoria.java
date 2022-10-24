package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.charset.MalformedInputException;

public class Kondoria extends AIEnemy {

    public Kondoria(int xUnit, int yUnit, Image img, Entity check_Bomber) {
        super(xUnit, yUnit, img, check_Bomber);
        this.currentDirection = Move.LEFT;
        this.can_remove = false;
        blockBomb();
    }
    @Override
    public void update() {
        if (this.isSurvive()) {
            animate();
            moveEnemy();
            this.chooseSprite();
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
                sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, animate, MAX_ANIMATE);
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
            int j = ((x + 12) / 32);
            int i = ((y + 16) / 32);
            int j_bomber = (check_Bomber.getX() + 16) / 32;
            int i_bomber = (check_Bomber.getY() + 16) / 32;
            if (j_bomber < j) {
                this.currentDirection = Move.LEFT;
            } else {
                this.currentDirection = Move.RIGHT;
            }

            if ((j * 32 == x && i * 32 == y)) {
                moveHori = 0;
                moveVerti = 0;
                blockBomb();
                if (j_bomber == j) {
                    moveHori = 0;
                } else if (j_bomber < j && canMoveL) {
                    moveHori = -speed;
                } else if (j_bomber > j && canMoveR){
                    moveHori = speed;
                }
                if (i_bomber == i) {
                    moveVerti = 0;
                } else if (i_bomber < i && canMoveU) {
                    moveVerti = -speed;
                } else if (i_bomber > i && canMoveD) {
                    moveVerti = speed;
                }
                if (moveVerti != 0 && moveHori != 0) {
                    if (Math.abs(i_bomber - i) > Math.abs(j_bomber - j)) {
                        moveHori = 0;
                    } else {
                        moveVerti = 0;
                    }
                }
            }
            x += moveHori;
            y += moveVerti;
        }
    }
}
