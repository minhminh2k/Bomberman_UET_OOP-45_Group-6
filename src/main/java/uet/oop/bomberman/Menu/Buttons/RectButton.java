package uet.oop.bomberman.Menu.Buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import uet.oop.bomberman.Sound.Sound;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class RectButton extends Button implements ButtonAction {
    protected final double BUTTON_WIDTH = 190;
    protected final double BUTTON_HEIGHT = 48.3;
    private final String FONT_PATH = "res/BreathFire.ttf";

    //BUTTON STYLE
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('button_pressed_rect.png');";
    private final String BUTTON_DEFAULT = "-fx-background-color: transparent ; -fx-background-image: url('button_default_rect.png');";

    public RectButton() {
        setButtonLabelFont();
        setBUTTON_HEIGHT();
        setBUTTON_WIDTH();
        setButtonDefault();
        ButtonEventHandler();
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
    public void setButtonDefault() {
        setStyle(BUTTON_DEFAULT);
    }

    /**
     * This function sets the button's font.
     */
    private void setButtonLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 17));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function sets the button's pressed-style.
     */
    public void setButtonPressed() {
        Sound.playSound(Sound.buttonTouch);
        setStyle(BUTTON_PRESSED);
        setPrefHeight(BUTTON_HEIGHT - 4);
        setLayoutY(getLayoutY() + 4);
    }

    /**
     * This function sets the button's released-style.
     */
    public void setButtonReleased() {
        setStyle(BUTTON_DEFAULT);
        setPrefHeight(BUTTON_HEIGHT);
        setLayoutY(getLayoutY() - 4);
    }

    /**
     * This function handles the button's reactions.
     */
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
