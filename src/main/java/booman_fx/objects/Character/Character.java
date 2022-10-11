package booman_fx.objects.Character;

import booman_fx.Enum.TypeSprite;
import booman_fx.engine.Sprite;
import booman_fx.game.Images;
import javafx.scene.image.Image;

import static booman_fx.Enum.Direction.*;

public abstract class Character extends Sprite {
    private final Images[][] imageCharacter;

    public Character(Image image, int xInMap, int yInMap, TypeSprite typeSprite, Images[][] imageCharacter) {
        super(image, xInMap, yInMap, typeSprite);
        this.imageCharacter = imageCharacter;
    }


    public Image getImageInfo() {
        return imageCharacter[DOWN.ordinal()][0].getImage();
    }
}
