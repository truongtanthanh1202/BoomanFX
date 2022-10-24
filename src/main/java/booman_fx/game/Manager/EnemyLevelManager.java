package booman_fx.game.Manager;

import booman_fx.objects.Character.Enemy.Enemy;
import booman_fx.objects.Character.Enemy.*;

import static booman_fx.Enum.TypeSprite.*;

public class EnemyLevelManager extends EnemyManager {
    public EnemyLevelManager(Enemy enemy) {
        super(enemy);
    }

    @Override
    public void control() {
        if (enemy instanceof EnemyLevel1) {
            if (nextStep.equals(enemy.getXInMap(), enemy.getYInMap()) || (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())
                    && spritesMap.getMap()[nextStep.getY()][nextStep.getX()].getTypeSprite(BOMB))) {
                super.control();
                findRandomWay(false);
                setMove();
            }
        } else if (enemy instanceof EnemyLevel2) {
            super.control();
            if (nextStep.equals(enemy.getXInMap(), enemy.getYInMap()) || (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())
                    && spritesMap.getMap()[nextStep.getY()][nextStep.getX()].getTypeSprite(BOMB))) {
                checkStoreBomb(true, false, 0);
                if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                    avoidBomb(0);
                } else {
                    findRandomWay(true);
                }
                setMove();
            }
        } else if (enemy instanceof EnemyLevel3) {
            if (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())) {
                super.control();
                checkStoreBomb(true, true, 0);
                if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                    avoidBomb(0);
                } else {
                    findShortestWay();
                }
                setMove();
            }
        } else if (enemy instanceof EnemyLevel4) {
            if (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())) {
                super.control();
                if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                    if (!avoidBomb(1)) {
                        avoidBomb(0);
                    }
                } else {
                    checkStoreBomb(true, true, 1);
                    findShortestWay();
                }
                setMove();
            }
        } else {
            if (enemy.checkCovered(enemy.getXInMap(), enemy.getYInMap())) {
                super.control();
                if (spritesMap.checkDanger(enemy.getXInMap(), enemy.getYInMap())) {
                    if (!avoidBomb(2)) {
                        if (!avoidBomb(1)) {
                            avoidBomb(0);
                        }
                    }
                } else {
                    checkStoreBomb(true, true, 2);
                    findShortestWay();
                }
                setMove();
            }
        }
    }
}
