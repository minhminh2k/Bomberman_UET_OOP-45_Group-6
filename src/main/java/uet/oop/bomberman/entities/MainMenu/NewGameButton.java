package uet.oop.bomberman.entities.MainMenu;

public class NewGameButton extends MyButton {

    private String lable = "NEW GAME";
    private double xPos = 160;
    private double yPos = 170;

    public NewGameButton() {
        super();
        setText(lable);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}
