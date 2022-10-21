package booman_fx.objects.Character.Player;

import booman_fx.Enum.TypePlayer;
import booman_fx.game.App;
import booman_fx.game.Images;
import booman_fx.objects.Character.Character;
import javafx.scene.image.Image;

import static booman_fx.Enum.Direction.*;
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
    public Image getImageInfo() {
        return super.getImageInfo();
    }
}
