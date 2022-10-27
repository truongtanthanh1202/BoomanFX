package booman_fx.game;

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

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import static booman_fx.Enum.StatusGame.*;
import static booman_fx.Enum.TypeSprite.*;
import static booman_fx.game.App.setRoot;

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
            switch (level) {
                case 3, 4 -> {
                    timeLeft.setValue(60*500);
                }
                case 5 -> {
                    timeLeft.setValue(60*600);
                } default -> {
                    timeLeft.setValue(60*300);
                }
            }
            generateMap();
            setRoot("GameSurface");
        }
    }

    @Override
    public void destroy(Sprite sprite) {
        super.destroy(sprite);
        spritesMap.removeSprite(sprite);
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

        if (status.getValue() == WIN.ordinal()) {
            checkWinGame();
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
        if (timeLeft.get() < 0 || player == null || player.livesProperty().getValue() <= 0) {
            StatusGame.setLoss(this);
            sleep(1);
            shutdown();
            pause();
            setRoot("EndGame");
        }
    }

    @Override
    protected boolean checkWinGame() {
        if (status.getValue() == WIN.ordinal()) {
            shutdown();
            sleep(1);
            App.setRoot("EndGame");
            return true;
        }

        if (level >= MAX_LEVEL) {
            StatusGame.setWin(this);
            return true;
        }

        return false;
    }

    public static void reGenerateMap() {
        generateMap();
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
