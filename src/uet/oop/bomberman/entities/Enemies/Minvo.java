package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends AIEnemy {

    public Minvo(int x, int y, Image img, Entity check_Bomber) {
        super(x, y, img, check_Bomber);
        this.setSpeed(2);
        this.can_remove = false;
    }

    @Override
    public void update() {
        if (this.isSurvive()) {
            animate();
            canMove();
            moveEnemy();
            this.chooseSprite();
        }
        if (!this.isSurvive()) {
            Dead_Animation();
        }
    }

    public void chooseSprite() {
        if (this.isSurvive()) {
            if (currentDirection == Move.LEFT) {
                sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.RIGHT) {
                sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.UP) {
                sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.DOWN) {
                sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, MAX_ANIMATE);
            }
        } else {
            if (!this.isSurvive()) {
                this.setMAX_ANIMATE(72);
                sprite = Sprite.movingSprite(Sprite.minvo_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, MAX_ANIMATE);
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
                canMove();
                if (j_bomber == j) {
                    moveHori = 0;
                } else if (j_bomber < j && canMoveL) {
                    moveHori = -speed;
                } else if (j_bomber > j && canMoveR) {
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
