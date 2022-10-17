package booman_fx.controller;

import booman_fx.objects.Character.Enemy;

import static booman_fx.Enum.TypeSprite.*;

public class EnemyLevel1Controller extends EnemyController {
    public EnemyLevel1Controller(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void control() {
        if (nextStep.equals(enemy.getXInMap(), enemy.getYInMap()) || (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())
                && spritesMap.getMap()[nextStep.getY()][nextStep.getX()].getTypeSprite(BOMB))) {
            super.control();
            findRandomWay(false);
            setMove();
        }
    }
}
