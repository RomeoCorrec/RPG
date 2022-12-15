package Fenetre;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class fenetreVictoire {

    private fenetreVictoire FV;
    private AnchorPane popAnchor;
    private Stage popStage;
    private Scene popScene;
    private Stage menuStage;
    private static final int G_WIDTH = 900;
    private static final int G_HEIGHT = 500;

    public fenetreVictoire(HBox hbox) {
        popAnchor = new AnchorPane();
        popAnchor.getChildren().add(hbox);
        AnchorPane.setBottomAnchor(hbox, 25.);
        popScene = new Scene(popAnchor, G_WIDTH, G_HEIGHT);
        popStage = new Stage();
        setBackground();
        popStage.setScene(popScene);
    }

    private void setBackground() {
        Image imageBackground = new Image(getClass().getResourceAsStream("/images/imageVictoire.png"));
        BackgroundImage background = new BackgroundImage(imageBackground, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        popAnchor.setBackground(new Background(background));
    }

    public void creationFenetreVictoire(Stage menuStage, fenetreVictoire Popup) {
        this.menuStage = menuStage;
        this.FV = Popup;
        //createBackgroud();
        //gameLoop();

        popStage.show();
        this.menuStage.close();

    }
}
