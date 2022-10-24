package booman_fx.objects.Character.Enemy;

import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.game.GameResources.Images;
import booman_fx.game.Manager.EnemyManager;
import booman_fx.game.GameResources.Pair;
import booman_fx.objects.Character.Character;
import booman_fx.objects.Portal;

import java.util.List;
import java.util.stream.Collectors;

import static booman_fx.Enum.Direction.DOWN;
import static booman_fx.Enum.TypeSprite.ENEMY;

public class Enemy extends Character {
    protected EnemyManager controller;
    public static void createEnemy(int realX, int realY) {
        switch (App.gameAttribute.getLevel()) {
            case 1 -> App.gameAttribute.spawn(new EnemyLevel1(realX, realY));
            case 2 -> App.gameAttribute.spawn(new EnemyLevel2(realX, realY));
            case 3 -> App.gameAttribute.spawn(new EnemyLevel3(realX, realY));
            case 4 -> App.gameAttribute.spawn(new EnemyLevel4(realX, realY));
            case 5 -> App.gameAttribute.spawn(new EnemyLevel5(realX, realY));
        }
    }

    public Enemy(int realX, int realY) {
        super(Images.enemy[App.gameAttribute.getLevel() - 1][DOWN.ordinal()][0].getImage(),
                realX, realY, ENEMY, Images.enemy[App.gameAttribute.getLevel() - 1]);
    }

    @Override
    public void update() {
        controller.control();
        super.update();
    }


    @Override
    public void handleDeath() {
        super.handleDeath();
        List<Sprite> enemies = App.gameAttribute.sprites().stream().filter(sprite -> sprite instanceof Enemy).collect(Collectors.toList());
        if (enemies.isEmpty()) {
            Pair position = App.gameAttribute.getSpritesMap().findEmptySquare();
            Portal.createPortal(position.getX(), position.getY());
        }
    }
}
