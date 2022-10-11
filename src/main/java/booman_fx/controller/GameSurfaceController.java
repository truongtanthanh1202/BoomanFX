package booman_fx.controller;

import booman_fx.game.App;
import booman_fx.game.GameState;
import booman_fx.game.Images;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSurfaceController implements Initializable {
    private final Images[] btnHome = Images.iconHome;

    private final Images[] btnPause = Images.iconPause;
    private final Images[] btnContinue = Images.iconContinue;

    private final Images[] btnOnSoundEffect = Images.iconOnSoundEffect;
    private final Images[] btnOffSoundEffect = Images.iconOffSoundEffect;

    private final Images[] btnOnMusic = Images.iconOnMusic;
    private final Images[] btnOffMusic = Images.iconOffMusic;

    @FXML
    private ImageView imgHome, imgOnMusic, imgOffMusic, imgOnSoundEffect, imgOffSoundEffect, imgPause, imgContinue;

    @FXML
    AnchorPane objectContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgHome.setImage(btnHome[0].getImage());
        imgOnMusic.setImage(btnOnMusic[0].getImage());
        imgOnSoundEffect.setImage(btnOnSoundEffect[0].getImage());
        imgPause.setImage(btnPause[0].getImage());

        imgContinue.setVisible(false);
        imgOffSoundEffect.setVisible(false);
        imgOffMusic.setVisible(false);

        try {
            objectContainer.getChildren().add(App.gameAttribute.getSceneSprites());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clickImgHome(MouseEvent mouseEvent) {
        App.setRoot("Menu");
    }

    public void enterImgHome(MouseEvent mouseEvent) {
        imgHome.setImage(btnHome[1].getImage());
    }

    public void exitImgHome(MouseEvent mouseEvent) {
        imgHome.setImage(btnHome[0].getImage());
    }

    public void clickimgPause(MouseEvent mouseEvent) {
        imgContinue.setImage(btnContinue[1].getImage());
        imgContinue.setVisible(true);
        imgPause.setVisible(false);
    }

    public void enterimgPause(MouseEvent mouseEvent) {
        imgPause.setImage(btnPause[1].getImage());
    }

    public void exitimgPause(MouseEvent mouseEvent) {
        imgPause.setImage(btnPause[0].getImage());
    }

    public void clickimgOnSoundEffect(MouseEvent mouseEvent) {
        imgOffSoundEffect.setImage(btnOffSoundEffect[1].getImage());
        imgOffSoundEffect.setVisible(true);
        imgOnSoundEffect.setVisible(false);
//        App.gameAttribute.getSoundEffectGame().pause();
    }

    public void enterimgOnSoundEffect(MouseEvent mouseEvent) {
        imgOnSoundEffect.setImage(btnOnSoundEffect[1].getImage());
    }

    public void exitimgOnSoundEffect(MouseEvent mouseEvent) {
        imgOnSoundEffect.setImage(btnOnSoundEffect[0].getImage());
    }

    public void clickimgOnMusic(MouseEvent mouseEvent) {
        imgOffMusic.setImage(btnOffMusic[1].getImage());
        imgOffMusic.setVisible(true);
        imgOnMusic.setVisible(false);
//        App.gameAttribute.getMusicGame().pause();
    }

    public void enterimgOnMusic(MouseEvent mouseEvent) {
        imgOnMusic.setImage(btnOnMusic[1].getImage());
    }

    public void exitimgOnMusic(MouseEvent mouseEvent) {
        imgOnMusic.setImage(btnOnMusic[0].getImage());
    }

    public void clickimgContinue(MouseEvent mouseEvent) {
        imgContinue.setVisible(false);
        imgPause.setVisible(true);
    }

    public void enterimgContinue(MouseEvent mouseEvent) {
        imgContinue.setImage(btnContinue[1].getImage());
    }

    public void exitimgContinue(MouseEvent mouseEvent) {
        imgContinue.setImage(btnContinue[0].getImage());
    }

    public void clickimgOffMusic(MouseEvent mouseEvent) {
        imgOffMusic.setVisible(false);
        imgOnMusic.setVisible(true);
//        App.gameAttribute.getMusicGame().play();
    }

    public void enterimgOffMusic(MouseEvent mouseEvent) {
        imgOffMusic.setImage(btnOffMusic[1].getImage());
    }

    public void exitimgOffMusic(MouseEvent mouseEvent) {
        imgOffMusic.setImage(btnOffMusic[0].getImage());
    }

    public void clickimgOffSoundEffect(MouseEvent mouseEvent) {
        imgOffSoundEffect.setVisible(false);
        imgOnSoundEffect.setVisible(true);
//        App.gameAttribute.getSoundEffectGame().play();
    }

    public void enterimgOffSoundEffect(MouseEvent mouseEvent) {
        imgOffSoundEffect.setImage(btnOffSoundEffect[1].getImage());
    }

    public void exitimgOffSoundEffect(MouseEvent mouseEvent) {
        imgOffSoundEffect.setImage(btnOffSoundEffect[0].getImage());
    }
}
