package booman_fx.Enum;

import booman_fx.objects.Character.Character;

public enum StatusCharacter {
        STUNNED, MOVE, IMMORTAL;

        public static void setStunned(Character character) {
            character.setStatus(STUNNED);
            character.setStunnedTime(Character.STUNNED_TIME);
        }

        public static void setMove(Character character) {
            character.setStatus(MOVE);
        }

        public static void setImmortal(Character character) {
            character.setStatus(IMMORTAL);
            character.setImmortalTime(Character.IMMORTAL_TIME);
        }
}
