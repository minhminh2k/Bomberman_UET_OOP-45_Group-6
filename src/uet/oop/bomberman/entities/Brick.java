package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Brick extends Entity{
    public static final int one_frame_brick = 15;
    public int frame_brick = -1;
    private List<Entity> stillObjects = new ArrayList<>();

    public List<Entity> getStillObjects() {
        return stillObjects;
    }
    public void setStillObjects(List<Entity> s) {
        stillObjects = s;
    }
    public Brick(){

    }
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public void frameBom() {
        if(frame_brick > -1) {
            if(frame_brick == 3 * one_frame_brick) {
                setImg(Sprite.brick_exploded.getFxImage());

            }
            else if(frame_brick == 2 * one_frame_brick) {
                setImg(Sprite.brick_exploded1.getFxImage());
            }
            else if(frame_brick == one_frame_brick) {
                setImg(Sprite.brick_exploded2.getFxImage());
            }
            if(frame_brick == 0) {
                stillObjects.remove(this);
            }
            frame_brick--;
        }
    }
    @Override
    public void update() {

    }
    public void setFrameBrick(int x){
        frame_brick = x;
    }
// xin loi nay de ten nham nen phai up lai

}