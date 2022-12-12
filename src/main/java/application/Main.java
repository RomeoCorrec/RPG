package application;

import Fenetre.FenetrePrincipale;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {
            FenetrePrincipale FP = new FenetrePrincipale();
            primaryStage = FP.getStage();
            primaryStage.show();
        } catch(Exception e) {e.printStackTrace();}
        }



        public static void main (String[]args){

            launch(args);
        }

}