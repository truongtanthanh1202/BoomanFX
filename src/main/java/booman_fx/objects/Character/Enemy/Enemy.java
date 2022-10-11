package booman_fx.objects.Character.Enemy;

import booman_fx.Enum.TypeSprite;
import booman_fx.game.App;
import booman_fx.game.Images;
import booman_fx.objects.Character.Character;
import javafx.scene.image.Image;

import static booman_fx.Enum.Direction.DOWN;
import static booman_fx.Enum.TypeSprite.ENEMY;

public class Enemy extends Character {

    public static Enemy createEnemy(int xInMap, int yInMap) {
//        switch (App.gameAttribute.getLevel()) {
//            case 1 -> App.gameAttribute.spawn(new EnemyLevel1(xInMap, yInMap));
//            case 2 -> App.gameAttribute.spawn(new EnemyLevel2(xInMap, yInMap));
//            case 3 -> App.gameAttribute.spawn(new EnemyLevel3(xInMap, yInMap));
//            case 4 -> App.gameAttribute.spawn(new EnemyLevel4(xInMap, yInMap));
//            case 5 -> App.gameAttribute.spawn(new EnemyLevel5(xInMap, yInMap));
//        }

        Enemy enemy = new Enemy(xInMap, yInMap);
        App.gameAttribute.spawn(enemy);
        return enemy;
    }

    public Enemy(int xInMap, int yInMap) {
        super(Images.enemy[App.gameAttribute.getLevel() - 1][DOWN.ordinal()][0].getImage(),
                xInMap, yInMap, ENEMY, Images.enemy[App.gameAttribute.getLevel() - 1]);

    }
}
