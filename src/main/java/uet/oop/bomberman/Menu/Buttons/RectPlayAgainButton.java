package uet.oop.bomberman.Menu.Buttons;

public class RectPlayAgainButton extends RectButton {

    private final String label = "PLAY AGAIN";
    private double xPos = 200;
    private double yPos = 180;

    public RectPlayAgainButton() {
        super();
        setText(label);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}

