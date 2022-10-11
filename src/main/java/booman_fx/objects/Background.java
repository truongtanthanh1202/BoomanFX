package booman_fx.objects;

import booman_fx.engine.Sprite;
import booman_fx.game.App;

import static booman_fx.Enum.TypeSprite.BACKGROUND;

public class Background extends Sprite {
    public static void createBackground(int xInMap, int yInMap){
        App.gameAttribute.spawn(new Background(xInMap, yInMap));
    }

    public Background(int xInMap, int yInMap) {
        super(App.gameAttribute.getSpritesMap().getBackground().getImage(), xInMap, yInMap, BACKGROUND);
    }
}
