package booman_fx.Enum;

public enum Direction {
    LEFT, RIGHT, DOWN, UP;

    public static Direction oppositeDirect(Direction direct) {
        Direction oppositeDirect = UP;
        switch (direct) {
            case UP -> oppositeDirect = DOWN;
            case LEFT -> oppositeDirect = RIGHT;
            case RIGHT -> oppositeDirect = LEFT;
        }
        return oppositeDirect;
    }
}
