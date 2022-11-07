package uet.oop.bomberman.Menu.Buttons;

import javafx.scene.control.Button;

public abstract class CircleButton extends Button implements ButtonAction {
    protected final double BUTTON_WIDTH = 30;
    protected final double BUTTON_HEIGHT = 30;

    public CircleButton() {
        setBUTTON_HEIGHT();
        setBUTTON_WIDTH();
    }

    /**
     * This function sets the button's height.
     */
    public void setBUTTON_HEIGHT() {
        setPrefHeight(BUTTON_HEIGHT);
    }

    /**
     * This function sets the button's width.
     */
    public void setBUTTON_WIDTH() {
        setPrefWidth(BUTTON_WIDTH);
    }

    /**
     * This function sets the button's default-display.
     */
    public abstract void setButtonDefault();

    /**
     * This function sets the button's pressed-display.
     */
    public abstract void setButtonPressed();

    /**
     * This function sets the button's released-display.
     */
    public abstract void setButtonReleased();

    /**
     * This function handles the button's reactions.
     */
    public abstract void ButtonEventHandler();
}
