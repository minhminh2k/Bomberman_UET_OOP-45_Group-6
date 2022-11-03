package uet.oop.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class AIEnemy extends Enemy {
    protected int long_distance;
    protected int moveHori;
    protected int moveVerti;
    protected Entity check_Bomber;
    public AIEnemy(int x, int y, Image img, Entity check_Bomber) {
        super(x, y, img);
        this.check_Bomber = check_Bomber;
    }

    public void blockBomb() {
        if (this.getX() % 32 == 0 && this.getY() % 32 == 0) {
            int x_pos = this.getX() / 32;
            int y_pos = this.getY() / 32;
            canMoveL = map[y_pos][x_pos - 1] != 'p';
            canMoveR = map[y_pos][x_pos + 1] != 'p';
            canMoveU = map[y_pos - 1][x_pos] != 'p';
            canMoveD = map[y_pos + 1][x_pos] != 'p';
        }
    }

    public int getMoveHori() {
        return moveHori;
    }

    public void setMoveHori(int moveHori) {
        this.moveHori = moveHori;
    }

    public int getMoveVerti() {
        return moveVerti;
    }

    public void setMoveVerti(int moveVerti) {
        this.moveVerti = moveVerti;
    }

    public Entity getCheck_Bomber() {
        return check_Bomber;
    }

    public void setCheck_Bomber(Entity check_Bomber) {
        this.check_Bomber = check_Bomber;
    }
}
