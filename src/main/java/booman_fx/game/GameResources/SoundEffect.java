package booman_fx.game.GameResources;

import booman_fx.engine.Audio;
import javafx.scene.media.MediaPlayer;
import java.util.Objects;

public class SoundEffect extends Audio {
    public SoundEffect(String path) {
        super(path);
    }

    @Override
    public void play() {
        mediaPlayer = new MediaPlayer(Objects.requireNonNull(media));
        mediaPlayer.setVolume(0.3);
        super.play();
    }
}
