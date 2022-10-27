package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Item.BomItem;
import uet.oop.bomberman.graphics.Sprite;

import java.util.HashSet;
import java.util.Set;

public class Bomber extends Entity {
    public Set<String> move = new HashSet<String>();
    public boolean outBomb = false;
    public int frame_dead = 0;
    public boolean justDead = false;
    public String lastStatus = "right";
    public boolean moving = false;

    protected int speed = 2;
    protected int v_x, v_y;
    protected int SIZE = 32;
    protected int chenhlech = 4;
    protected int frame_r, frame_l, frame_u, frame_d;
    public static final int one_frame = 4;

    public void setPos(int a, int b) {
        x = a;
        y = b;
    }

    public void setPos(int a, int b, boolean z) {
        x = a;
        y = b;
        justDead = true;
        frame_dead = 0;
    }

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

    @Override
    public void update() {
        frameMove();
        update_moving();
        checkStatusInvisiple();
    }

    public void updateLastStatus(String s) {
        lastStatus = s;
    }

    public void updateLastStatus() {
        for (String i : move) {
            lastStatus = i;
        }
    }

    public String getLastStatus() {
        return lastStatus;
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
                if ((!canMove(y1, x2) && lech1 < SIZE - chenhlech) || (!canMove(y2, x2) && lech2 < SIZE - chenhlech) || (!canMove(y1, x2) && !canMove(y2, x2))) {
                    x = (x2 - 1) * SIZE;
                    v_x = 0;
                }
            } else if (v_x < 0) {
                lech1 = Math.abs(getY() - y1 * SIZE);
                lech2 = Math.abs(getY() - y2 * SIZE);
                if ((!canMove(y1, x1) && lech1 < SIZE - chenhlech) || (!canMove(y2, x1) && lech2 < SIZE - chenhlech) || (!canMove(y2, x1) && !canMove(y1, x1))) {
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
                if ((!canMove(y2, x1) && lech1 < SIZE - chenhlech) || (!canMove(y2, x2) && lech2 < SIZE - chenhlech) || (!canMove(y2, x1) && !canMove(y2, x2))) {
                    y = (y2 - 1) * SIZE;
                    v_y = 0;
                }
            } else if (v_y < 0) {
                if ((!canMove(y1, x1) && lech1 < SIZE - chenhlech) || (!canMove(y1, x2) && lech2 < SIZE - chenhlech) || (!canMove(y1, x1) && !canMove(y1, x2))) {
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
        if (moving && !justDead) {
            switch (lastStatus) {
                case ("right"): {
                    frame_r++;
                    if (frame_r == 6 * one_frame) frame_r = 0;
                    int du = frame_r % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.player_right.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.player_right_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.player_right_2.getFxImage());
                    }
                    break;
                }
                case ("left"): {
                    frame_l += 1;
                    if (frame_l == 6 * one_frame) frame_l = 0;
                    int du = frame_l % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.player_left.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.player_left_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.player_left_2.getFxImage());
                    }
                    break;
                }
                case ("up"): {
                    frame_u += 1;
                    if (frame_u == 6 * one_frame) frame_u = 0;
                    int du = frame_u % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.player_up.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.player_up_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.player_up_2.getFxImage());
                    }
                    break;
                }
                case ("down"): {
                    frame_d += 1;
                    if (frame_d == 6 * one_frame) frame_d = 0;
                    int du = frame_d % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.player_down.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.player_down_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.player_down_2.getFxImage());
                    }
                    break;
                }
            }
        } else if (moving && justDead) {
            switch (lastStatus) {
                case ("right"): {
                    frame_r++;
                    if (frame_r == 6 * one_frame) frame_r = 0;
                    int du = frame_r % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_right.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_right_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_right_2.getFxImage());
                    }
                    break;
                }
                case ("left"): {
                    frame_l += 1;
                    if (frame_l == 6 * one_frame) frame_l = 0;
                    int du = frame_l % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_left.getFxImage());
                    } else if (2 * one_frame == du) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_left_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_left_2.getFxImage());
                    }
                    break;
                }
                case ("up"): {
                    frame_u += 1;
                    if (frame_u == 6 * one_frame) frame_u = 0;
                    int du = frame_u % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_up.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_up_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_up_2.getFxImage());
                    }
                    break;
                }
                case ("down"): {
                    frame_d += 1;
                    if (frame_d == 6 * one_frame) frame_d = 0;
                    int du = frame_d % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_down.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_down_1.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_down_2.getFxImage());
                    }
                    break;
                }
            }
        }
        if (!moving && justDead) {
            switch (lastStatus) {
                case ("right"): {
                    frame_r++;
                    if (frame_r == 6 * one_frame) frame_r = 0;
                    int du = frame_r % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_right.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_right.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_right.getFxImage());
                    }
                    break;
                }
                case ("left"): {
                    frame_l += 1;
                    if (frame_l == 6 * one_frame) frame_l = 0;
                    int du = frame_l % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_left.getFxImage());
                    } else if (2 * one_frame == du) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_left.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_left.getFxImage());
                    }
                    break;
                }
                case ("up"): {
                    frame_u += 1;
                    if (frame_u == 6 * one_frame) frame_u = 0;
                    int du = frame_u % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_up.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_up.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_up.getFxImage());
                    }
                    break;
                }
                case ("down"): {
                    frame_d += 1;
                    if (frame_d == 6 * one_frame) frame_d = 0;
                    int du = frame_d % (6 * one_frame);
                    if (du == 0) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == one_frame) {
                        setImg(Sprite.player_down.getFxImage());
                    } else if (du == 2 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 3 * one_frame) {
                        setImg(Sprite.player_down.getFxImage());
                    } else if (du == 4 * one_frame) {
                        setImg(Sprite.Empty.getFxImage());
                    } else if (du == 5 * one_frame) {
                        setImg(Sprite.player_down.getFxImage());
                    }
                    break;
                }
            }
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    boolean canMove(int i, int j) {
        if (map[i][j] == ' ' || ( map[i][j] == 'p' && !outBomb) || map[i][j] == '1' || map[i][j] == '2' || map[i][j] == '3'
                || map[i][j] == '4' || map[i][j] == '5' || map[i][j] == '6') {
            return true;
        }
        return false;
    }

    public boolean getJustDead() {
        return justDead;
    }

    public void checkStatusInvisiple() {
        if (justDead) {
            frame_dead++;
        }
        if (frame_dead == BomItem.one_frame_bom * 6) {
            justDead = false;
            frame_dead = 0;
        }
    }

    public void checkOutBomb(BomItem a) {
        int leftA, rightA, topA, bottomA;
        int rowBom, colBom;
        leftA = this.getX()  / 32;
        rightA = ( this.getX() + 24 ) / 32;
        topA = this.getY() / 32;
        bottomA = (this.getY()+28) / 32;
        rowBom = a.getY()/32;
        colBom = a.getX()/32;
        if( !(rowBom == topA || rowBom == bottomA) || !(colBom == leftA || colBom == rightA) ) {
            outBomb = true;
        }
    }
    public void setOutBomb(boolean a) {
        outBomb = a;
    }


}