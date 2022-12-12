package Fenetre;

import ElementsJeu.*;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import jeudeplateau.RepPersonnages;

import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.web;


public class fenetreJeu {

    private fenetreJeu FJ;
    private static final int G_WIDTH = 1000;
    private static final int G_HEIGHT = 800;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private Stage menuStage;

    private AnimationTimer gameTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private Label textDialogue;
    private final static String BACKGROUND_IMAGE = "src\\main\\java\\images\\herbe.jpg";
    private ArrayList<Hero> listeHero;
    private int heroActuel;
    private int EnemyAttaquant;
    private ArrayList<Enemy> listeEnnemy;
    private ArrayList<Combatant> tableauCombatants;
    ArrayList<Combatant> TableauMorts;

    private HBox HBhero;
    private HBox HBennemy;
    private HBox HBsubscene;

    private final static String imageGuerrier = "/images/chevalier.png";
    private final static String imageMage = "/images/mage.png";
    private final static String imageHealer = "/images/Healer.png";
    private final static String imageHunter = "/images/Archer.png";
    private final static String imageEnemy = "/images/Enemy.png";
    private final static String imageFondDialogue = "/images/FontTexteParchemin (2).jpg";


    public fenetreJeu() {
        initialisationStage();
    }

    private void initialisationStage() {
        gamePane = new AnchorPane();

        HBhero = new HBox();
        HBhero.setSpacing(10.);
        AnchorPane.setTopAnchor(HBhero, 200.);
        AnchorPane.setLeftAnchor(HBhero, 75.);
        gamePane.getChildren().add(HBhero);

        HBennemy = new HBox();
        AnchorPane.setTopAnchor(HBennemy, 10.);
        AnchorPane.setLeftAnchor(HBennemy, 520.);
        gamePane.getChildren().add(HBennemy);

        gameScene = new Scene(gamePane, G_WIDTH, G_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

        Image imageDialogue = new Image(getClass().getResourceAsStream(imageFondDialogue));
        ImageView imageViewDialogue = new ImageView(imageDialogue);
        AnchorPane.setLeftAnchor(imageViewDialogue, 50.);
        AnchorPane.setTopAnchor(imageViewDialogue, 500.);
        gamePane.getChildren().add(imageViewDialogue);

        textDialogue = new Label();
        Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
        textDialogue.setFont(font);
        AnchorPane.setLeftAnchor(textDialogue, 100.);
        AnchorPane.setTopAnchor(textDialogue, 550.);
        gamePane.getChildren().add(textDialogue);


        HBsubscene = new HBox();
        //HBsubscene.setPrefHeight(300);
        //HBsubscene.setPrefWidth(300);
        AnchorPane.setTopAnchor(HBsubscene, 500.);
        AnchorPane.setLeftAnchor(HBsubscene, 650.);

        HBsubscene.setAlignment(Pos.CENTER);
        HBsubscene.setBackground(new Background(new BackgroundFill(web("#11F1E0"), new CornerRadii(0), new Insets(0))));
        gamePane.getChildren().add(HBsubscene);
        //subSceneAction = new subSceneAction();
        //subSceneAction.setLayoutX(gamePane.getWidth()- 450);
        //subSceneAction.setLayoutY(gamePane.getHeight() - 300);
        //grilleBouton menuAction = new MenuAction(HBsubscene, FJ.getListeEnnemy().size());
    }

    public void creationFenetreJeu(Stage menuStage, fenetreJeu FJ) {
        this.menuStage = menuStage;
        this.FJ = FJ;
        //createBackgroud();
        //gameLoop();

        gameStage.show();
        this.menuStage.close();

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

    private void createBackgroud() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            Image image = new Image(this.getClass().getResourceAsStream("com/example/application/images/herbe.jpg"));
            ImageView backGroundImage1 = new ImageView(image);
            ImageView backGroundImage2 = new ImageView(image);
            GridPane.setConstraints(backGroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backGroundImage1, i % 3, i / 3);
            gridPane1.getChildren().add(backGroundImage1);
            gridPane2.getChildren().add(backGroundImage2);

        }
        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);

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

    public void setPartie(int nbrJoueurs, ArrayList<String> nomJoueurs, ArrayList<String> classJoueurs) throws InterruptedException {
        listeHero = new ArrayList<>();
        listeEnnemy = new ArrayList<>();
        //imageHeros = new ArrayList<>();
        for (int i = 0; i < nbrJoueurs; i++) {
            String classHero = classJoueurs.get(i);
            String nomHero = nomJoueurs.get(i);
            switch (classHero) {
                case "Warrior":
                    listeHero.add(new Warrior(nomHero));
                    //imageHeros.add(new ImageView(imageGuerrier));
                    HBhero.getChildren().add(new RepPersonnages(imageGuerrier));
                    break;
                case "Mage":
                    listeHero.add(new Mage(nomHero));
                    HBhero.getChildren().add(new RepPersonnages(imageMage));
                    break;
                case "Healer":
                    listeHero.add(new Healer(nomHero));
                    HBhero.getChildren().add(new RepPersonnages(imageHealer));
                    break;
                case "Hunter":
                    listeHero.add(new Hunter(nomHero));
                    HBhero.getChildren().add(new RepPersonnages(imageHunter));
                    break;
            }
            listeEnnemy.add(new Enemy("enemy " + i));
            HBennemy.getChildren().add(new RepPersonnages(imageEnemy));
            Random rd = new Random();
            heroActuel = rd.nextInt(listeHero.size());
            EnemyAttaquant = rd.nextInt(listeEnnemy.size());
            TableauMorts = new ArrayList<>();
        }

        grilleBouton GB = new grilleBouton();
        GB.setVgap(10);
        if (!(listeHero.get(heroActuel) instanceof Healer)) {
            Button attaqueButon = new Button("Actions");
            attaqueButon.setPrefWidth(200);
            attaqueButon.setPrefHeight(40);
            attaqueButon.setOnAction(event -> {
                chooseEnemy(GB);
            });
            GB.add(attaqueButon, 0, 0, 2, 1);
            HBsubscene.getChildren().add(GB);

            HBox.setMargin(GB, new Insets(30,30,30,30));
            HBsubscene.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        }

        else {
            Button soinButton = new Button("Soigner");
            soinButton.setPrefWidth(200);
            soinButton.setPrefHeight(40);
            soinButton.setOnAction(event -> {
                soinSolo(GB);
            });
            GB.add(soinButton, 0, 0, 2, 1);
            HBsubscene.getChildren().add(GB);
            HBsubscene.setPadding(new Insets(20, 20, 20, 20));
        }

        Button defenceBouton = new Button("Se défendre");
        defenceBouton.setPrefWidth(200);
        defenceBouton.setPrefHeight(40);
        defenceBouton.setOnAction(event -> {
            defence(GB);
            PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
            pauseTransition.setOnFinished(e -> {
                attaqueEnemy(GB);
                initGrille(GB);
            });
            pauseTransition.play();
        });
        GB.add(defenceBouton, 0, 1, 2, 1);
    }


    public void chooseEnemy(grilleBouton GB) {
        GB.getChildren().clear();
        int x = 0;
        int y = 0;
        for (Enemy E : listeEnnemy) {

            Button button = new Button(E.getName());
            button.setPrefHeight(40);
            button.setPrefWidth(200);
            int finalY = y;
            button.setOnAction(event -> {
                Attaque(GB, finalY);
            });

            GB.add(button, x, y);

            y++;
        }
    }

    public void Attaque(grilleBouton GB, int x) {



        if (listeHero.get(heroActuel).getClasse().equals("Warrior")) {
            float pdvInit = listeEnnemy.get(x).getPdVie();
            ((Warrior) listeHero.get(heroActuel)).coupDepee(((Warrior) listeHero.get(heroActuel)), listeEnnemy.get(x));
            float pdvActuel = listeEnnemy.get(x).getPdVie();
            setMessage(listeHero.get(heroActuel).getName() + " attaque " + listeEnnemy.get(x).getName() + "\n" + "et lui inflige " + (pdvInit - pdvActuel) + " points de dégats");
            Timeline task = setBarEnemy(listeEnnemy.get(x), ((RepPersonnages) HBennemy.getChildren().get(x)).getBarreDeVie());
            task.playFromStart();
            PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
            GB.setVisible(false);
            if (listeEnnemy.get(x).getPdVie() <= 0) {
                mortEnemy(GB, x);
            }
            pauseTransition.setOnFinished(e -> {
                initGrille(GB);
                GB.setVisible(true);
                attaqueEnemy(GB);
            });
            pauseTransition.play();


        }

        else if (listeHero.get(heroActuel).getClasse().equals("Mage")) {
            GB.getChildren().clear();

            Mage mage = (Mage) listeHero.get(heroActuel);

            Button sortDeFeuButton = new Button("Sort de feu");
            sortDeFeuButton.setPrefHeight(40);
            sortDeFeuButton.setPrefWidth(200);
            sortDeFeuButton.setOnAction(event -> {
                float pdvInit = listeEnnemy.get(x).getPdVie();
                mage.sortDeFeu(mage, listeEnnemy.get(x));
                float pdvActuel = listeEnnemy.get(x).getPdVie();
                setMessage(listeHero.get(heroActuel).getName() + " lance une boule de feu sur " + listeEnnemy.get(x).getName() + "\n" + " et lui inflige" + (pdvInit - pdvActuel));
                Timeline task = setBarEnemy(listeEnnemy.get(x), ((RepPersonnages) HBennemy.getChildren().get(x)).getBarreDeVie());
                task.playFromStart();
                PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
                GB.setVisible(false);
                if (listeEnnemy.get(x).getPdVie() <= 0) {
                    mortEnemy(GB, x);
                }
                pauseTransition.setOnFinished(e -> {
                    initGrille(GB);
                    GB.setVisible(true);
                    attaqueEnemy(GB);
                });
                pauseTransition.play();
            });


            Button sortDeGlaceButton = new Button("Sort de glace");
            sortDeGlaceButton.setPrefHeight(40);
            sortDeGlaceButton.setPrefWidth(200);
            sortDeGlaceButton.setOnAction(event -> {

                setMessage(listeHero.get(heroActuel).getName() + " affaiblit " + listeEnnemy.get(x).getName());
                mage.sortDeGlace(mage, listeEnnemy.get(x));
                PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
                GB.setVisible(false);
                if (listeEnnemy.get(x).getPdVie() <= 0) {
                    mortEnemy(GB, x);
                }
                pauseTransition.setOnFinished(e -> {
                    initGrille(GB);
                    GB.setVisible(true);
                    attaqueEnemy(GB);
                });
                pauseTransition.play();
            });

            GB.add(sortDeFeuButton, 0,0, 2,1);
            GB.add(sortDeGlaceButton, 0,1, 2,1);
        }

        else if (listeHero.get(heroActuel).getClasse().equals("Hunter")) {
            float pvInit = listeEnnemy.get(x).getPdVie();
            ((Hunter) listeHero.get(heroActuel)).tireAlArc(listeEnnemy.get(x), ((Hunter) listeHero.get(heroActuel)));
            float pvActuel = listeEnnemy.get(x).getPdVie();
            setMessage(listeHero.get(heroActuel).getName() + " tire une flèche sur " + listeEnnemy.get(x).getName() + "\n" + " et lui inflige " + (pvInit - pvActuel) + " point de dégats");
            Timeline task = setBarEnemy(listeEnnemy.get(x), ((RepPersonnages) HBennemy.getChildren().get(x)).getBarreDeVie());
            task.playFromStart();
            PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
            GB.setVisible(false);
            if (listeEnnemy.get(x).getPdVie() <= 0) {
                mortEnemy(GB, x);
            }
            pauseTransition.setOnFinished(e -> {
                initGrille(GB);
                GB.setVisible(true);
                attaqueEnemy(GB);
            });
            pauseTransition.play();
        }

    }

    private void defence(grilleBouton GB) {
        listeHero.get(heroActuel).setDefendre(true);
        setMessage(listeHero.get(heroActuel).getName() + " se défend !");
    }

    private void attaqueEnemy(grilleBouton GB) {

        Random R = new Random();
        int choixCible = R.nextInt(listeHero.size());
        Enemy attaquant = listeEnnemy.get(EnemyAttaquant);

        float pvInit = listeHero.get(choixCible).getPdVie();
        attaquant.attaquer(attaquant, listeHero.get(choixCible));
        float pvActuel = listeHero.get(choixCible).getPdVie();
        setMessage(attaquant.getName() + " attaque " + listeHero.get(choixCible).getName() + "\n" + " et lui inflige " + (pvInit - pvActuel) + " points de dégats");
        Timeline task = setBarHero(listeHero.get(choixCible), ((RepPersonnages) HBhero.getChildren().get(choixCible)).getBarreDeVie());
        task.playFromStart();

        EnemyAttaquant++;
        if (EnemyAttaquant >= listeEnnemy.size()) {
            EnemyAttaquant = 0;
        }

        if (pvActuel <= 0) {
            mortHero(GB, choixCible);
        }

    }


    private void soinSolo(grilleBouton GB) {
        GB.getChildren().clear();

        Healer healer = (Healer) listeHero.get(heroActuel);

        int x = 0;
        int y = 0;
        for (Hero H : listeHero) {

            Button button = new Button(H.getName());
            button.setPrefHeight(40);
            button.setPrefWidth(200);
            int finalX = y;
            button.setOnAction(event -> {
                setMessage(listeHero.get(heroActuel).getName() + " soigne " + listeHero.get(finalX).getName());
                healer.soin(healer, listeHero.get(finalX));
                //Timeline task = setBarHero(listeHero.get(finalX), ((RepPersonnages) HBhero.getChildren().get(finalX)).getBarreDeVie());
                //task.playFromStart();
                ((RepPersonnages) HBhero.getChildren().get(finalX)).getBarreDeVie().setProgress(listeHero.get(finalX).getPdVie()/listeHero.get(finalX).getPdVieMax());
                PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
                GB.setVisible(false);
                pauseTransition.setOnFinished(e -> {
                    initGrille(GB);
                    GB.setVisible(true);
                    attaqueEnemy(GB);
                });
                pauseTransition.play();
            });

            GB.add(button, x, y);

            y++;
        }
    }

    private void initGrille(grilleBouton GB) {
        heroActuel++;
        if (heroActuel >= listeHero.size()) {
            heroActuel = 0;
        }

        if (TableauMorts.contains(listeHero.get(heroActuel))) {
            heroActuel++;
            if (heroActuel >= listeHero.size()) {
                heroActuel = 0;
            }
        }

        GB.getChildren().clear();
        if (!(listeHero.get(heroActuel) instanceof Healer)) {
            Button attaqueButon = new Button("Actions");
            attaqueButon.setPrefWidth(200);
            attaqueButon.setPrefHeight(40);
            attaqueButon.setOnAction(event -> {
                chooseEnemy(GB);
            });
            GB.add(attaqueButon, 0, 0, 2, 1);
        }

        else if(listeHero.get(heroActuel) instanceof Healer){
            Button soinButton = new Button("Soigner");
            soinButton.setPrefWidth(200);
            soinButton.setPrefHeight(40);
            soinButton.setOnAction(event -> {
                soinSolo(GB);
            });
            GB.add(soinButton, 0, 0, 2, 1);
        }

        Button defenceBouton = new Button("Se défendre");
        defenceBouton.setPrefWidth(200);
        defenceBouton.setPrefHeight(40);
        defenceBouton.setOnAction(event -> {
            defence(GB);
            PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
            pauseTransition.setOnFinished(e -> {
                attaqueEnemy(GB);
                initGrille(GB);
            });
            pauseTransition.play();
        });
        GB.add(defenceBouton, 0, 1, 2, 1);
    }

    private void mortHero(grilleBouton GB, int heroMort) {
        PauseTransition pauseTransition1 = new PauseTransition((Duration.millis(1500)));
        TableauMorts.add(listeHero.get(heroMort));
        String name = listeHero.get(heroMort).getName();
        listeHero.remove(heroMort);
        pauseTransition1.setOnFinished(e -> {
            HBhero.getChildren().remove(heroMort);
            setMessage(name + " est mort !");
        });
        pauseTransition1.play();

        if (listeHero.size() == 0) {
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished(e -> {
                setMessage("C'est perdu");
            });
            pauseTransition.play();

            fenetrePopup gameOver = new fenetrePopup();
            PauseTransition pauseTransition2 = new PauseTransition((Duration.millis(5000)));
            GB.setVisible(false);
            pauseTransition2.setOnFinished(e -> {
                gameOver.creationFenetreGameOver(gameStage, gameOver);
                gameStage.close();
            });
            pauseTransition2.play();
        }

    }

    private void mortEnemy(grilleBouton GB, int enemyMort) {
        PauseTransition pauseTransition1 = new PauseTransition((Duration.millis(1500)));
        TableauMorts.add(listeEnnemy.get(enemyMort));
        String name = listeEnnemy.get(enemyMort).getName();
        listeEnnemy.remove(enemyMort);
        pauseTransition1.setOnFinished(e -> {
            HBennemy.getChildren().remove(enemyMort);
            setMessage(name + " est mort !");
        });
        pauseTransition1.play();

        if (listeEnnemy.size() == 0) {
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished(e -> {
                setMessage("C'est gagné !!");
            });
            pauseTransition.play();
        }

    }

    private Timeline setBarHero(Hero combatant, ProgressBar bar) {
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(bar.progressProperty(), bar.getProgress())
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(bar.progressProperty(), (int) combatant.getPdVie()/combatant.getPdvMax())
                )
        );

        return task;
    }


    private Timeline setBarEnemy(Enemy combatant, ProgressBar bar) {
        Timeline task = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new KeyValue(bar.progressProperty(), bar.getProgress())
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(bar.progressProperty(), combatant.getPdVie()/combatant.getPdvMax())
                )
        );

        return task;
    }

    private void setMessage(String string) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }
            protected void interpolate(double frac) {
                final int length = string.length();
                final int n = Math.round(length * (float) frac);
                textDialogue.setText(string.substring(0, n));
            }
        };
        animation.play();
    }

    public ArrayList<Enemy> getListeEnnemy() {
        return listeEnnemy;
    }

    public ArrayList<Hero> getListeHero() {
        return listeHero;
    }

    public int getHeroActuel() {
        return heroActuel;
    }

    public Label getTextDialogue() {
        return textDialogue;
    }

    public HBox getHBsubscene() {
        return HBsubscene;
    }

    public HBox getHBhero() {
        return HBhero;
    }

    public AnchorPane getGamePane() {
        return gamePane;
    }


}
