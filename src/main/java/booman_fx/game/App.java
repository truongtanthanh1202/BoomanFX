package booman_fx.game;

import booman_fx.engine.GameAttribute;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    public static GameAttribute gameAttribute;
    public static Scene scene;

    @Override
    public void start(Stage stage) {
        //scene dùng để load file Menu.fxml
        scene = new Scene(Objects.requireNonNull(loadFXML("Menu")));
        stage.setTitle("BoomanFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        launch();
    }
}
