package booman_fx.engine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Objects;

public class Audio {
    protected MediaPlayer mediaPlayer;
    protected Media media;
    boolean status;

    public Audio(String path) {
        File mediaFile = new File(path);
        try {
            media = new Media(mediaFile.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mediaPlayer = new MediaPlayer(Objects.requireNonNull(media));
        status = true;
    }

    public void play() {
        mediaPlayer.play();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
