package booman_fx.objects;

import booman_fx.engine.Sprite;
import booman_fx.game.App;

import static booman_fx.Enum.TypeSprite.BACKGROUND;

public class Background extends Sprite {
    public static void createBackground(int realX, int realY){
        App.gameAttribute.spawn(new Background(realX, realY));
    }

    public Background(int realX, int realY) {
        super(App.gameAttribute.getSpritesMap().getBackground().getImage(), realX, realY, BACKGROUND);
    }
}
