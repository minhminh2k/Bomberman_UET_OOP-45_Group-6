package uet.oop.bomberman.Menu.Buttons;

public class CircleSoundButton extends CircleButton {

    private static final double xPos = 560;
    private static final double yPos = 12;
    private final String ADDRESS_MUTED = getClass().getResource("/menu/muted.png").toExternalForm();
    private final String ADDRESS_UNMUTED = getClass().getResource("/menu/unmuted.png").toExternalForm();
    private final String MUTED = "-fx-background-color: transparent; -fx-background-image: url("+ ADDRESS_MUTED +");";
    private final String UNMUTED = "-fx-background-color: transparent ; -fx-background-image: url("+ ADDRESS_UNMUTED +");";
    public CircleSoundButton() {
        super();
        setUNMUTED();
        setLayoutX(xPos);
        setLayoutY(yPos);
    }

    @Override
    public void setButtonDefault() {

    }

    @Override
    public void setButtonPressed() {

    }

    @Override
    public void setButtonReleased() {

    }

    /**
     * This function sets the button's display to muted.
     */
    public void setMUTED() {
        setStyle(MUTED);
    }

    /**
     * This function sets the button's display to unmuted.
     */
    public void setUNMUTED() {
        setStyle(UNMUTED);
    }

    @Override
    public void ButtonEventHandler() {

    }
}
