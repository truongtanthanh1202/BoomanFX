package booman_fx.objects.Character;

import booman_fx.game.Manager.EnemyLevel5Manager;

public class EnemyLevel5 extends Enemy {
    public EnemyLevel5(int xInMap, int yInMap) {
        super(xInMap, yInMap);
        controller = new EnemyLevel5Manager(this);
    }
}
