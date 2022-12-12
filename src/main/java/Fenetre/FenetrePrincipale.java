package Fenetre;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.scene.control.Alert.AlertType;
public class FenetrePrincipale {

    private AnchorPane mainPane;
    private static final String BACKGROUND_IMAGE = "/images/herbe.jpg";
    private Stage mainStage;
    private Scene mainScene;
    private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
    private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
    private ArrayList<MenuButton> boutonClasse = new ArrayList<MenuButton>();
    private ArrayList<String> listeClasse = new ArrayList<String>();
    private Label l_NbJoueurs;
    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private AnimationTimer gameTimer;

    public FenetrePrincipale() {
        initialisationFenetrePrincipale();

    }

    private void initialisationFenetrePrincipale() {
        mainPane = new AnchorPane();
        createBackgroud();

        VBox root = new VBox();
        //MotionBlur motionBlur = new MotionBlur();
        //motionBlur.setRadius(100);
        //motionBlur.setAngle(40);
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(5);
        l_NbJoueurs = new Label("Noms des joueurs (2 minimum) :");
        l_NbJoueurs.setTextFill(Color.web("#5F0404"));
        ImageView imageView1=new ImageView();
        imageView1.setFitHeight(500);
        imageView1.setFitWidth(600.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);


        imageView1.setImage(new Image(getClass().getResourceAsStream("/images/Titre.png")));

        root.getChildren().add(imageView1);
        VBox.setMargin((imageView1), new Insets(30,0,0,100));

        GridPane grilleJoueur = new GridPane();
        grilleJoueur.setPadding(new Insets(10));
        grilleJoueur.setVgap(30);
        grilleJoueur.setHgap(25);
        VBox.setMargin((grilleJoueur), new Insets(100, 10, 10, 100));

        grilleJoueur.add(l_NbJoueurs, 0,0, 3, 1);

        final Color shadowColor = Color.BLACK.deriveColor(0, 0, 0, 1);
        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, shadowColor, 2, 1, 0,0);
        grilleJoueur.setEffect(dropShadow);

        for(int i=0; i<4; i++) {
            listeJoueurs.add(new TextField(i<2?"Héro"+(i+1):""));
            //listeJoueurs.get(i).setEffect(dropShadow);
            listeJoueurs.get(i).setPromptText("Nom du héro "+(i+1));
            listeJoueurs.get(i).setPrefWidth(250);

            boutonClasse.add(new MenuButton("classe du héro"));
            //boutonClasse.get(i).setEffect(dropShadow);
            MenuItem class1 = new MenuItem("Warrior");
            MenuItem class2 = new MenuItem("Mage");
            MenuItem class3 = new MenuItem("Hunter");
            MenuItem class4 = new MenuItem("Healer");
            boutonClasse.get(i).getItems().addAll(class1, class2, class3, class4);
            //listeJoueurs.get(i).setMaxWidth();

            int finalI = i;
            class1.setOnAction(event -> {
                boutonClasse.get(finalI).setText(class1.getText());
            });

            class2.setOnAction(event -> {
                boutonClasse.get(finalI).setText(class2.getText());
            });

            class3.setOnAction(event -> {
                boutonClasse.get(finalI).setText(class3.getText());


            });

            class4.setOnAction(event -> {
                boutonClasse.get(finalI).setText(class4.getText());
            });

            grilleJoueur.add(listeJoueurs.get(i),0, i+1, 3, 1);
            grilleJoueur.add(boutonClasse.get(i), 4, i+1);
        }

        root.getChildren().add(grilleJoueur);
        grilleJoueur.setAlignment(Pos.CENTER);
        Button b_Valider = new Button("Valider");
        b_Valider.setDefaultButton(true);
        VBox.setMargin((b_Valider), new Insets(10,0,10,350));

        b_Valider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                fenetreJeu jeu = new fenetreJeu();
                ArrayList<String> nomHeros = new ArrayList<>();
                boolean peuxLancer = true;
                int nbrJoueurs = 0;
                for(int i = 0; i<4; i++) {
                    if (listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty() && boutonClasse.get(i).getText().equals("classe du héro")) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Classe(s) non définie(s)");
                        alert.setContentText("veuillez renseigner les classes de vos héros");
                        peuxLancer = false;
                        alert.showAndWait();

                    }
                    else if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty()) {
                        nomHeros.add(listeJoueurs.get(i).getText());
                        listeClasse.add(boutonClasse.get(i).getText());
                        nbrJoueurs++;
                    }
                }
                if (peuxLancer) {
                    System.out.println(nomHeros);
                    jeu.creationFenetreJeu(mainStage, jeu);
                    try {
                        jeu.setPartie(nbrJoueurs, nomHeros, listeClasse);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });


        root.getChildren().add(b_Valider);
        b_Valider.setEffect(dropShadow);
        mainPane.getChildren().add(root);

        gameLoop();

        mainScene = new Scene(mainPane,WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
    }

    private void createBackgroud() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backGroundImage1 = new ImageView(new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE)));
            ImageView backGroundImage2 = new ImageView(new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE)));
            GridPane.setConstraints(backGroundImage1, i% 3, i / 3);
            GridPane.setConstraints(backGroundImage1, i% 3, i / 3);
            gridPane1.getChildren().add(backGroundImage1);
            gridPane2.getChildren().add(backGroundImage2);

        }
        gridPane2.setLayoutY(-1024);

        mainPane.getChildren().addAll(gridPane1, gridPane2);

    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

    private void gameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                moveBackground();
            }
        };

        gameTimer.start();
    }

    public Stage getStage() {
        return mainStage;
    }

}
