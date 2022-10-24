package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Oneal extends AIEnemy {
    public Oneal(int xUnit, int yUnit, Image img, Entity entity) {
        super(xUnit, yUnit, img, entity);
        this.setSpeed(2);
        this.currentDirection = Move.LEFT;
        this.can_remove = false;
        canMove();
    }

    @Override
    public void update() {
        if (this.isSurvive()) {
            animate();
            canMove();
            moveEnemy();
            this.chooseSprite();
            this.update_Speed();
        }
        if(!this.isSurvive()) {
            Dead_Animation();
        }
    }

    public void update_Speed() {
        if(this.isSurvive()) {
            if (move_distance >= 64) {
                this.setMove_distance(0);
                int new_speed = rand.nextInt(2);
                this.setSpeed(new_speed + 1);
            }
        }
    }
    public void chooseSprite() {
        if(this.isSurvive()) {
            if (currentDirection == Move.LEFT) {
                sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.RIGHT) {
                sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.UP) {
                sprite = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.DOWN) {
                sprite = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, MAX_ANIMATE);
            }
        } else {
            if (!this.isSurvive()) {
                this.setMAX_ANIMATE(72);
                sprite = Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, MAX_ANIMATE);
            }
        }
        this.setImg(sprite.getFxImage());
    }
    public void moveEnemy() {
        if(this.isSurvive()) {
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
        }
    }
}
