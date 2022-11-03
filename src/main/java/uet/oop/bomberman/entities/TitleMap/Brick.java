package uet.oop.bomberman.entities.TitleMap;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Item.BomItem;


public class Brick extends Entity {
    public boolean isExploded = false;
    public String power = "";
    public static final int one_frame_brick = BomItem.one_frame_bom;
    public int frame_brick = -1;
    public Image flameItem;
/*    public FlameItem item;*/
    public boolean hasSetItem = false;
    public Brick(){

    }
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
        frame_Brick();
    }
    public void brickStartexploded(){
        frame_brick = 3 * one_frame_brick;

    }
    public void frame_Brick() {
        if(frame_brick > -1 ) {
            if(frame_brick == 3*one_frame_brick) {
                setImg(Sprite.brick_exploded.getFxImage());
            }
            else if(frame_brick == 2 * one_frame_brick) {
                setImg(Sprite.brick_exploded1.getFxImage());
            }
            else if(frame_brick == one_frame_brick) {
                setImg(Sprite.brick_exploded2.getFxImage());
            }
            else if(frame_brick == 1) {
                isExploded = true;
            }
            frame_brick --;
        }
    }
    public int getFrame_brick() {
        return frame_brick;
    }
    public void setIsExploded(boolean a) {
        isExploded = a;
    }
    public void setFlameItem(Image a) {
        flameItem = a;
        hasSetItem = true;
    }
    public Image getFlameItem() {
        return flameItem;
    }
    public boolean getSetItem() {
        return hasSetItem;
    }
    public void setPower(String s) {
        this.power = s;
    }
    public String getPower() {
        return power;
    }

}
