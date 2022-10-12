package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Bomber extends Entity {
    Set<String> move = new HashSet<String>();
    public String lastStatus = "right";

    public boolean moving = false;

    protected int speed = 2;
    protected int v_x, v_y;
    protected int SIZE = 32;
    protected int chenhlech = 6;
    protected int frame_r, frame_l, frame_u, frame_d;
    public static final int one_frame = 7;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        v_x = v_y = 0;
        frame_r = frame_d = frame_u = frame_l = 0;
    }

    public void setMoving(boolean m) {
        this.moving = m;
    }

    public boolean isMoving() {
        return moving;
    }

    public void addMove(String s) {
        move.add(s);
    }

    public void deleteMove(String s) {
        move.remove(s);
    }

    public int sizeMoving() {
        return move.size();
    }

    public void printStatus() {
        System.out.println("lan 1");
        for (String a : move) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    @Override
    public void update() {
        frameMove();
        update_moving();
    }

    public void updateLastStatus(String s) {
        lastStatus = s;
    }

    public void checkCollisionS() {
        int x1, x2, y1, y2, lech2, lech1;
        x1 = (getX() + v_x) / SIZE;
        x2 = (getX() + v_x + SIZE - 1) / SIZE;
        y1 = (getY()) / SIZE;
        y2 = (getY() + SIZE - 1) / SIZE;
        if (x1 >= 0 && x2 < colMap && y1 >= 0 && y2 < rowMap) {
            if (v_x > 0) {
                lech1 = Math.abs(getY() - y1 * SIZE);
                lech2 = Math.abs(getY() - y2 * SIZE);
                if ((map[y1][x2] != ' ' && lech1 < SIZE - chenhlech) || (map[y2][x2] != ' ' && lech2 < SIZE - chenhlech) || (map[y1][x2] != ' ' && map[y2][x2] != ' ')) {
                    x = (x2 - 1) * SIZE;
                    v_x = 0;
                }
            } else if (v_x < 0) {
                lech1 = Math.abs(getY() - y1 * SIZE);
                lech2 = Math.abs(getY() - y2 * SIZE);
                if ((map[y1][x1] != ' ' && lech1 < SIZE - chenhlech) || (map[y2][x1] != ' ' && lech2 < SIZE - chenhlech) || (map[y2][x1] != ' ' && map[y1][x1] != ' ')) {
                    x = (x1 + 1) * SIZE;
                    v_x = 0;
                }
            }
        }
        x1 = getX() / SIZE;
        x2 = (getX() + SIZE - 1) / SIZE;
        y1 = (getY() + v_y) / SIZE;
        y2 = (getY() + v_y + SIZE - 1) / SIZE;
        lech1 = Math.abs(getX() - x1 * SIZE);
        lech2 = Math.abs(getX() - x2 * SIZE);
        if (x1 >= 0 && x2 < colMap && y1 >= 0 && y2 < rowMap) {
            if (v_y > 0) {
                if ((map[y2][x1] != ' ' && lech1 < SIZE - chenhlech) || (map[y2][x2] != ' ' && lech2 < SIZE - chenhlech) || (map[y2][x1] != ' ' && map[y2][x2] != ' ')) {
                    y = (y2 - 1) * SIZE;
                    v_y = 0;
                }
            } else if (v_y < 0) {
                if ((map[y1][x1] != ' ' && lech1 < SIZE - chenhlech) || (map[y1][x2] != ' ' && lech2 < SIZE - chenhlech) || (map[y2][x1] != ' ' && map[y2][x2] != ' ')) {
                    y = (y1 + 1) * SIZE;
                    v_y = 0;
                }
            }
        }
        y += v_y;
        x += v_x;
    }

    public boolean checkStatus(String s) {
        if (move.contains(s)) {
            return true;
        }
        return false;
    }

    public void update_moving() {
        if (move.contains("right")) {
            v_x = speed;

        }
        if (move.contains("left")) {
            v_x = -1 * speed;

        }

        if (move.contains("up")) {
            v_y = -1 * speed;

        }
        if (move.contains("down")) {
            v_y = speed;
        }
        if (move.size() == 0) {
            v_x = 0;
            v_y = 0;
        }
        checkCollisionS();
    }

    public void frameMove() {
        if (moving) {
            switch (lastStatus) {
                case ("right"): {
                    frame_r++;
                    if (frame_r >= 3*one_frame) frame_r = 0;
                    int du = frame_r % (3*one_frame);
                    if (du < one_frame) {
                        setImg(Sprite.player_right.getFxImage());
                    } else if (du < 2*one_frame && du >= one_frame) {
                        setImg(Sprite.player_right_1.getFxImage());
                    } else {
                        setImg(Sprite.player_right_2.getFxImage());
                    }
                    break;
                }
                case ("left"): {
                    frame_l += 1;
                    if (frame_l >= 3*one_frame) frame_l = 0;
                    int du = frame_l % (3*one_frame);
                    if (du < one_frame) {
                        setImg(Sprite.player_left.getFxImage());
                    } else if (du < 2*one_frame && du >= one_frame ) {
                        setImg(Sprite.player_left_1.getFxImage());
                    } else {
                        setImg(Sprite.player_left_2.getFxImage());
                    }
                    break;
                }
                case ("up"): {
                    frame_u += 1;
                    if (frame_u >= 3*one_frame) frame_u = 0;
                    int du = frame_u % (3*one_frame);
                    if (du <= one_frame) {
                        setImg(Sprite.player_up.getFxImage());
                    }
                    if (du < 2*one_frame && du >= one_frame) {
                        setImg(Sprite.player_up_1.getFxImage());
                    } else {
                        setImg(Sprite.player_up_2.getFxImage());
                    }
                    break;
                }
                case ("down"): {
                    frame_d += 1;
                    if (frame_d >= 3*one_frame) frame_d = 0;
                    int du = frame_d % (3*one_frame);
                    if (du < one_frame) {
                        setImg(Sprite.player_down.getFxImage());
                    } else if (du < 2*one_frame && du >= one_frame) {
                        setImg(Sprite.player_down_1.getFxImage());
                    } else {
                        setImg(Sprite.player_down_2.getFxImage());
                    }
                    break;
                }
            }
        }
    }
// xin loi nay de ten nham nen phai up lai

}