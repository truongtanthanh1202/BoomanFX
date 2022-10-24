package booman_fx.controller;

import booman_fx.game.App;
import booman_fx.game.Images;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import booman_fx.objects.Character.Character;

import java.net.URL;
import java.util.ResourceBundle;

import static booman_fx.Enum.StatusGame.*;

public class GameSurfaceController implements Initializable {
    private final Images[] btnHome = Images.iconHome;

    private final Images[] btnPause = Images.iconPause;
    private final Images[] btnContinue = Images.iconContinue;

    private final Images[] btnOnSoundEffect = Images.iconOnSoundEffect;
    private final Images[] btnOffSoundEffect = Images.iconOffSoundEffect;

    private final Images[] btnOnMusic = Images.iconOnMusic;
    private final Images[] btnOffMusic = Images.iconOffMusic;

    private final Images[] imgStatus = Images.imageStatus;

    @FXML
    private ImageView imgPlayer, imgHome, imgOnMusic, imgOffMusic, imgOnSoundEffect, imgOffSoundEffect, imgPause, imgContinue, imageStatusGame;

    @FXML
    Label labelLevelGame, labelTimeLeft, labelLive, labelBoomPower, labelBooms, labelSpeed;

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

        setInfoItem(App.gameAttribute.getPlayer());

        labelLevelGame.setText("Level: " + App.gameAttribute.getLevel());
        labelTimeLeft.setText("300"); // Sửa lại để count down về 1
        App.gameAttribute.getTimeLeft().addListener((observableValue, oldValue, newValue) ->
        {
            labelTimeLeft.setText("" + (int) newValue/ App.gameAttribute.getFramesPerSecond());
        });

        try {
            imgPlayer.setImage(App.gameAttribute.getPlayer().getImage());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(App.gameAttribute.getPlayer());
        }

        try {
            objectContainer.getChildren().add(App.gameAttribute.getSceneSprites());
        } catch (Exception e) {
            System.out.println(e);
        }

        App.gameAttribute.statusProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.intValue() != PLAY.ordinal() && newValue.intValue() != PAUSE.ordinal()) {
                imageStatusGame.setImage(imgStatus[newValue.intValue()].getImage());
            }
        });
    }

    private void setInfoItem(Character character) {
        labelLive.setText(String.valueOf(character.livesProperty().getValue()));
        labelBooms.setText(String.valueOf(character.numBombProperty().getValue()));
        labelBoomPower.setText(String.valueOf(character.powerBombProperty().getValue()));
        labelSpeed.setText(String.valueOf(character.powerSpeedProperty().getValue()));
        character.livesProperty().addListener((observableValue, oldValue, newValue) -> labelLive.setText(String.valueOf(newValue)));
        character.numBombProperty().addListener((observableValue, oldValue, newValue) -> labelBooms.setText(String.valueOf(newValue)));
        character.powerBombProperty().addListener((observableValue, oldValue, newValue) -> labelBoomPower.setText(String.valueOf(newValue)));
        character.powerSpeedProperty().addListener((observableValue, oldValue, newValue) -> labelSpeed.setText(String.valueOf(newValue)));
    }

    public void clickImgHome(MouseEvent mouseEvent) {
        App.gameAttribute.shutdown();
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
        App.gameAttribute.pause();
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
        try {
            App.gameAttribute.getSoundEffectGame().pause();
            App.gameAttribute.getSoundEffectGame().setStatus(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(App.gameAttribute.getSoundEffectGame().isStatus());
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
        try {
            App.gameAttribute.getMusicGame().pause();
        } catch (Exception e) {
            System.out.println(e);
        }
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
        App.gameAttribute.begin();
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
        try {
            App.gameAttribute.getMusicGame().play();
        } catch (Exception e) {
            System.out.println(e);
        }

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
        try {
            App.gameAttribute.getSoundEffectGame().setStatus(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(App.gameAttribute.getSoundEffectGame().isStatus());
    }

    public void enterimgOffSoundEffect(MouseEvent mouseEvent) {
        imgOffSoundEffect.setImage(btnOffSoundEffect[1].getImage());
    }

    public void exitimgOffSoundEffect(MouseEvent mouseEvent) {
        imgOffSoundEffect.setImage(btnOffSoundEffect[0].getImage());
    }
}
