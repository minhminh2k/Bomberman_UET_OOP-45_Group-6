package uet.oop.bomberman.Menu.Buttons;

public class RectReturnMainMenuButton extends RectButton {
    private final String label = "RETURN TO MAIN MENU";
    private double xPos = 160;
    private double yPos = 380;

    public RectReturnMainMenuButton() {
        super();
        setText(label);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }

    public RectReturnMainMenuButton(double x, double y) {
        super();
        setText(label);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
