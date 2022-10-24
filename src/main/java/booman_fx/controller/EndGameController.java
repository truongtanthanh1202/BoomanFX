package booman_fx.controller;

import booman_fx.game.App;
import booman_fx.game.Images;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static booman_fx.Enum.StatusGame.*;

public class EndGameController implements Initializable {
    private final Images[] buttonNewGame = Images.buttonNewGame;
    private final Images[] imageStatus = Images.imageStatus;

    @FXML
    ImageView imageNewGame, imgStatus;

    @FXML
    private void enterImageNewGame() {
        imageNewGame.setImage(buttonNewGame[1].getImage());
    }

    @FXML
    private void exitImageNewGame() {
        imageNewGame.setImage(buttonNewGame[0].getImage());
    }

    @FXML
    private void clickImageNewGame() {
        App.gameAttribute.setStatus(PLAY.ordinal());
        App.setRoot("Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageNewGame.setImage(buttonNewGame[0].getImage());
        if (App.gameAttribute.statusProperty().getValue() == LOSS.ordinal()) {
            imgStatus.setImage(imageStatus[0].getImage());
        } else if (App.gameAttribute.statusProperty().getValue() == WIN.ordinal()) {
            imgStatus.setImage(imageStatus[1].getImage());
        } else {
            imgStatus.setImage(imageStatus[0].getImage());
        }
    }
}
