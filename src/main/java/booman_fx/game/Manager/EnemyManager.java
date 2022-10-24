package booman_fx.game.Manager;

import booman_fx.Enum.Direction;
import booman_fx.Enum.TypeSprite;
import booman_fx.game.App;
import booman_fx.game.Map;
import booman_fx.objects.Character.Enemy.Enemy;
import booman_fx.game.GameResources.Pair;

import java.util.*;

import static booman_fx.Enum.Direction.*;
import static booman_fx.Enum.TypeSprite.*;

public abstract class EnemyManager {
    // this enemy to control
    protected final Enemy enemy;

    // map of game
    protected Map spritesMap;

    protected Pair nextStep;

    protected Direction direction;

    public EnemyManager(Enemy enemy) {
        this.enemy = enemy;
        // way to move
        nextStep = new Pair(enemy.getXInMap(), enemy.getYInMap());
        direction = UP;
    }


    protected void checkExitWay(boolean exitWay) {
        if (!exitWay) {
            direction = Direction.oppositeDirect(direction);
            int nextX = enemy.getXInMap() + Map.dx[direction.ordinal()];
            int nextY = enemy.getYInMap() + Map.dy[direction.ordinal()];
            if (spritesMap.getMap()[nextY][nextX].getTypeSprite(BOMB)) {
                nextStep = new Pair(enemy.getXInMap(), enemy.getYInMap());
            } else {
                nextStep = new Pair(nextX, nextY);
            }
        }
    }
    //find random way
    protected void findRandomWay(boolean acrossBox) {
        boolean exitWay = false;
        boolean[] used = new boolean[Direction.values().length];
        for (int i = 0; i < 3; ++i) {
            Direction direct;
            while (true) {
                direct = Direction.values()[Math.abs(new Random().nextInt() % 4)];
                if (direct != Direction.oppositeDirect(direction) && !used[direct.ordinal()]) {
                    used[direct.ordinal()] = true;
                    break;
                }
            }

            int nextX = enemy.getXInMap() + Map.dx[direct.ordinal()];//Enemy move
            int nextY = enemy.getYInMap() + Map.dy[direct.ordinal()];
            if ((!acrossBox && spritesMap.getMap()[nextY][nextX].checkNotExist(new TypeSprite[]{BOMB, BOX, EXPLODE, WALL}))
                    || (acrossBox && spritesMap.getMap()[nextY][nextX].checkNotExist(new TypeSprite[]{BOMB, EXPLODE, WALL}))) {
                nextStep = new Pair(nextX, nextY);
                direction = direct;
                exitWay = true;
                break;
            }
        }
        checkExitWay(exitWay);
    }



    // find the shortest way from this enemy to player.
    protected void findShortestWay() {
        int[][] dis = new int[spritesMap.getHeight()][spritesMap.getWidth()];
        Pair[][] last = new Pair[spritesMap.getHeight()][spritesMap.getWidth()];
        for (int i = 0; i < spritesMap.getHeight(); i++) {
            for (int j = 0; j < spritesMap.getWidth(); j++) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        Deque<Pair> way = new ArrayDeque<>();
        way.addLast(new Pair(enemy.getXInMap(), enemy.getYInMap()));
        dis[enemy.getYInMap()][enemy.getXInMap()] = 0;

        while (!way.isEmpty()) {
            Pair cur = way.pop();
            if (spritesMap.getMap()[cur.getY()][cur.getX()].getTypeSprite(PLAYER)) {
                while (!cur.equals(new Pair(enemy.getXInMap(), enemy.getYInMap()))) {
                    nextStep = cur;
                    cur = last[cur.getY()][cur.getX()];
                }
                break;
            }

            for (Direction direct : Direction.values()) {
                int X = cur.getX() + Map.dx[direct.ordinal()];
                int Y = cur.getY() + Map.dy[direct.ordinal()];
                if (spritesMap.checkSquareInMap(X, Y) && dis[Y][X] == Integer.MAX_VALUE) {
                    dis[Y][X] = dis[cur.getY()][cur.getX()] + 1;
                    if (dis[Y][X] <= 1) {
                        if ((!spritesMap.checkDanger(X, Y) && spritesMap.getMap()[Y][X].checkNotExist(new TypeSprite[]{EXPLODE, WALL, BOX}))) {
                            way.addLast(new Pair(X, Y));
                            last[Y][X] = new Pair(cur.getX(), cur.getY());
                        }
                    } else {
                        if (!spritesMap.getMap()[Y][X].getTypeSprite(WALL)) {
                            way.addLast(new Pair(X, Y));
                            last[Y][X] = new Pair(cur.getX(), cur.getY());
                        }
                    }
                }
            }
        }
    }



    protected boolean checkCondition(int safe, Pair cur) {
        if (safe >= 1) {
            if ((cur.getX() == enemy.getXInMap() && Math.abs(cur.getY() - enemy.getYInMap()) <= enemy.powerBombProperty().getValue())
                    || (cur.getY() == enemy.getYInMap() && Math.abs(cur.getX() - enemy.getXInMap()) <= enemy.powerBombProperty().getValue())) {
                return false;
            }
        }

        if (spritesMap.checkDanger(cur.getX(), cur.getY())) {
            return false;
        }
        return true;
    }

    // find way to avoid bomb if this enemy is threatened
    protected boolean avoidBomb(int safe) {
        boolean[][] visited = new boolean[spritesMap.getHeight()][spritesMap.getWidth()];
        Pair[][] last = new Pair[spritesMap.getHeight()][spritesMap.getWidth()];
        Deque<Pair> way = new ArrayDeque<>();
        way.addLast(new Pair(enemy.getXInMap(), enemy.getYInMap()));

        while (!way.isEmpty()) {
            Pair cur = way.pop();
            boolean existWay = checkCondition(safe, cur);
            if (existWay) {
                while (!cur.equals(new Pair(enemy.getXInMap(), enemy.getYInMap()))) {
                    nextStep = cur;
                    cur = last[cur.getY()][cur.getX()];
                }
                return true;
            }

            visited[cur.getY()][cur.getX()] = true;
            for (int i = 0; i < Direction.values().length; ++i) {
                int X = cur.getX() + Map.dx[i];
                int Y = cur.getY() + Map.dy[i];
                if (spritesMap.checkSquareInMap(X, Y) && !visited[Y][X]
                        && spritesMap.getMap()[Y][X].checkNotExist(new TypeSprite[]{BOMB, BOX, EXPLODE, WALL})) {
                    if ((safe >= 1 && spritesMap.checkDanger(X, Y)) ||
                            (safe >= 2 && spritesMap.getMap()[Y][X].getTypeSprite(PLAYER))) {
                        continue;
                    }

                    way.addLast(new Pair(X, Y));
                    last[Y][X] = new Pair(cur.getX(), cur.getY());
                }
            }
        }
        return false;
    }

    private void storeBomb(int safe) {
        // player and enemy in a square, player set bomb -> enemy not set bomb
        if (spritesMap.getMap()[enemy.getYInMap()][enemy.getXInMap()].getTypeSprite(BOMB)) {
            return;
        }

        if (enemy.numBombProperty().getValue() > 0 && avoidBomb(safe)) {
            enemy.storeBomb();
        }
    }

    protected void checkStoreBomb(boolean destroyPlayer, Boolean destroyBox, int safe) {
        spritesMap = App.gameAttribute.getSpritesMap();
        if (spritesMap.getMap()[enemy.getYInMap()][enemy.getXInMap()].getTypeSprite(BOMB)) {
            return;
        }

        boolean exist = false;
        for (Direction direct : Direction.values()) {
            int x = enemy.getXInMap() + Map.dx[direct.ordinal()];
            int y = enemy.getYInMap() + Map.dy[direct.ordinal()];
            if ((destroyPlayer && spritesMap.getMap()[y][x].getTypeSprite(PLAYER))
                    || destroyBox && spritesMap.getMap()[y][x].getTypeSprite(BOX)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            storeBomb(safe);
        }
    }

    protected void setStatic() {
        enemy.setMoveLeft(false);
        enemy.setMoveUp(false);
        enemy.setMoveDown(false);
        enemy.setMoveRight(false);
    }

    protected void setMove() {
        setStatic();
        if (enemy.getYInMap() < nextStep.getY()) {
            enemy.setMoveDown(true);
            direction = DOWN;
        } else if (enemy.getYInMap() > nextStep.getY()) {
            enemy.setMoveUp(true);
            direction = UP;
        } else if (enemy.getXInMap() < nextStep.getX()) {
            enemy.setMoveRight(true);
            direction = RIGHT;
        } else if (enemy.getXInMap() > nextStep.getX()) {
            enemy.setMoveLeft(true);
            direction = LEFT;
        }
    }

    // control this enemy
    public void control() {
        spritesMap = App.gameAttribute.getSpritesMap();
    }
}
