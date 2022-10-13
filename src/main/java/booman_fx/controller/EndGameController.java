package booman_fx.controller;

import booman_fx.game.App;
import booman_fx.game.Images;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {
    private final Images[] buttonNewGame = Images.buttonNewGame;
    private final Images[] imageStatus = Images.imageLWP;

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
        App.setRoot("ChooseMap");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageNewGame.setImage(buttonNewGame[0].getImage());
        imgStatus.setImage(imageStatus[0].getImage());
    }
}
