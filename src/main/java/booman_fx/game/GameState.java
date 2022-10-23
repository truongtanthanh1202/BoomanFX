package booman_fx.game;

//import booman_fx.Enum.StatusGame;
import booman_fx.Enum.StatusGame;
import booman_fx.engine.GameAttribute;
import booman_fx.engine.Sprite;
import booman_fx.game.GameResources.GameMusic;
import booman_fx.game.GameResources.SoundEffect;
import booman_fx.objects.*;
import booman_fx.objects.Character.Enemy.Enemy;
import booman_fx.objects.Character.Player.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import booman_fx.objects.Character.Enemy.Enemy.Enemy;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static booman_fx.Enum.StatusGame.*;
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
        level = importLevelFromFile();
    }

    public static void initial() {
        App.gameAttribute = new GameState(fps);
        App.gameAttribute.begin();
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

    @Override
    protected void nextLevel() {
        if (isNextLevel) {
            sceneSprites.getChildren().clear();
            isNextLevel = false;
            level++;
            StatusGame.setPlay(this);
            timeLeft.setValue(18000);
            generateMap();
            setRoot("GameSurface");
            begin();
        }
    }

    @Override
    protected void checkNextLevel() {
        if (status.getValue() == PASS_LEVEL.ordinal()) {
            sleep(1);
            isNextLevel = true;
            pause();
            setRoot("ChooseMap");
            return;
        }

        if (spritesMap == null || spritesMap.getMap()[player.getYInMap()][player.getXInMap()].getTypeSprite(PORTAL)) {
            if (!checkWinGame()) {
                StatusGame.setPassLevel(this);
            }
        }
    }

    @Override
    protected void checkEndGame() {
        timeLeft.setValue(timeLeft.get() - 1);
        if (timeLeft.get() < 0 || player == null) {
            sleep(1);
            pause();
            setRoot("EndGame");
        }
        if ((player != null && player.livesProperty().getValue() <= 0)) {
            StatusGame.setLoss(this);
            pause();
            sleep(1);
            setRoot("EndGame");
        }
    }

    @Override
    protected boolean checkWinGame() {
        if (level >= MAX_LEVEL) {
//            StatusGame.setWin(this);
//            return true;
            System.out.println(level);
        }
        return false;
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
                    player = Player.createPlayer(w, h);
                    setInput();
                }
            }
        }
    }

    public void spawn(Sprite sprite) {
        super.spawn(sprite);
        spritesMap.addSprite(sprite);
    }

    private static void setInput() {
        EventHandler<KeyEvent> playerPress = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) {
                player.setMoveLeft(true);
            }
            if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) {
                player.setMoveDown(true);
            }
            if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) {
                player.setMoveRight(true);
            }
            if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) {
                player.setMoveUp(true);
            }
            if (keyEvent.getCode() == KeyCode.SPACE || keyEvent.getCode() == KeyCode.ENTER) {
                if(status.getValue() != PAUSE.ordinal()) {
                    player.storeBomb();
                }
            }
        };
        App.scene.setOnKeyPressed(playerPress);

        EventHandler<KeyEvent> playerRelease = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.LEFT) {
                player.setMoveLeft(false);
            }
            if (keyEvent.getCode() == KeyCode.S || keyEvent.getCode() == KeyCode.DOWN) {
                player.setMoveDown(false);
            }
            if (keyEvent.getCode() == KeyCode.D || keyEvent.getCode() == KeyCode.RIGHT) {
                player.setMoveRight(false);
            }
            if (keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.UP) {
                player.setMoveUp(false);
            }
        };
        App.scene.setOnKeyReleased(playerRelease);
    }

    private static int importLevelFromFile(){
        int levelGame = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/booman_fx/map/level-map.txt");
            Scanner input = new Scanner(Objects.requireNonNull(fileInputStream));
            String level = input.nextLine();
            levelGame = level.charAt(7) - '0' - 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return levelGame;
    }

    @Override
    public void sleep(int second) {
        if (spritesMap != null) {
            try {
                Thread.sleep(second*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
