module bom_it.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens booman_fx.game to javafx.fxml;
    opens booman_fx.engine to javafx.fxml;
    opens booman_fx.controller to javafx.fxml;
//    opens booman_fx.objects to javafx.fxml;
    exports booman_fx.game;
    exports booman_fx.engine;
    exports booman_fx.controller;
    exports booman_fx.game.Manager;
    opens booman_fx.game.Manager to javafx.fxml;
    exports booman_fx.game.GameResources;
    opens booman_fx.game.GameResources to javafx.fxml;
//    exports booman_fx.objects;
//    exports booman_fx.Enum;
//    exports booman_fx.objects.Character.Enemy.Enemy;
//    opens booman_fx.objects.Character.Enemy.Enemy to javafx.fxml;
//    exports booman_fx.objects.Item;
//    opens booman_fx.objects.Item to javafx.fxml;
//    exports booman_fx.objects.Character;
//    opens booman_fx.objects.Character to javafx.fxml;
}