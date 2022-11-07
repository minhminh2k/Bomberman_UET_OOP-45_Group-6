package uet.oop.bomberman.Menu.Buttons;

public class RectHelpButton extends RectButton {
    private String lable = "HELP";
    private double xPos = 160;
    private double yPos = 310;

    public RectHelpButton() {
        super();
        setText(lable);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}


