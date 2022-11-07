package uet.oop.bomberman.Menu.Buttons;

public class RectExitButton extends RectButton {
    private final String label = "EXIT";
    private double xPos = 160;
    private double yPos = 380;

    public RectExitButton() {
        super();
        setText(label);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }

    public RectExitButton(double x, double y) {
        super();
        setText(label);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
