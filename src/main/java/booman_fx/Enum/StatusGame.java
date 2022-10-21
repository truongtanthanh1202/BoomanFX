package booman_fx.Enum;

import booman_fx.engine.GameAttribute;

public enum StatusGame {
    LOSS, WIN, PASS_LEVEL, PLAY, PAUSE;

    public static void setPause(GameAttribute game) {
        game.setStatus(PAUSE.ordinal());
    }

    public static void setLoss(GameAttribute game) {
        game.setStatus(LOSS.ordinal());
        game.setTimeLWP(game.getTimeLWP() - 1);
    }

    public static void setWin(GameAttribute game) {
        game.setStatus(WIN.ordinal());
        game.setTimeLWP(game.getTimeLWP() - 1);
    }

    public static void setPassLevel(GameAttribute game) {
        game.setStatus(PASS_LEVEL.ordinal());
        game.setTimeLWP(game.getTimeLWP() - 1);
    }

    public static void setPlay(GameAttribute game) {
        game.setStatus(PLAY.ordinal());
        game.setTimeLWP(GameAttribute.TIME_LWP);
    }
}
