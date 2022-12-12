package com.example.demo2;

import Fenetre.FenetrePrincipale;
import Fenetre.fenetreJeu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class HelloApplication extends Application {

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