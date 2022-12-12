package Fenetre;

import java.util.ArrayList;

import Fenetre.FenetrePrincipale;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FenetreDemarrage {
    private VBox root;
    private Label l_NbJoueurs;
    private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
    private Button b_Valider;
    private int choix = 0;
    private Button btn;
    private Scene mainScene;
    private AnchorPane anchorPaneRoot;
    private Stage mainStage;
    private static final int Height = 600;
    private static final int Width = 800;
    public FenetreDemarrage() {

        anchorPaneRoot = new AnchorPane();
        mainScene = new Scene(anchorPaneRoot, Width, Height);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        root = new VBox();

        //initRoot();
        Scene scene = new Scene(root, 400,190);


        //stage.setScene(scene);

        // stage.setOnHiding(new EvtQuitter());
    }

    private void initRoot() {
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(5);
        // root.resize(884, 495);

        l_NbJoueurs = new Label("Noms des joueurs (2 minimum) :");


        VBox vBox1 = new VBox();
        vBox1.setPrefHeight(100.0);
        vBox1.setPrefWidth(100.);
        vBox1.setStyle("-fx-background-color: #005395");
        vBox1.setBlendMode(BlendMode.SRC_OVER);
        vBox1.setDepthTest(DepthTest.INHERIT);
        vBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        vBox1.setCenterShape(true);

        ImageView imageView1 = new ImageView();
        imageView1.setFitHeight(500.0);
        imageView1.setFitWidth(500.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);

        VBox.setMargin((imageView1), new Insets(10, 0, 0, 170));

        vBox1.getChildren().add(imageView1);


        root.getChildren().add(vBox1);


        root.getChildren().add(l_NbJoueurs);
        for (int i = 0; i < 4; i++) {
            listeJoueurs.add(new TextField(i < 2 ? "Joueur" + (i + 1) : ""));
            listeJoueurs.get(i).setPromptText("Nom du joueur " + (i + 1));
            root.getChildren().add(listeJoueurs.get(i));
        }
        /*
        b_Valider = new Button("Valider");
        b_Valider.setOnAction(new EvtValider());
        b_Valider.setDefaultButton(true);
        b_Valider.setOnAction(new EvtValider());

        root.getChildren().add(b_Valider);
        */


        VBox vBox2 =new VBox();
        vBox2.setPrefHeight(100.0);
        vBox2.setPrefWidth(100.);
        vBox2.setStyle("-fx-background-color: #005395");
        vBox2.setBlendMode(BlendMode.SRC_OVER);
        vBox2.setDepthTest(DepthTest.INHERIT);
        vBox2.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        vBox2.setCenterShape(true);

        root.getChildren().add(vBox2);
    }




        public Stage getStage () {
            return mainStage;
        }
    /*
    private class EvtValider implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ArrayList<String> champs = new ArrayList<String>();
            for(int i=0; i<4; i++) {
                if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
                    champs.add(listeJoueurs.get(i).getText());
            }
            if(champs.size()>=2) {
                choix = 1;
                fp.setPartie(champs.size(), champs);
                fp.getStage().show();
                stage.close();
            }
            event.consume();
        }
    }
    */

        /*
    private class EvtQuitter implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent event) {
            if(choix == 0)
                System.exit(0);
            event.consume();
        }
    }
    */
}
