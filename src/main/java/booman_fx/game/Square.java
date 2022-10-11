package booman_fx.game;

import booman_fx.Enum.TypeSprite;

import static booman_fx.Enum.TypeSprite.*;

public class Square {
    boolean[] typeSprite;

    public boolean getTypeSprite(TypeSprite type) {
        return typeSprite[type.ordinal()];
    }

    public Square() {
        typeSprite = new boolean[TypeSprite.values().length]; //Tạo 1 mảng bool có số ptu = số ptu của TypeSprite.enum
    }

    public void add(TypeSprite type) {
        typeSprite[type.ordinal()] = true;
    }

    public void remove(TypeSprite type) {
        typeSprite[type.ordinal()] = false;
    }

    public boolean checkEmpty() {
        for (int i = 1; i < TypeSprite.values().length; ++i) {
            if (i != BACKGROUND.ordinal() && typeSprite[i]) {
                return false;
            }
        }
        return true;
    }
}
