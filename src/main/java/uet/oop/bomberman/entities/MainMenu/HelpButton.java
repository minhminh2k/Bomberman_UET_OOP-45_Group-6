package uet.oop.bomberman.entities.MainMenu;

public class HelpButton extends MyButton {
    private String lable = "HELP";
    private double xPos = 160;
    private double yPos = 310;

    public HelpButton() {
        super();
        setText(lable);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}


