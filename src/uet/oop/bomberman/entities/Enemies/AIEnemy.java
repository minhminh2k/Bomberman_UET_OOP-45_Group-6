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
