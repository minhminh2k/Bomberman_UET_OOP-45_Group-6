package uet.oop.bomberman.entities.MainMenu;

public class ResumeButton extends MyButton {
    private String lable = "RESUME";
    private double xPos = 160;
    private double yPos = 240;

    public ResumeButton() {
        super();
        setText(lable);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}
