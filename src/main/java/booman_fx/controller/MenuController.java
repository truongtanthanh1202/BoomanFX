package booman_fx.controller;

import booman_fx.game.App;
import booman_fx.game.GameState;
import booman_fx.game.Images;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private final Images[] buttonStart = Images.buttonStart;
    private final Images[] buttonInstruct = Images.buttonInstruct;

    @FXML
    private ImageView imageStart, imageInstruct;

    @FXML
    private void enterImageInstruct() {
        imageInstruct.setImage(buttonInstruct[1].getImage());
    }

    @FXML
    private void exitImageInstruct() {
        imageInstruct.setImage(buttonInstruct[0].getImage());
    }

    @FXML
    private void clickImageInstruct() {
        App.setRoot("Instruction");
    }

    @FXML
    private void enterImageStart() {
        imageStart.setImage(buttonStart[1].getImage());
    }

    @FXML
    private void exitImageStart() {
        imageStart.setImage(buttonStart[0].getImage());
    }

    @FXML
    private void clickImageStart() {
        GameState.initial();
        App.setRoot("ChooseMap");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageStart.setImage(buttonStart[0].getImage());
        imageInstruct.setImage(buttonInstruct[0].getImage());
        GameState.startSound();

        try {
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/booman_fx/map/level-map.txt");
            String level = "level: 5\n";
            outputStream.write(level.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
