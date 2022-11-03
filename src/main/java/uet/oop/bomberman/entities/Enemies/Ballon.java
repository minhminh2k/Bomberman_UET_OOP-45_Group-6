package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.graphics.Sprite;

public class Ballon extends Enemy {
    private int long_distance;

    public Ballon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.setSpeed(1);
        long_distance = 0;
    }

    @Override
    public void update() {
        if (this.isSurvive()) {
            if (this.getMove_distance() == 0) {
                Di = rand.nextInt(2);
            }
            animate();
            canMove();
            this.chooseSprite();
            this.moveEnemy();
            this.change_direction();
            this.update_Direction(this.move_distance, Di);
        }
        if (!this.isSurvive()) {
            Dead_Animation();
        }

    }

    public void change_direction() {
        if (this.isSurvive()) {
            if (currentDirection == Move.LEFT && !canMoveL) {
                this.randomDirection();
                this.setMove_distance(0);
            }
            if (currentDirection == Move.RIGHT && !canMoveR) {
                this.randomDirection();
                this.setMove_distance(0);
            }
            if (currentDirection == Move.UP && !canMoveU) {
                this.randomDirection();
                this.setMove_distance(0);
            }
            if (currentDirection == Move.DOWN && !canMoveD) {
                this.randomDirection();
                this.setMove_distance(0);
            }
        }
    }

    public void moveEnemy() {
        if (this.isSurvive()) {
            if (currentDirection == Move.LEFT && canMoveL) {
                x = x - speed;
                move_distance += speed;
                long_distance += speed;
            }
            if (currentDirection == Move.RIGHT && canMoveR) {
                x = x + speed;
                move_distance += speed;
                long_distance += speed;
            }
            if (currentDirection == Move.UP && canMoveU) {
                y = y - speed;
                move_distance += speed;
                long_distance += speed;
            }
            if (currentDirection == Move.DOWN && canMoveD) {
                y = y + speed;
                move_distance += speed;
                long_distance += speed;
            }
        }
    }

    public void update_Direction(int move_distance, int Di) {
        if (move_distance == (Di + 2) * 32 && this.isSurvive()) {
            this.setMove_distance(0);
            this.randomDirection();
        }
    }

    public void chooseSprite() {
        if (this.isSurvive()) {
            if (currentDirection == Move.LEFT) {
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.RIGHT) {
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.UP) {
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, MAX_ANIMATE);
            }
            if (currentDirection == Move.DOWN) {
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, MAX_ANIMATE);
            }
        } else {
            if (!this.isSurvive()) {
                this.setMAX_ANIMATE(72);
                sprite = Sprite.movingSprite(Sprite.balloom_dead, Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animate, MAX_ANIMATE);
            }
        }
        this.setImg(sprite.getFxImage());
    }
}