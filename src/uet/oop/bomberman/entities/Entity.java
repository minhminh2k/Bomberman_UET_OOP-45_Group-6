package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    public char [][]map;
    protected int rowMap;
    protected int colMap;

    public Entity() {

    }
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }
    public void setEntity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }
    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void moveRight(){
    }
    public void moveLeft(){}
    public void moveUp(){}
    public void moveDown(){}
    public void updateMap(char[][] mapMatrix, int row, int col) {
        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map[i][j] = mapMatrix[i][j];
            }
        }
        colMap = col;
        rowMap = row;
    }
    public void setPos(int a, int b) {
        x = a;
        y = b;
    }

}
