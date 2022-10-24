package booman_fx.controller;

import booman_fx.game.GameResources.Images;
import booman_fx.game.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Instruction2Controller implements Initializable {
    private final Images[] buttonControl = Images.buttonBack;

    @FXML
    ImageView imageControl;

    @FXML
    private void enterImageControl() {
        imageControl.setImage(buttonControl[1].getImage());
    }

    @FXML
    private void exitImageControl() {
        imageControl.setImage(buttonControl[0].getImage());
    }

    @FXML
    private void clickImageControl() {
        App.setRoot("Instruction");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageControl.setImage(buttonControl[0].getImage());
    }
}
