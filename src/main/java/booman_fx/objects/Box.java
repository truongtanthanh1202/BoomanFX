package booman_fx.objects;

import booman_fx.engine.Sprite;
import booman_fx.game.App;

import java.util.Random;

import static booman_fx.Enum.TypeSprite.*;

public class Box extends Sprite {
    public static void createBox(int realX, int realY){
        int index = Math.abs(new Random().nextInt()) % 3;
        App.gameAttribute.spawn(new Box(realX, realY, index));
    }

    public Box(int realX, int realY, int index) {
        super(App.gameAttribute.getSpritesMap().getBox()[index].getImage(), realX, realY, BOX);
    }
}
