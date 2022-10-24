package uet.oop.bomberman.entities;

import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Enemies.Enemy;
import uet.oop.bomberman.entities.BomItem;

public class CheckCollision {
    public boolean checkCollisionWithEnemy(Bomber bomberman, Enemy enemy) {
        int leftA, leftB;
        int rightA, rightB;
        int topA, topB;
        int bottomA, bottomB;
        leftA = bomberman.getX()+1;
        rightA = leftA + 24;
        topA = bomberman.getY() + 2;
        bottomA = topA + 30;
        leftB = enemy.getX()+1;
        rightB = leftB + 30;
        topB = enemy.getY() + 1;
        bottomB = topB + 30;
        if ((bottomB >= topA && rightB >= leftA && rightB - 32 <= leftA && bottomB - 32 <= topA)
                || (bottomB >= topA && rightA >= leftB && rightA - 24 <= leftB && bottomB - 32 <= topA)
                || (bottomA >= topB && rightB >= leftA && rightB - 32 <= leftA && bottomA - 32 <= topB)
                || (bottomA >= topB && rightA >= leftB && rightA - 24 <= leftB && bottomA - 32 <= topB)) {
            Sound.playSound(Sound.playerDead);
            return true;
        }
        return false;
    }

    public boolean checkCollisionWithBomb(BomItem bomb, Enemy enemy, int right, int left, int up, int down) {
        if(bomb.getFrame_bom() <= bomb.getOne_frame_bom()*3 ) {
            int leftA, leftB;
            int rightA, rightB;
            int topA, topB;
            int bottomA, bottomB;
            leftA = enemy.getX();
            rightA = leftA + 31;
            leftA /= 32;
            rightA /= 32;
            topA = enemy.getY();
            bottomA = topA + 31;
            topA /= 32;
            bottomA /= 32;
            leftB = bomb.getX()+1;
            topB = bomb.getY()+1;
            leftB /= 32;
            topB /= 32;
            if(leftA == leftB && topA >= topB - up && topA <= topB + down) {
                enemy.setDead();
                return true;
            }
            if(leftA == leftB && bottomA >= topB - up && bottomA <= topB + down) {
                enemy.setDead();
                return true;
            }
            if(rightA == leftB && topA >= topB - up && topA <= topB + down ) {
                enemy.setDead();
                return true;
            }
            if(rightA == leftB && bottomA >= topB - up && bottomA <= topB + down ) {
                enemy.setDead();
                return true;
            }
            if(topA == topB && leftA >= leftB - left && leftA <= leftB + right) {
                enemy.setDead();
                return true;
            }
            if(bottomA == topB && leftA >= leftB - left && leftA <= leftB + right) {
                enemy.setDead();
                return true;
            }
            if(topA == topB && rightA >= leftB - left && rightA <= leftB + right) {
                enemy.setDead();
                return true;
            }
            if(bottomA == topB && rightA >= leftB - left && rightA <= leftB + right) {
                enemy.setDead();
                return true;
            }
        }
        return false;
    }
    public boolean checkCollisionWithBomb(BomItem bomb, Bomber bomberman) {
        if(bomb.getFrame_bom() <= bomb.getOne_frame_bom()*3 ) {
            int leftA, leftB;
            int rightA, rightB;
            int topA, topB;
            int bottomA, bottomB;
            leftA = bomberman.getX();
            rightA = leftA + 24;
            leftA /= 32;
            rightA /= 32;
            topA = bomberman.getY();
            bottomA = topA + 31;
            topA /= 32;
            bottomA /= 32;
            leftB = bomb.getX()+1;
            topB = bomb.getY()+1;
            leftB /= 32;
            topB /= 32;
            if(leftA == leftB && topA >= topB - bomb.getDistanceUp() && topA <= topB + bomb.getDistanceDown()) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(leftA == leftB && bottomA >= topB - bomb.getDistanceUp() && bottomA <= topB + bomb.getDistanceDown()) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(rightA == leftB && topA >= topB - bomb.getDistanceUp() && topA <= topB + bomb.getDistanceDown() ) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(rightA == leftB && bottomA >= topB - bomb.getDistanceUp() && bottomA <= topB + bomb.getDistanceDown() ) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(topA == topB && leftA >= leftB - bomb.getDistanceLeft() && leftA <= leftB + bomb.getDistanceRight()) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(bottomA == topB && leftA >= leftB - bomb.getDistanceLeft() && leftA <= leftB + bomb.getDistanceRight()) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(topA == topB && rightA >= leftB - bomb.getDistanceLeft() && rightA <= leftB + bomb.getDistanceRight()) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
            if(bottomA == topB && rightA >= leftB - bomb.getDistanceLeft() && rightA <= leftB + bomb.getDistanceRight()) {
                Sound.playSound(Sound.playerDead);
                return true;
            }
        }

        return false;
    }
}
