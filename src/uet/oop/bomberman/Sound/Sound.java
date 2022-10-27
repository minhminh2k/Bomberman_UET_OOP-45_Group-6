package uet.oop.bomberman.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import uet.oop.bomberman.entities.Bomber;

public class Sound {
    private static int count = 0;
    private boolean playing = true;
    private static List<Media> mediaList = new ArrayList<>();
    public static String explosion = "res/SFX/Bomb_Explosion.wav";
    public static String playerDead = "res/SFX/player_dead.wav";
    public static String soundEnemy = "res/SFX/enemy_dead.wav";
    public static String item = "res/SFX/Item.wav";
    public static String setBomb = "res/SFX/setBomb.wav";
    public static String footStep1 = "res/SFX/Footstep 1.wav";
    public static String EndGame = "res/Music/songs/Endgame.mp3";
    public static MediaPlayer soundBackground = new MediaPlayer(
            new Media(new File("res/Music/game/game2.mp3").toURI().toString()));

    public static MediaPlayer test = new MediaPlayer(
            new Media(new File("res/SFX/setBomb.wav").toURI().toString()));

    public static MediaPlayer menuGame = new MediaPlayer(
            new Media(new File("res/Music/menu.mp3").toURI().toString()));

    public static MediaPlayer walk = new MediaPlayer(
            new Media(new File("res/SFX/Footstep 1.wav").toURI().toString()));

    private MediaPlayer mediaPlayer;

    public void init() {
            try {
                mediaList.add(new Media(new File("res/SFX/Action Misc 5.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 6.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 7.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 8.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 9.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 10.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 11.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 12.wav").toURI().toString()));
                mediaList.add(new Media(new File("res/SFX/Action Misc 13.wav").toURI().toString()));
            } catch (Exception e) {
                System.out.println("Sound Constructor Error");
            }
        }
    public static void playSound(String path) {
        try {
            MediaPlayer sound = new MediaPlayer(new Media(new File(path).toURI().toString()));
            sound.setVolume(0.5);
            sound.play();
        } catch (Exception e) {
            System.out.println("Sound Error");
        }
    }

    public void playBackground() {
        if(playing) {
            soundBackground.setVolume(0.2);
            soundBackground.setOnEndOfMedia(new Runnable() {
                public void run() {
                    soundBackground.seek(Duration.ZERO);
                }
            });
            soundBackground.play();
            //playing = false;
        }
        else {
            soundBackground.pause();
        }
    }
    public void soundMoving(Bomber bomberman) {
        if(bomberman.isMoving()) {
            System.out.println("da chay");
            walk.setVolume(0.2);
            walk.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    walk.seek(Duration.ZERO);
                }
            });
            walk.play();
        }
        else {
            walk.pause();
        }
    }


    public static void stopBackground() {
        soundBackground.stop();
    }
}
