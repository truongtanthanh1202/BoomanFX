package booman_fx.objects;

import booman_fx.engine.Sprite;
import booman_fx.game.App;
import booman_fx.game.GameResources.Images;

import java.util.Date;

import static booman_fx.Enum.TypeSprite.PORTAL;

public class Portal extends Sprite {
    private static final Images[] PORTAL_IMAGE = Images.portal;

    public static void createPortal(int realX, int realY){
        App.gameAttribute.spawn(new Portal(realX, realY));
    }
    protected Portal(int realX, int realY) {
        super(Images.portal[0].getImage(), realX, realY, PORTAL);
    }

    @Override
    public void update() {
        setImage(PORTAL_IMAGE[(int) (new Date().getTime() / 100) % 3 + 2].getImage());
    }
}
