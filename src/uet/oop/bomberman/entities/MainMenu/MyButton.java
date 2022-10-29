package uet.oop.bomberman.entities.MainMenu;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class MyButton extends Button {
    protected final double BUTTON_WIDTH = 190;
    protected final double BUTTON_HEIGHT = 48.3;

    private final String FONT_PATH = "res/BreathFire.ttf";
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('red_button_pressed.png');";
    private final String BUTTON_DEFAULT = "-fx-background-color: transparent ; -fx-background-image: url('button.png');";

    public double getBUTTON_WIDTH() {
        return BUTTON_WIDTH;
    }

    public double getBUTTON_HEIGHT() {
        return BUTTON_HEIGHT;
    }

    public MyButton() {
        setButtonLabelFont();
        setBUTTON_HEIGHT();
        setBUTTON_WIDTH();
        setButtonDefault();
        ButtonEventHandler();
    }
    
    private void setBUTTON_HEIGHT() {
        setPrefHeight(BUTTON_HEIGHT);
    }

    private void setBUTTON_WIDTH() {
        setPrefWidth(BUTTON_WIDTH);
    }

    private void setButtonDefault() {
        setStyle(BUTTON_DEFAULT);
    }

    private void setButtonLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 17));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setButtonPressed() {
        setStyle(BUTTON_PRESSED);
        setPrefHeight(BUTTON_HEIGHT - 4);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleased() {
        setStyle(BUTTON_DEFAULT);
        setPrefHeight(BUTTON_HEIGHT);
        setLayoutY(getLayoutY() - 4);
    }

    private void ButtonEventHandler() {

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
