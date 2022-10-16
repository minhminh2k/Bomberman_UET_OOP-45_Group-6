package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Ghost extends AIEnemy {
    private boolean status;

    public Ghost(int x, int y, Image img, Entity check_Bomber) {
        super(x, y, img, check_Bomber);
        this.setSpeed(1);
        this.can_remove = false;
        this.currentDirection = Move.LEFT;
        checkStatus();
    }

    public void checkStatus() {
        if (this.isSurvive() && !status) {
            int j_ghost = ((x + 12) / 32);
            int i_ghost = ((y + 16) / 32);
            int j_bomber = (check_Bomber.getX() + 16) / 32;
            int i_bomber = (check_Bomber.getY() + 16) / 32;
            if (Math.abs(j_ghost - j_bomber) <= 5 && Math.abs(i_ghost - i_bomber) <= 5
                    && j_ghost * 32 == x && i_ghost * 32 == y) {
                status = true;
                this.setSpeed(2);
            }
        }
    }

    @Override
    public void update() {
        if (this.isSurvive()) {
            animate();
            checkStatus();
            moveEnemy_false();
            moveEnemy();
            this.chooseSprite();
//            if (long_distance >= 800) {
//                this.setDead();
//            }
        }
        if (!this.isSurvive()) {
            Dead_Animation();
        }
    }


    public void change_direction() {
        if (this.currentDirection == Move.LEFT) {
            x = x - speed;
        }
        if (this.currentDirection == Move.RIGHT) {
            x = x + speed;
        }
        if (this.currentDirection == Move.UP) {
            y = y - speed;
        }
        if (this.currentDirection == Move.DOWN) {
            y = y - speed;
        }
        long_distance += speed;
    }

    public void moveEnemy_false() {
        if (!status) {
            this.change_direction();
            canMove();
            if (!canMoveL) {
                this.currentDirection = Move.RIGHT;
            }
            if (!canMoveR) {
                this.currentDirection = Move.LEFT;
            }
        }
    }

    public void moveEnemy() {
        if (this.isSurvive() && status) {
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
                if (j_bomber == j) {
                    moveHori = 0;
                } else if (j_bomber < j) {
                    moveHori = -speed;
                } else {
                    moveHori = speed;
                }
                if (i_bomber == i) {
                    moveVerti = 0;
                } else if (i_bomber < i) {
                    moveVerti = -speed;
                } else {
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

            if (moveHori != 0 || moveVerti != 0) {
                move_distance += speed;
                long_distance += speed;
            }
        }
    }

    @Override
    public void chooseSprite() {
        if (this.isSurvive()) {
            if (!status) {
                if (currentDirection == Move.LEFT) {
                    sprite = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, animate, MAX_ANIMATE);
                }
                if (currentDirection == Move.RIGHT) {
                    sprite = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, animate, MAX_ANIMATE);
                }
                if (currentDirection == Move.UP) {
                    sprite = Sprite.movingSprite(Sprite.ghost_left1, Sprite.ghost_left2, animate, MAX_ANIMATE);
                }
                if (currentDirection == Move.DOWN) {
                    sprite = Sprite.movingSprite(Sprite.ghost_right1, Sprite.ghost_right2, animate, MAX_ANIMATE);
                }
            } else {
                if (currentDirection == Move.LEFT) {
                    sprite = Sprite.movingSprite(Sprite.ghost_left3, Sprite.ghost_left3, animate, MAX_ANIMATE);
                }
                if (currentDirection == Move.RIGHT) {
                    sprite = Sprite.movingSprite(Sprite.ghost_right3, Sprite.ghost_right3, animate, MAX_ANIMATE);
                }
                if (currentDirection == Move.UP) {
                    sprite = Sprite.movingSprite(Sprite.ghost_left3, Sprite.ghost_left3, animate, MAX_ANIMATE);
                }
                if (currentDirection == Move.DOWN) {
                    sprite = Sprite.movingSprite(Sprite.ghost_right3, Sprite.ghost_right3, animate, MAX_ANIMATE);
                }
            }
        } else {
            if (!this.isSurvive()) {
                this.setMAX_ANIMATE(72);
                sprite = Sprite.movingSprite(Sprite.ghost_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, MAX_ANIMATE);
            }
        }
        this.setImg(sprite.getFxImage());
    }
}
