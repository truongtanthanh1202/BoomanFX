package booman_fx.controller;

import booman_fx.Enum.TypePlayer;
import booman_fx.game.GameResources.Images;
import booman_fx.game.App;
import booman_fx.objects.Character.Player.Player;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static booman_fx.Enum.Direction.DOWN;

public class ChooseCharacterController implements Initializable {
    private final Images[] buttonContinue = Images.buttonContinue;
    private final Images[] buttonBack = Images.buttonBack;

    @FXML
    AnchorPane anchorPane;

    @FXML
    ImageView imagePlayer, imageContinue, imageBack;

    @FXML
    private void enterImageContinue() {
        imageContinue.setImage(buttonContinue[1].getImage());
    }

    @FXML
    private void exitImageContinue() {
        imageContinue.setImage(buttonContinue[0].getImage());
    }

    @FXML
    private void clickImageContinue() {
        App.setRoot("GameSurface");
        App.gameAttribute.begin();
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
        App.setRoot("ChooseMap");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageContinue.setImage(buttonContinue[0].getImage());
        imageBack.setImage(buttonBack[0].getImage());
        imagePlayer.setImage(Images.boomer[Player.type.ordinal()][DOWN.ordinal()][0].getImage());

        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 4; ++j) {
                ImageView imageView = new ImageView(Images.boomer[i * 4 + j][DOWN.ordinal()][0].getImage());
                imageView.setTranslateX(250 + j * 100);
                imageView.setTranslateY(120 + i * 120);
                imageView.setFitHeight(90);
                imageView.setFitWidth(60);

                TypePlayer type = TypePlayer.values()[i * 4 + j];
                imageView.setOnMouseClicked(mouseEvent -> {
                    Player.type = type;
                    imagePlayer.setImage(Images.boomer[Player.type.ordinal()][DOWN.ordinal()][0].getImage());
                });

                anchorPane.getChildren().add(imageView);
            }
        }
    }
}
