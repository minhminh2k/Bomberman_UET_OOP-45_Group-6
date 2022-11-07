package uet.oop.bomberman.Menu.Buttons;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CirclePauseButton extends CircleButton {

    private static final double xPos = 930;
    private static final double yPos = 0;
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('pause_button_pressed.png');";
    private final String BUTTON_DEFAULT = "-fx-background-color: transparent ; -fx-background-image: url('pause_button_default.png');";
    public CirclePauseButton() {
        super();
        setButtonDefault();
        ButtonEventHandler();
        setLayoutX(xPos);
        setLayoutY(yPos);
    }

    /**
     * This function sets the button's default-display.
     */
    @Override
    public void setButtonDefault() {
        setStyle(BUTTON_DEFAULT);
    }

    /**
     * This function sets the button's pressed-display.
     */
    @Override
    public void setButtonPressed() {
        setStyle(BUTTON_PRESSED);
        setPrefHeight(BUTTON_HEIGHT - 2);
        setLayoutY(getLayoutY() + 2);
    }

    /**
     * This function sets the button's released-display.
     */
    @Override
    public void setButtonReleased() {
        setStyle(BUTTON_DEFAULT);
        setPrefHeight(BUTTON_HEIGHT);
        setLayoutY(getLayoutY() - 2);
    }

    /**
     * This function sets the button's reactions.
     */
    @Override
    public void ButtonEventHandler() {
        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressed();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleased();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}

