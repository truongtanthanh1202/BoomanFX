package booman_fx.objects;

import booman_fx.game.GameResources.Images;
import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.objects.Item.Item;

import static booman_fx.Enum.TypeSprite.*;

public class Explode extends Sprite {
    private static final double DEATH_TIME = 0.3;
    private double deathTime = DEATH_TIME;
    private boolean createItem;

    public static void createExplode(int realX, int realY, int power){
        App.gameAttribute.spawn(new Explode(realX, realY, 6));
        boolean up = false;
        boolean left = false;
        boolean down = false;
        boolean right = false;
        Explode explode;

        for (int i = 1; i <= power; ++i) {
            if (!up) {
                explode = new Explode(realX, realY - i, (i == power) ? 2 : 0);
                up = spawnExplode(explode);
            }

            if (!down) {
                explode = new Explode(realX, realY + i, (i == power) ? 4 : 0);
                down = spawnExplode(explode);
            }

            if (!left) {
                explode = new Explode(realX - i, realY, (i == power) ? 5 : 1);
                left = spawnExplode(explode);
            }

            if (!right) {
                explode = new Explode(realX + i, realY, (i == power) ? 3 : 1);
                right = spawnExplode(explode);
            }
        }
    }

    // check explode can spread out
    private static boolean spawnExplode(Explode explode) {
        if (explode.collisionBox()) {
            App.gameAttribute.spawn(explode);
            return true;
        } else if (!explode.collisionWall()) {
            App.gameAttribute.spawn(explode);
            return false;
        }
        return true;
    }

    // constructor
    public Explode(int realX, int realY, int index) {
        super(Images.explodes[index].getImage(), realX, realY, EXPLODE);
        createItem = false;
    }

    @Override
    public void update() {
        deathTime -= 1.0 / App.gameAttribute.getFramesPerSecond();
        if (deathTime < 0) {
            handleDeath();
        }
    }

    @Override
    public void executeCollision() {
        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (this.checkCollision(sprite) && sprite instanceof Box) {
                sprite.handleDeath();
                createItem = true;
            }
        }
    }

    @Override
    public void handleDeath() {
        super.handleDeath();
        if (createItem) {
            Item.createItem(xInMap, yInMap);
        }
    }

    // check collision with wall
    public boolean collisionWall() {
        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (sprite instanceof Wall && this.checkCollision(sprite)) {
                return true;
            }
        }
        return false;
    }

    // check collision with box
    public boolean collisionBox() {
        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (sprite instanceof Box && this.checkCollision(sprite)) {
                return true;
            }
        }
        return false;
    }
}
