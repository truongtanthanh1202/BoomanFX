package booman_fx.objects.Character.Player;

import booman_fx.Enum.TypePlayer;
import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.game.GameResources.Images;
import booman_fx.objects.Bomb;
import booman_fx.objects.Box;
import booman_fx.objects.Character.Character;
import booman_fx.objects.Explode;
import booman_fx.objects.Item.Item;
import booman_fx.objects.Wall;
import javafx.scene.image.Image;

import static booman_fx.Enum.Direction.*;
import static booman_fx.Enum.StatusCharacter.IMMORTAL;
import static booman_fx.Enum.TypeSprite.*;

public class Player extends Character {
    public static TypePlayer type = TypePlayer.LIGHTGREEN;

    public static Player createPlayer(int xInMap, int yInMap) {
        Player player = new Player(xInMap, yInMap);
        App.gameAttribute.spawn(player);
        return player;
    }

    public Player(int xInMap, int yInMap) {
        super(Images.boomer[type.ordinal()][DOWN.ordinal()][0].getImage(),
                xInMap, yInMap, PLAYER, Images.boomer[type.ordinal()]);
    }

    @Override
    public void executeCollision() {
        boolean[] collisionBomb = new boolean[2];

        for (Sprite sprite : App.gameAttribute.sprites()) {
            if (this.checkCollision(sprite)) {
                if (sprite instanceof Wall) {
                    collisionWall();
                } else if (sprite instanceof Box) {
                    collisionBox();
                } else if (sprite instanceof Explode && status != IMMORTAL) {
                    collisionExplode();
                } else if (sprite instanceof Bomb) {
                    if (sprite != onBomb[0] && sprite != onBomb[1]) {
                        moveBack();
                    } else {
                        if (sprite == onBomb[0]) {
                            collisionBomb[0] = true;
                        } else {
                            collisionBomb[1] = true;
                        }
                    }
                } else if (sprite instanceof Item) {
                    collisionItem((Item) sprite);
                }
            }
        }

//        if (onBomb[0] != null && !collisionBomb[0]) {
//            onBomb[0] = null;
//        }
    }

    @Override
    public Image getImageInfo() {
        return super.getImageInfo();
    }

    @Override
    public void update() {
        super.update();
    }
}
