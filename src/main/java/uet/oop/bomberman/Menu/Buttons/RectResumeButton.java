package uet.oop.bomberman.Menu.Buttons;

public class RectResumeButton extends RectButton {
    private final String label = "RESUME";
    private double xPos = 160;
    private double yPos = 240;

    public RectResumeButton() {
        super();
        setText(label);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }

    public RectResumeButton(double x, double y) {
        super();
        setText(label);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }
}
