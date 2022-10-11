package booman_fx.game;

//import booman_fx.Enum.StatusGame;
import booman_fx.engine.GameAttribute;
import booman_fx.engine.Sprite;
import booman_fx.game.GameResources.GameMusic;
import booman_fx.game.GameResources.SoundEffect;
import booman_fx.objects.*;
import booman_fx.objects.Character.Enemy.Enemy;
import booman_fx.objects.Character.Player.Player;
//import booman_fx.objects.Character.Enemy.Enemy;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static booman_fx.Enum.TypeSprite.*;
import static booman_fx.game.App.setRoot;
//import static booman_fx.Enum.StatusGame.*;

public class GameState extends GameAttribute {
    public static final int HEIGHT = 600;
    public static final int WIDTH = 680;
    public static final int SIZE_A_SQUARE = 40;
    public static final int fps = 60;
    private static final int MAX_LEVEL = 5;

    public GameState(int framesPerSecond) {
        super(framesPerSecond);
        level = getLevel();
    }

    public static void initial() {
        App.gameAttribute = new GameState(60);
        generateMap();
    }

    public static void startSound() {
        musicGame = new GameMusic("src/main/resources/booman_fx/music/BackgroundMusic.mp3");
        soundEffectGame = new SoundEffect("src/main/resources/booman_fx/music/BoomExplode.wav");
        musicGame.play();
    }

    @Override
    protected void showSprites() {
        List<Sprite> sprites = sprites().stream().filter(sprite -> !(sprite instanceof Background)).collect(Collectors.toList());
        sceneSprites.getChildren().removeAll(sprites);
        Collections.sort(sprites);
        sceneSprites.getChildren().addAll(sprites);
    }

    private static void generateMap() {
        spritesMap = new Map(WIDTH / SIZE_A_SQUARE, HEIGHT / SIZE_A_SQUARE, level);
        for (int h = 0; h < HEIGHT / SIZE_A_SQUARE; ++h) {
            for (int w = 0; w < WIDTH / SIZE_A_SQUARE; ++w) {
                Background.createBackground(w, h);
            }
        }

        for (int h = 0; h < HEIGHT / SIZE_A_SQUARE; ++h) {
            for (int w = 0; w < WIDTH / SIZE_A_SQUARE; ++w) {
                if (spritesMap.getMap()[h][w].getTypeSprite(BOX)) {
                    Box.createBox(w, h);
                }
                else if (spritesMap.getMap()[h][w].getTypeSprite(WALL)) {
                    Wall.createWall(w, h);
                }
                else if (spritesMap.getMap()[h][w].getTypeSprite(ENEMY)) {
                    Enemy.createEnemy(w, h);
                }
                else if (spritesMap.getMap()[h][w].getTypeSprite(PLAYER)) {
                    Player.createPlayer(w, h);
//                    player = Player.createPlayer(w, h);
//                    setInput();
                }
            }
        }
    }

//    private int importLevelFromFile() {
//        String url = "src/main/resources/booman_fx/map/level-map.txt";
//        FileInputStream fileInputStream = null;
//        try {
//            fileInputStream = new FileInputStream(url);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        Scanner input = new Scanner(Objects.requireNonNull(fileInputStream));
//        String level = input.nextLine();
//
//        return (level.charAt(7) - '0' - 1);
//    }
}
