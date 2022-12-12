package application;

import Fenetre.FenetreDemarrage;
import Fenetre.FenetrePrincipale;
import javafx.application.Application;

// import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {
            FenetreDemarrage fd = new FenetreDemarrage();
            primaryStage = fd.getStage();
            primaryStage.show();
        } catch(Exception e) {e.printStackTrace();}
    }



    public static void main(String[] args) {

        launch(args);
    }
}
