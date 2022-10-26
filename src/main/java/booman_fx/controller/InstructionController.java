package booman_fx.controller;

import booman_fx.game.GameResources.Images;
import booman_fx.game.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructionController implements Initializable {
    private final Images[] buttonBack = Images.buttonBack;
    private final Images[] buttonPowerUp = Images.buttonContinue;
    @FXML
    ImageView imageBack, imagePowerUp;

    @FXML
    private void enterImagePowerUp() {
        imagePowerUp.setImage(buttonPowerUp[1].getImage());
    }

    @FXML
    private void exitImagePowerUp() {
        imagePowerUp.setImage(buttonPowerUp[0].getImage());
    }

    @FXML
    private void clickImagePowerUp() {
        App.setRoot("Instruction2");
    }

    @FXML
    private void enterImageBack() {
        imageBack.setImage(buttonBack[1].getImage());
    }

    @FXML
    private void exitImageBack() {
        imageBack.setImage(buttonBack[0].getImage());
    }

    @FXML
    private void clickImageBack() {
        App.setRoot("Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageBack.setImage(buttonBack[0].getImage());
        imagePowerUp.setImage(buttonPowerUp[0].getImage());
    }

}
