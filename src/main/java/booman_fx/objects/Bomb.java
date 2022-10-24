package booman_fx.objects;

import booman_fx.game.GameResources.Images;
import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.objects.Character.Character;

import java.util.Date;

import static booman_fx.Enum.TypeSprite.*;

public class Bomb extends Sprite {
    private static final Images[] BOMB_IMAGE = Images.bomb;
    private static final double DEATH_TIME = 3;

    private final Character character;
    private double deathTime = DEATH_TIME;
    private final int power;

    public static void createBomb(int realX, int realY, Character character){
        App.gameAttribute.spawn(new Bomb(realX, realY, character));
    }

    // constructor
    public Bomb(int realX, int realY, Character character) {
        super(BOMB_IMAGE[0].getImage(), realX, realY, BOMB);
        this.character = character;
        this.power = character.powerBombProperty().getValue();

        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (sprite instanceof Character && sprite.checkCollision(this)) {
                ((Character) sprite).setOnBomb(((Character) sprite).getOnBomb(), this);

            }
        }
    }

    public int getPower() {
        return power;
    }

    @Override
    public void update() {
        setImage(BOMB_IMAGE[(int) (new Date().getTime() / 100) % 2 + 1].getImage());
        deathTime -= 1.0 / App.gameAttribute.getFramesPerSecond();
        if (deathTime < 0) {
            handleDeath();
        }
    }

    @Override
    public void executeCollision() {
        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (this.checkCollision(sprite) && sprite instanceof Explode) {
                handleDeath();
            }
        }

    }

    @Override
    public void handleDeath() {
        if (App.gameAttribute.getSoundEffectGame().isStatus()) {
            App.gameAttribute.getSoundEffectGame().play();
        }
        super.handleDeath();
        character.increaseNumBomb();
        Explode.createExplode(xInMap, yInMap, power);
    }
}
