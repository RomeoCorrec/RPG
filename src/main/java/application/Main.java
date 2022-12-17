package application;

import ElementsJeu.Game;
import Fenetre.FenetrePrincipale;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConsoleParser;
import utils.InputParser;

import java.io.IOException;
import java.lang.reflect.Type;

public class Main extends Application {
    InputParser parser = new ConsoleParser();
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {

        int choix;
        parser.affichageTexte("Choisissez le mode de jeu");
        parser.affichageTexte("1. Console");
        parser.affichageTexte("2. interface graphique");
        do {
            choix = parser.recuperationInt();
        } while (choix != 1 && choix != 2);

        switch (choix) {
            case 1:
                Game.main(new String[]{});
                break;
            case 2:
                try {
                    FenetrePrincipale FP = new FenetrePrincipale();
                    primaryStage = FP.getStage();
                    primaryStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

    }



        public static void main (String[]args){

            launch(args);
        }

}