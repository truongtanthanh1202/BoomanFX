package booman_fx.objects;

import booman_fx.engine.Sprite;
import booman_fx.game.App;

import java.util.Random;

import static booman_fx.Enum.TypeSprite.*;

public class Wall extends Sprite {
    public static void createWall(int realX, int realY){
        int index; //có 2 loại wall, index pick random xem ném loại nào
        if ((realY == 0 || realX == 0 || realY + 1 == 15 || realX + 1 == 17)) {
            index = 0;
        } else {
            int random = Math.abs(new Random().nextInt()) % 10;
            index = (random < 3) ? 0 : 1;
        }
        App.gameAttribute.spawn(new Wall(realX, realY, index));
    }

    public Wall(int realX, int realY, int index) {
        super(App.gameAttribute.getSpritesMap().getWall()[index].getImage(), realX, realY, WALL);
    }
}
