package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.BombermanGame;

import java.util.ArrayList;
import java.util.List;

public class BomItem extends Entity {
    public boolean status = false;
    public static int HEIGHT = 14;
    public static int WIDTH = 31;
    public static int RowY;
    public static int ColX;
    public static final int one_frame_bom = 20;
    public int frame_bom = -1;

    public int getSizeBom() {
        return SizeBom;
    }

    public int SizeBom = 1;
    public boolean[] checkbomb = new boolean[9];

    public int distanceRight = 0;
    public int distanceLeft = 0;
    public int distanceUp = 0;
    public int distanceDown = 0;
    public int explodeRight = 0;
    public int explodeLeft = 0;
    public int explodeUp = 0;
    public int explodeDown = 0;
    private List<Entity> stillObjects = new ArrayList<>();

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public void setStillObjects(List<Entity> s) {
        stillObjects = s;
    }

    public Entity[] bomb = new Entity[9];

    public BomItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public BomItem() {
        for (int i = 0; i < 9; i++) {
            bomb[i] = new Entity() {
                @Override
                public void update() {

                }
            };
        }
    }

    @Override
    public void update() {
        frameBom();
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setBomExploded() {
        Sound.playSound(Sound.explosion);
        bomb[0].setImg(Sprite.bomb_exploded.getFxImage());
        int row = bomb[0].getY() / Sprite.SCALED_SIZE;
        int col = bomb[0].getX() / Sprite.SCALED_SIZE;
        checkbomb[0] = true;
        if (SizeBom == 1) {
            if (col + 1 <= WIDTH) {
                if (canPass(row, col + 1)) {
                    bomb[1].setEntity(col + 1, row, Sprite.explosion_horizontal_right_last.getFxImage());
                    checkbomb[1] = true;
                    stillObjects.add(bomb[1]);
                }
            }
            if (row + 1 <= HEIGHT) {
                if (canPass(row + 1, col)) {
                    bomb[7].setEntity(col, row + 1, Sprite.explosion_vertical_down_last.getFxImage());
                    checkbomb[7] = true;
                    stillObjects.add(bomb[7]);
                }
            }
            if (col - 1 >= 0) {
                if (canPass(row, col - 1)) {
                    bomb[3].setEntity(col - 1, row, Sprite.explosion_horizontal_left_last.getFxImage());
                    checkbomb[3] = true;
                    stillObjects.add(bomb[3]);
                }
            }
            if (row - 1 >= 0) {
                if (canPass(row - 1, col)) {
                    bomb[5].setEntity(col, row - 1, Sprite.explosion_vertical_top_last.getFxImage());
                    checkbomb[5] = true;
                    stillObjects.add(bomb[5]);
                }
            }
        } else if (SizeBom == 2) {
            if (col + 2 < WIDTH) {
                if (canPass(row, col + 2) && canPass(row, col + 1)) {
                    bomb[2].setEntity(col + 2, row, Sprite.explosion_horizontal_right_last.getFxImage());
                    checkbomb[2] = true;
                    bomb[1].setEntity(col + 1, row, Sprite.explosion_horizontal.getFxImage());
                    checkbomb[1] = true;
                    stillObjects.add(bomb[2]);
                    stillObjects.add(bomb[1]);
                } else if (canPass(row, col + 1) && !canPass(row, col + 2)) {
                    bomb[1].setEntity(col + 1, row, Sprite.explosion_horizontal_right_last.getFxImage());
                    checkbomb[1] = true;
                    stillObjects.add(bomb[1]);
                }
            } else if (col + 2 >= WIDTH) {
            }
            if (row + 2 < HEIGHT) {
                if (canPass(row + 2, col) && canPass(row + 1, col)) {
                    bomb[8].setEntity(col, row + 2, Sprite.explosion_vertical_down_last.getFxImage());
                    checkbomb[8] = true;
                    bomb[7].setEntity(col, row + 1, Sprite.explosion_vertical.getFxImage());
                    checkbomb[7] = true;
                    stillObjects.add(bomb[8]);
                    stillObjects.add(bomb[7]);
                } else if (canPass(row + 1, col) && !canPass(row + 2, col)) {
                    bomb[7].setEntity(col, row + 1, Sprite.explosion_vertical_down_last.getFxImage());
                    checkbomb[7] = true;
                    stillObjects.add(bomb[7]);
                }
            } else {

            }
            if (col - 2 > 0) {
                if (canPass(row, col - 2) && canPass(row, col - 1)) {
                    bomb[3].setEntity(col - 1, row, Sprite.explosion_horizontal.getFxImage());
                    checkbomb[3] = true;
                    bomb[4].setEntity(col - 2, row, Sprite.explosion_horizontal_left_last.getFxImage());
                    checkbomb[4] = true;
                    stillObjects.add(bomb[3]);
                    stillObjects.add(bomb[4]);
                } else if (canPass(row, col - 1) && !canPass(row, col - 2)) {
                    bomb[3].setEntity(col - 1, row, Sprite.explosion_horizontal_left_last.getFxImage());
                    checkbomb[3] = true;
                    stillObjects.add(bomb[3]);
                }
            } else if (col == 2) {
                if (canPass(row, col - 1)) {
                    bomb[3].setEntity(col - 1, row, Sprite.explosion_horizontal_left_last.getFxImage());
                    checkbomb[3] = true;
                    stillObjects.add(bomb[3]);
                }
            }
            if (row - 2 > 0) {
                if (canPass(row - 2, col) && canPass(row - 1, col)) {
                    bomb[6].setEntity(col, row - 2, Sprite.explosion_vertical_top_last.getFxImage());
                    checkbomb[6] = true;
                    bomb[5].setEntity(col, row - 1, Sprite.explosion_vertical.getFxImage());
                    checkbomb[5] = true;
                    stillObjects.add(bomb[5]);
                    stillObjects.add(bomb[6]);
                } else if (canPass(row - 1, col) && !canPass(row - 2, col)) {
                    bomb[5].setEntity(col, row - 1, Sprite.explosion_vertical_top_last.getFxImage());
                    checkbomb[5] = true;
                    stillObjects.add(bomb[5]);
                }
            } else if (row == 2) {
                if (canPass(row - 1, col)) {
                    bomb[5].setEntity(col, row - 1, Sprite.explosion_vertical_top_last.getFxImage());
                    checkbomb[5] = true;
                    stillObjects.add(bomb[5]);
                }
            }
        }
    }

    public void setBomExploded1() {
        bomb[0].setImg(Sprite.bomb_exploded1.getFxImage());
        int row = bomb[0].getY() / Sprite.SCALED_SIZE;
        int col = bomb[0].getX() / Sprite.SCALED_SIZE;
        if (SizeBom == 1) {
            if (col + 1 <= WIDTH) {
                if (canPass(row, col + 1)) {
                    bomb[1].setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
                }
            }
            if (row + 1 <= HEIGHT) {
                if (canPass(row + 1, col)) {
                    bomb[7].setImg(Sprite.explosion_vertical_down_last1.getFxImage());
                }
            }
            if (col - 1 >= 0) {
                if (canPass(row, col - 1)) {
                    bomb[3].setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
                }
            }
            if (row - 1 >= 0) {
                if (canPass(row - 1, col)) {
                    bomb[5].setImg(Sprite.explosion_vertical_top_last1.getFxImage());
                }
            }
        } else if (SizeBom == 2) {
            if (col + 2 < WIDTH) {
                if (canPass(row, col + 2) && canPass(row, col + 1)) {
                    bomb[2].setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
                    bomb[1].setImg(Sprite.explosion_horizontal1.getFxImage());
                } else if (!canPass(row, col + 2) && canPass(row, col + 1)) {
                    bomb[1].setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
                }
            } else {

            }
            if (row + 2 < HEIGHT) {
                if (canPass(row + 2, col) && canPass(row + 1, col)) {
                    bomb[8].setImg(Sprite.explosion_vertical_down_last1.getFxImage());
                    bomb[7].setImg(Sprite.explosion_vertical1.getFxImage());
                } else if (!canPass(row+2, col) && canPass(row+1, col)) {
                    bomb[7].setImg(Sprite.explosion_vertical_down_last1.getFxImage());
                }
            } else {

            }
            if (col - 2 > 0) {
                if (canPass(row, col - 2) && canPass(row, col - 1)) {
                    bomb[3].setImg(Sprite.explosion_horizontal1.getFxImage());
                    bomb[4].setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
                } else if (!canPass(row, col - 2) && canPass(row, col - 1)) {
                    bomb[3].setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
                }
            } else if (col == 2) {
                if (canPass(row, col - 1)) {
                    bomb[3].setEntity(col - 1, row, Sprite.explosion_horizontal_left_last1.getFxImage());
                }
            }
            if (row - 2 > 0) {
                if ((canPass(row - 2, col)) && canPass(row - 1, col)) {
                    bomb[6].setImg(Sprite.explosion_vertical_top_last1.getFxImage());
                    bomb[5].setImg(Sprite.explosion_vertical1.getFxImage());
                } else if (!canPass(row - 2, col) && canPass(row - 1, col)) {
                    bomb[5].setImg(Sprite.explosion_vertical_top_last1.getFxImage());
                }
            } else if (row == 2) {
                if (canPass(row - 1, col)) {
                    bomb[5].setEntity(col, row - 1, Sprite.explosion_vertical_top_last1.getFxImage());
                }
            }
        }
    }

    public void setBomExploded2() {
        bomb[0].setImg(Sprite.bomb_exploded2.getFxImage());
        int row = bomb[0].getY() / Sprite.SCALED_SIZE;
        int col = bomb[0].getX() / Sprite.SCALED_SIZE;
        if (SizeBom == 1) {
            if (col + 1 <= WIDTH) {
                if (canPass(row, col + 1)) {
                    bomb[1].setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
                }
            }
            if (row + 1 <= HEIGHT) {
                if (canPass(row + 1, col)) {
                    bomb[7].setImg(Sprite.explosion_vertical_down_last2.getFxImage());
                }
            }
            if (col - 1 >= 0) {
                if (canPass(row, col - 1)) {
                    bomb[3].setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
                }
            }
            if (row - 1 >= 0) {
                if (canPass(row - 1, col)) {
                    bomb[5].setImg(Sprite.explosion_vertical_top_last2.getFxImage());
                }
            }
        } else if (SizeBom == 2) {
            if (col + 2 < WIDTH) {
                if (canPass(row, col + 2) && canPass(row, col + 1)) {
                    bomb[2].setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
                    bomb[1].setImg(Sprite.explosion_horizontal2.getFxImage());
                } else if (!canPass(row, col + 2) && canPass(row, col + 1)) {
                    bomb[1].setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
                }
            } else {

            }
            if (row + 2 < HEIGHT) {
                if (canPass(row + 2, col) && canPass(row + 1, col)) {
                    bomb[8].setImg(Sprite.explosion_vertical_down_last2.getFxImage());
                    bomb[7].setImg(Sprite.explosion_vertical2.getFxImage());
                } else if (!canPass(row + 2, col) && canPass(row + 1, col)) {
                    bomb[7].setImg(Sprite.explosion_vertical_down_last2.getFxImage());
                }
            } else {

            }
            if (col - 2 > 0) {
                if (canPass(row, col - 2) && canPass(row, col - 1)) {
                    bomb[3].setImg(Sprite.explosion_horizontal2.getFxImage());
                    bomb[4].setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
                } else if (!canPass(row, col - 2) && canPass(row, col - 1)) {
                    bomb[3].setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
                }
            } else if (col == 2) {
                if (canPass(row, col - 1)) {
                    bomb[3].setEntity(col - 1, row, Sprite.explosion_horizontal_left_last2.getFxImage());
                }
            }
            if (row - 2 > 0) {
                if (canPass(row - 2, col) && canPass(row - 1, col)) {
                    bomb[6].setImg(Sprite.explosion_vertical_top_last2.getFxImage());
                    bomb[5].setImg(Sprite.explosion_vertical2.getFxImage());
                } else if (!canPass(row - 2, col) && canPass(row - 1, col)) {
                    bomb[5].setImg(Sprite.explosion_vertical_top_last2.getFxImage());
                }
            } else if (row == 2) {
                if (canPass(row - 1, col)) {
                    bomb[5].setEntity(col, row - 1, Sprite.explosion_vertical_top_last2.getFxImage());
                }
            }
        }
    }

    public void frameBom() {
        if (frame_bom > -1) {
            if (frame_bom == 5 * one_frame_bom) {
                bomb[0].setImg(Sprite.bomb_1.getFxImage());
            } else if (frame_bom == 4 * one_frame_bom) {
                bomb[0].setImg(Sprite.bomb_2.getFxImage());
            } else if (frame_bom == 3 * one_frame_bom) {
                setBomExploded();

            } else if (frame_bom == 2 * one_frame_bom) {
                setBomExploded1();
            } else if (frame_bom == one_frame_bom) {
                setBomExploded2();
            }
            if (frame_bom == 0) {
                for (int i = 0; i < 9; i++) {
                    if (checkbomb[i]) {
                        stillObjects.remove(bomb[i]);
                        checkbomb[i] = false;
                    }
                }
                distanceUp = distanceDown = distanceRight = distanceLeft = 0;
                explodeRight = explodeUp = explodeDown = explodeLeft = 0;
            }
            frame_bom--;
        } else {
            status = false;
        }
    }

    public void setSizeBom(int x) {
        SizeBom = x;
    }

    /*public void setStatus(boolean s){
        status = s;
    }*/
    public boolean getStatus() {
        return status;
    }

    public void setPos(int a, int b) {
        x = a * 32;
        y = b * 32;
        status = true;
        frame_bom = 6 * one_frame_bom;
        bomb[0].setEntity(a, b, Sprite.bomb.getFxImage());
        stillObjects.add(bomb[0]);
        for (int i = 0; i < 9; i++) {
            checkbomb[i] = false;
        }
        RowY = b;
        ColX = a;
        distanceRight = checkRight();
        distanceLeft = checkLeft();
        distanceDown = checkDown();
        distanceUp = checkUp();
        updateExplode();
        Sound.playSound(Sound.setBomb);
    }

    public int checkRight() {
        if (SizeBom == 2 && ColX + 2 < WIDTH && canExplode(RowY, ColX + 2) && canPass(RowY, ColX+1)) {
            return 2;
        }
        if (canExplode(RowY, ColX + 1)) {
            return 1;
        }
        return 0;
    }

    public int checkLeft() {

        if (SizeBom == 2 && ColX - 2 > 0 && canExplode(RowY, ColX - 2) && canPass(RowY, ColX-1)) {
            return 2;
        }
        if (canExplode(RowY, ColX - 1)) {
            return 1;
        }
        return 0;
    }

    public int checkDown() {
        if (SizeBom == 2 && RowY + 2 < HEIGHT && canExplode(RowY + 2, ColX) && canPass(RowY+1,ColX)) {
            //brickDown = true;
            return 2;
        }
        if (canExplode(RowY + 1, ColX)) {
            //brickDown = true;
            return 1;
        }
        return 0;
    }

    public int checkUp() {
        if (SizeBom == 2 && RowY - 2 > 0 && (canExplode(RowY - 2, ColX)) && canPass(RowY -1, ColX)) {
            //brickUp = true;
            return 2;
        }
        if (canExplode(RowY - 1, ColX)) {
            //brickUp = true;
            return 1;
        }
        return 0;
    }

    public int getFrame_bom() {
        return frame_bom;
    }

    public void setSize(int height, int width) {
        HEIGHT = height;
        WIDTH = width;
    }

    public int getOne_frame_bom() {
        return one_frame_bom;
    }

    public int getDistanceRight() {
        return distanceRight;
    }

    public int getDistanceLeft() {
        return distanceLeft;
    }

    public int getDistanceUp() {
        return distanceUp;
    }

    public int getDistanceDown() {
        return distanceDown;
    }

    public void setCharAtMap(char r, int i, int j) {
        map[i][j] = r;
    }

    public boolean canExplode(int i, int j) {
        if (map[i][j] == 'x' || map[i][j] == 's' || map[i][j] == 'b' || map[i][j] == 'e' || map[i][j] == '*') {
            return true;
        }
        return false;
    }

    public boolean canPass(int i, int j) {
        if (map[i][j] == ' ' || map[i][j] == 'p' || map[i][j] == '1' || map[i][j] == '2' || map[i][j] == '3'
                || map[i][j] == '4' || map[i][j] == '5' || map[i][j] == '6' || map[i][j] == 'y') {
            return true;
        }
        return false;
    }
    public int getExplodeRight() {
        return explodeRight;
    }
    public int getExplodeLeft() {
        return explodeLeft;
    }
    public int getExplodeUp() {
        return explodeUp;
    }
    public int getExplodeDown() {
        return explodeDown;
    }

    public void updateExplode() {
        int row = bomb[0].getY() / Sprite.SCALED_SIZE;
        int col = bomb[0].getX() / Sprite.SCALED_SIZE;
        if (SizeBom == 1) {
            if (col + 1 <= WIDTH) {
                if (canPass(row, col + 1)) {
                    explodeRight = 1;
                }
            }
            if (row + 1 <= HEIGHT) {
                if (canPass(row + 1, col)) {
                    explodeDown = 1;
                }
            }
            if (col - 1 >= 0) {
                if (canPass(row, col - 1)) {
                    explodeLeft = 1;
                }
            }
            if (row - 1 >= 0) {
                if (canPass(row - 1, col)) {
                    explodeUp = 1;
                }
            }
        } else if (SizeBom == 2) {
            if (col + 2 < WIDTH) {
                if (canPass(row, col + 2) && canPass(row, col + 1)) {
                    explodeRight = 2;
                } else if (canPass(row, col + 1) && !canPass(row, col + 2)) {
                    explodeRight = 1;
                }
            } else if (col + 2 >= WIDTH) {
            }
            if (row + 2 < HEIGHT) {
                if (canPass(row + 2, col) && canPass(row + 1, col)) {
                    explodeDown = 2;
                } else if (canPass(row + 1, col) && !canPass(row + 2, col)) {
                    explodeDown = 1;
                }
            } else {

            }
            if (col - 2 > 0) {
                if (canPass(row, col - 2) && canPass(row, col - 1)) {
                    explodeLeft = 2;
                } else if (canPass(row, col - 1) && !canPass(row, col - 2)) {
                    explodeLeft = 1;
                }
            } else if (col == 2) {
                if (canPass(row, col - 1)) {
                    explodeLeft = 1;
                }
            }
            if (row - 2 > 0) {
                if (canPass(row - 2, col) && canPass(row - 1, col)) {
                    explodeUp = 2;
                } else if (canPass(row - 1, col) && !canPass(row - 2, col)) {
                    explodeDown = 1;
                }
            } else if (row == 2) {
                if (canPass(row - 1, col)) {
                    explodeUp = 1;
                }
            }
        }
    }

}
