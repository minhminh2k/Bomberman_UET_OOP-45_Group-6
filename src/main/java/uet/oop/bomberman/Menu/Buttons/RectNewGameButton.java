package uet.oop.bomberman.Menu.Buttons;

public class RectNewGameButton extends RectButton {

    private final String label = "NEW GAME";
    private double xPos = 160;
    private double yPos = 170;

    public RectNewGameButton() {
        super();
        setText(label);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
    }
}
