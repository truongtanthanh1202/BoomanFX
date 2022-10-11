package booman_fx.game.GameResources;

import booman_fx.engine.Audio;
import javafx.scene.media.MediaPlayer;

public class GameMusic extends Audio {
    public GameMusic(String path) {
        super(path);
    }

    @Override
    public void play() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.2);
        super.play();
    }
}
