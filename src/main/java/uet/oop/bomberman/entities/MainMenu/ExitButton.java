package uet.oop.bomberman.entities.MainMenu;

public class ExitButton extends MyButton {
    private String lable = "EXIT";
    private double xPos = 160;
    private double yPos = 380;

    public ExitButton() {
        super();
        setText(lable);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}
