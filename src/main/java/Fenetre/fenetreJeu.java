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
import Représentations.RepPersonnages;

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
    private ArrayList<Hero> listeHeroInit;
    private int nbrHeroInit;
    private int nbrCombat;
    private int heroActuel;
    private int EnemyAttaquant;
    private ArrayList<Enemy> listeEnnemy;
    private ArrayList<Enemy> listeEnnemyInit;
    private ArrayList<Combatant> tableauCombatants;
    ArrayList<Combatant> TableauMorts;

    private boolean aAffronterUnBoss = false;

    private HBox HBhero;
    private HBox HBennemy;
    private HBox HBsubscene;

    private final static String imageGuerrier = "/images/chevalier.png";
    private final static String imageMage = "/images/mage.png";
    private final static String imageHealer = "/images/Healer.png";
    private final static String imageHunter = "/images/Archer.png";
    private final static String imageEnemy = "/images/Enemy.png";
    private final static String imageBoss = "/images/BOSS.png";
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
        //HBsubscene.setBackground(new Background(new BackgroundFill(web("#11F1E0"), new CornerRadii(0), new Insets(0))));
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


    private void createBackgroud() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            Image image = new Image(getClass().getResourceAsStream("/images/herbe.jpg"));
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
                    HBhero.getChildren().add(new RepPersonnages(imageGuerrier, listeHero.get(i)));
                    break;
                case "Mage":
                    listeHero.add(new Mage(nomHero));
                    HBhero.getChildren().add(new RepPersonnages(imageMage, listeHero.get(i)));
                    break;
                case "Healer":
                    listeHero.add(new Healer(nomHero));
                    HBhero.getChildren().add(new RepPersonnages(imageHealer, listeHero.get(i)));
                    break;
                case "Hunter":
                    listeHero.add(new Hunter(nomHero));
                    HBhero.getChildren().add(new RepPersonnages(imageHunter, listeHero.get(i)));
                    break;
            }
            listeEnnemy.add(new Enemy("enemy " + (i+1)));
            HBennemy.getChildren().add(new RepPersonnages(imageEnemy, listeEnnemy.get(i)));
            Random rd = new Random();
            heroActuel = rd.nextInt(listeHero.size());
            EnemyAttaquant = rd.nextInt(listeEnnemy.size());
            TableauMorts = new ArrayList<>();
        }

        InitialisationInventaire(listeHero);

        listeEnnemyInit = new ArrayList<>();
        listeHeroInit = new ArrayList<>();
        listeHeroInit.addAll(listeHero);
        listeEnnemyInit.addAll(listeEnnemy);

        nbrHeroInit = listeHero.size();
        nbrCombat = 0;

        grilleBouton GB = new grilleBouton();
        GB.setVgap(10);
        setMessage("Cest le tour de " + listeHero.get(heroActuel).getName());

        HBsubscene.getChildren().add(GB);
        HBsubscene.setPadding(new Insets(20, 20, 20, 20));
        HBsubscene.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        HBox.setMargin(GB, new Insets(30,30,30,30));

        initGrille(GB);
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
        intiBoutonRetour(GB , y);
    }

    public void Attaque(grilleBouton GB, int x) {

        if (TableauMorts.contains(listeHeroInit.get(heroActuel))) {
            heroActuel++;
            if (heroActuel >= listeHero.size()) {
                heroActuel = 0;
            }
        }

        while (HBhero.getChildren().size() > listeHero.size()) {
            heroActuel--;
        }

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));

            if (listeHero.get(heroActuel).getClasse().equals("Warrior")) {

                float pdvInit = listeEnnemy.get(x).getPdVie();
                ((Warrior) listeHero.get(heroActuel)).coupDepee(((Warrior) listeHero.get(heroActuel)), listeEnnemy.get(x));
                float pdvActuel = listeEnnemy.get(x).getPdVie();
                setMessage(listeHero.get(heroActuel).getName() + " attaque " + listeEnnemy.get(x).getName() + "\n" + "et lui inflige " + (pdvInit - pdvActuel) + " points de dégats");
                Timeline task = setBarEnemy(listeEnnemy.get(x), ((RepPersonnages) HBennemy.getChildren().get(x)).getBarreDeVie());
                task.playFromStart();
                PauseTransition pauseTransition1 = new PauseTransition((Duration.millis(4500)));
                GB.setVisible(false);
                if (listeEnnemy.get(x).getPdVie() <= 0) {
                    mortEnemy(GB, x);
                }
                pauseTransition1.setOnFinished(e -> {
                    attaqueEnemy(GB);
                    pauseTransition.setOnFinished(event -> {
                        initGrille(GB);
                        int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                        ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                        GB.setVisible(true);
                    });
                    pauseTransition.play();
                });
                pauseTransition1.play();


            } else if (listeHero.get(heroActuel).getClasse().equals("Mage")) {

                GB.getChildren().clear();

                Mage mage = (Mage) listeHero.get(heroActuel);

                Button sortDeFeuButton = new Button("Sort de feu");
                sortDeFeuButton.setPrefHeight(40);
                sortDeFeuButton.setPrefWidth(200);
                sortDeFeuButton.setOnAction(event -> {
                    float pdvInit = listeEnnemy.get(x).getPdVie();
                    mage.sortDeFeu(mage, listeEnnemy.get(x));
                    float pdvActuel = listeEnnemy.get(x).getPdVie();
                    setMessage(listeHero.get(heroActuel).getName() + " lance une boule de feu sur " + listeEnnemy.get(x).getName() + "\n" + " et lui inflige " + (pdvInit - pdvActuel)+ " points de dégats");
                    Timeline task = setBarEnemy(listeEnnemy.get(x), ((RepPersonnages) HBennemy.getChildren().get(x)).getBarreDeVie());
                    task.playFromStart();
                    PauseTransition pauseTransition2 = new PauseTransition((Duration.millis(4500)));
                    GB.setVisible(false);
                    if (listeEnnemy.get(x).getPdVie() <= 0) {
                        mortEnemy(GB, x);
                    }
                    pauseTransition2.setOnFinished(e -> {
                        attaqueEnemy(GB);
                        pauseTransition.setOnFinished(event1 -> {
                            initGrille(GB);
                            int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                            ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                            GB.setVisible(true);
                        });
                        pauseTransition.play();
                    });
                    pauseTransition2.play();
                });


                Button sortDeGlaceButton = new Button("Sort de glace");
                sortDeGlaceButton.setPrefHeight(40);
                sortDeGlaceButton.setPrefWidth(200);
                sortDeGlaceButton.setOnAction(event -> {

                    setMessage(listeHero.get(heroActuel-1).getName() + " affaiblit " + listeEnnemy.get(x).getName());
                    mage.sortDeGlace(mage, listeEnnemy.get(x));
                    PauseTransition pauseTransition3 = new PauseTransition((Duration.millis(4500)));
                    GB.setVisible(false);
                    if (listeEnnemy.get(x).getPdVie() <= 0) {
                        mortEnemy(GB, x);
                    }
                    pauseTransition3.setOnFinished(e -> {
                        attaqueEnemy(GB);
                        pauseTransition.setOnFinished(event2 -> {
                            initGrille(GB);
                            int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                            ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);

                            GB.setVisible(true);
                        });
                        pauseTransition.play();                    });
                    pauseTransition3.play();
                });

                GB.add(sortDeFeuButton, 0, 0, 2, 1);
                GB.add(sortDeGlaceButton, 0, 1, 2, 1);
                intiBoutonRetour(GB , 3);


            } else if (listeHero.get(heroActuel).getClasse().equals("Hunter")) {


                float pvInit = listeEnnemy.get(x).getPdVie();
                ((Hunter) listeHero.get(heroActuel)).tireAlArc(listeEnnemy.get(x), ((Hunter) listeHero.get(heroActuel)));
                float pvActuel = listeEnnemy.get(x).getPdVie();
                setMessage(listeHero.get(heroActuel).getName() + " tire une flèche sur " + listeEnnemy.get(x).getName() + "\n" + " et lui inflige " + (pvInit - pvActuel) + " point de dégats");
                Timeline task = setBarEnemy(listeEnnemy.get(x), ((RepPersonnages) HBennemy.getChildren().get(x)).getBarreDeVie());
                task.playFromStart();
                PauseTransition pauseTransition4 = new PauseTransition((Duration.millis(4500)));
                GB.setVisible(false);
                if (listeEnnemy.get(x).getPdVie() <= 0) {
                    mortEnemy(GB, x);
                }
                pauseTransition4.setOnFinished(e -> {
                    attaqueEnemy(GB);
                    pauseTransition.setOnFinished(event -> {
                        initGrille(GB);
                        int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                        ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                        GB.setVisible(true);
                    });
                    pauseTransition.play();
                });
                pauseTransition4.play();
            }

    }

    private void defence(grilleBouton GB) {
        listeHero.get(heroActuel).setDefendre(true);
        setMessage(listeHero.get(heroActuel).getName() + " se défend !");

    }

    private void attaqueEnemy(grilleBouton GB) {


        while(EnemyAttaquant >= listeEnnemy.size()) {
            EnemyAttaquant--;
        }


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

        //setMessage("Cest le tour de " + listeHero.get(heroActuel).getName());

    }

    private void intiBoutonRetour(grilleBouton GB, int y) {
        Button retourButton = new Button("Retour");
        GB.add(retourButton, 0, y);
        retourButton.setOnAction(event -> {
            heroActuel--;
            if(heroActuel < -1) {
                heroActuel = -1;
            }
            initGrille(GB);
        });
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
                Timeline task = setBarHero(listeHero.get(finalX), ((RepPersonnages) HBhero.getChildren().get(finalX)).getBarreDeVie());
                task.playFromStart();
                // ((RepPersonnages) HBhero.getChildren().get(finalX)).getBarreDeVie().setProgress(listeHero.get(finalX).getPdVie()/listeHero.get(finalX).getPdVieMax());
                PauseTransition pauseTransition2 = new PauseTransition((Duration.millis(3000)));
                PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
                GB.setVisible(false);
                pauseTransition2.setOnFinished(e -> {
                    attaqueEnemy(GB);
                    pauseTransition.setOnFinished(event1 -> {
                        initGrille(GB);
                        int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                        ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                        GB.setVisible(true);
                    });
                    pauseTransition.play();
                });
                pauseTransition2.play();
            });

            GB.add(button, x, y);

            y++;
        }
        intiBoutonRetour(GB, y+1);
    }

    private void soinZone(grilleBouton GB) {

        Healer healer = (Healer) listeHero.get(heroActuel);
        setMessage(healer.getName() + " soigne en zone");
        healer.soinDeZone(healer, listeHero);

        for (int l = 0; l < HBhero.getChildren().size(); l++) {
            Timeline task = setBarHero(listeHero.get(l), ((RepPersonnages) HBhero.getChildren().get(l)).getBarreDeVie());
            task.playFromStart();
        }

        PauseTransition pauseTransition2 = new PauseTransition((Duration.millis(3000)));
        PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
        GB.setVisible(false);
        pauseTransition2.setOnFinished(e -> {
            attaqueEnemy(GB);
            pauseTransition.setOnFinished(event1 -> {
                initGrille(GB);
                int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                GB.setVisible(true);
            });
            pauseTransition.play();
        });
        pauseTransition2.play();

    }

    private void initGrille(grilleBouton GB) {

        heroActuel++;
        if (heroActuel >= listeHero.size()) {
            heroActuel = 0;
        }

        int i = 1;
        setMessage("Cest le tour de " + listeHero.get(heroActuel).getName());
        ((RepPersonnages) HBhero.getChildren().get(heroActuel)).getNomCombatant().setTextFill(Color.RED);

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

            Button soinZoneButton = new Button("Soigner en zone");
            soinZoneButton.setPrefWidth(200);
            soinZoneButton.setPrefHeight(40);
            soinZoneButton.setOnAction(event -> {
                soinZone(GB);
            });
            GB.add(soinButton, 0, 0, 2, 1);
            GB.add(soinZoneButton, 0, 1, 2, 1);
            i++;
        }

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
        PauseTransition pauseTransition2 = new PauseTransition(Duration.millis(3000));
        Button defenceBouton = new Button("Se défendre");
        defenceBouton.setPrefWidth(200);
        defenceBouton.setPrefHeight(40);
        defenceBouton.setOnAction(event -> {
            defence(GB);
            GB.setVisible(false);
            pauseTransition2.setOnFinished(e -> {
                attaqueEnemy(GB);
                pauseTransition.setOnFinished(event1 -> {
                    initGrille(GB);
                    int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                    ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                    GB.setVisible(true);
                });
                pauseTransition.play();
            });
            pauseTransition2.play();
        });
        GB.add(defenceBouton, 0, i, 2, 1);

        Button itemsButton = new Button("Utiliser un item");
        itemsButton.setPrefWidth(200);
        itemsButton.setPrefHeight(40);
        itemsButton.setOnAction(event -> {
            choixItem(GB);
        });
        GB.add(itemsButton, 0, i+1, 2, 1);

    }

    private void choixItem(grilleBouton GB) {
        GB.getChildren().clear();

        setMessage("Vous disposer dans votre inventaire de :\n-" + listeHero.get(heroActuel).getQuantiteNouriture() +
                " portions de nouriture\n-" + listeHero.get(heroActuel).getQuantitePotion() + " potions");

        Button choixPotion = new Button("Potion");
        choixPotion.setPrefWidth(100);
        choixPotion.setPrefHeight(20);

        MenuButton nbrPotions = new MenuButton("Quantité");
        nbrPotions.setPrefWidth(100);
        nbrPotions.setPrefHeight(20);

        Button choixNouriture = new Button("Nouriture");
        choixNouriture.setPrefWidth(100);
        choixNouriture.setPrefHeight(20);

        MenuButton nbrPortions = new MenuButton("Quantité");
        nbrPortions.setPrefWidth(100);
        nbrPortions.setPrefHeight(20);

        for (int i = 0; i < listeHero.get(heroActuel).getQuantitePotion(); i++) {
            MenuItem nbr = new MenuItem(Integer.toString(i + 1));
            nbrPotions.getItems().add(nbr);
            int I = i;
            nbr.setOnAction(event -> {
                nbrPotions.setText(nbr.getText());
            });
        }

        for (int i = 0; i < listeHero.get(heroActuel).getQuantiteNouriture(); i++) {
            MenuItem nbr = new MenuItem(Integer.toString(i + 1));
            nbrPortions.getItems().add(nbr);
            int I = i;
            nbr.setOnAction(event -> {
                nbrPortions.setText(nbr.getText());
            });
        }

        choixPotion.setOnAction(event -> {
            if (!nbrPotions.getText().equals("Quantité")) {
                int quantite = Integer.parseInt(nbrPotions.getText());

                float pdvRecup = listeHero.get(heroActuel).getEfficaciteSoin() * quantite * 20;
                if (listeHero.get(heroActuel).getPdVie() + pdvRecup > listeHero.get(heroActuel).getPdvMax()) {
                    listeHero.get(heroActuel).setPdVie(listeHero.get(heroActuel).getPdvMax());
                } else {
                    listeHero.get(heroActuel).setPdVie(listeHero.get(heroActuel).getPdVie() + pdvRecup);
                }
                listeHero.get(heroActuel).setQuantitePotion(listeHero.get(heroActuel).getQuantitePotion() - quantite);

                if (listeHero.get(heroActuel).getQuantitePotion() < 0) {
                    listeHero.get(heroActuel).setQuantitePotion(0);
                }

                setMessage("Vous buvez " + quantite + " potion et récupérez " + pdvRecup + " points de vie");
                Timeline task = setBarHero(listeHero.get(heroActuel), ((RepPersonnages) HBhero.getChildren().get(heroActuel)).getBarreDeVie());
                task.playFromStart();

                PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
                PauseTransition pauseTransition1 = new PauseTransition((Duration.millis(3000)));
                GB.setVisible(false);
                pauseTransition.setOnFinished(e -> {
                    attaqueEnemy(GB);
                    pauseTransition1.setOnFinished(event1 -> {
                        initGrille(GB);
                        int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                        ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                        GB.setVisible(true);
                    });
                    pauseTransition1.play();
                });
                pauseTransition.play();

            } else if (nbrPotions.getText().equals("Quantité")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("quantité de potions souhaité");
                alert.setContentText("veuillez donner la quantité de potions souhaité");
                alert.showAndWait();
            }


        });


        choixNouriture.setOnAction(event -> {
            if (!nbrPortions.getText().equals("Quantité")) {
                int quantite = Integer.parseInt(nbrPortions.getText());

                float pdvRecup = listeHero.get(heroActuel).getEfficaciteSoin() * quantite * 10;
                if (listeHero.get(heroActuel).getPdVie() + pdvRecup > listeHero.get(heroActuel).getPdvMax()) {
                    listeHero.get(heroActuel).setPdVie(listeHero.get(heroActuel).getPdvMax());
                } else {
                    listeHero.get(heroActuel).setPdVie(listeHero.get(heroActuel).getPdVie() + pdvRecup);
                }
                listeHero.get(heroActuel).setQuantiteNouriture(listeHero.get(heroActuel).getQuantiteNouriture() - quantite);

                /*
                if (listeHero.get(heroActuel).getQuantiteNouriture() < 0) {
                    listeHero.get(heroActuel).setQuantiteNouriture(0);
                }
                */

                setMessage("Vous mangez " + quantite + " portions et récupérez " + pdvRecup + " points de vie");
                Timeline task = setBarHero(listeHero.get(heroActuel), ((RepPersonnages) HBhero.getChildren().get(heroActuel)).getBarreDeVie());
                task.playFromStart();

                PauseTransition pauseTransition = new PauseTransition((Duration.millis(3000)));
                PauseTransition pauseTransition1 = new PauseTransition((Duration.millis(3000)));
                GB.setVisible(false);
                pauseTransition.setOnFinished(e -> {
                    attaqueEnemy(GB);
                    pauseTransition1.setOnFinished(event1 -> {
                        initGrille(GB);
                        int heroPrecedent = (heroActuel - 1) == -1 ? listeHero.size() -1 :heroActuel-1;
                        ((RepPersonnages) HBhero.getChildren().get(heroPrecedent)).getNomCombatant().setTextFill(Color.BLACK);
                        GB.setVisible(true);
                    });
                    pauseTransition1.play();
                });
                pauseTransition.play();

            } else if (nbrPortions.getText().equals("Quantité")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("quantité de nouriture souhaité");
                alert.setContentText("veuillez donner la quantité de portions souhaité");
                alert.showAndWait();
            }


        });

        GB.add(choixPotion, 0, 0, 2, 1);
        GB.add(choixNouriture, 0, 1, 2, 1);
        GB.add(nbrPotions, 2, 0, 2, 1);
        GB.add(nbrPortions, 2, 1, 2, 1);
        intiBoutonRetour(GB, 2);
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

            fenetreGameOver gameOver = new fenetreGameOver();
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

        if (TableauMorts.contains(listeEnnemyInit.get(EnemyAttaquant))) {
            EnemyAttaquant++;
            if (EnemyAttaquant >= listeEnnemy.size()) {
                EnemyAttaquant = 0;
            }
        }

        if (listeEnnemy.size() == 0) {
            nbrCombat++;
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(3000));
            pauseTransition.setOnFinished(e -> {
                setMessage("C'est gagné !!");
            });
            pauseTransition.play();
            pauseTransition.setOnFinished(event -> {
                nbrCombat++;
                rebootCombat(nbrCombat, GB);
            });
            pauseTransition.play();
        }

    }

    private void rebootCombat(int b, grilleBouton GB) {
        if (nbrCombat < 5) {
            GB.getChildren().clear();
            for (int i = 0; i < nbrHeroInit; i++) {
                listeEnnemy.add(new Enemy("enemy " + i));
                HBennemy.getChildren().add(new RepPersonnages(imageEnemy, listeEnnemy.get(i)));
                Random rd = new Random();
                heroActuel = rd.nextInt(listeHero.size());
                EnemyAttaquant = rd.nextInt(listeEnnemy.size());
                TableauMorts = new ArrayList<>();
            }
        }
        else if (aAffronterUnBoss) {
            setMessage("Vous avez vaincu le boss");
            fenetreVictoire fenetreVictoire = new fenetreVictoire(HBhero);
            for (int l = 0; l < listeHero.size(); l++) {
                ((RepPersonnages) HBhero.getChildren().get(l)).getNomCombatant().setTextFill(Color.web("#269CBF"));
            }
            PauseTransition pauseTransition2 = new PauseTransition((Duration.millis(1000)));
            GB.setVisible(false);
            pauseTransition2.setOnFinished(e -> {
                fenetreVictoire.creationFenetreVictoire(gameStage, fenetreVictoire);
                gameStage.close();
            });
            pauseTransition2.play();

        }
        else {
            setMessage("Vous rencontrez un BOSS !!");
            aAffronterUnBoss = true;
            PauseTransition pauseTransition = new PauseTransition(Duration.millis(1000));
            pauseTransition.setOnFinished(event -> {
                BOSS boss = new BOSS("BOSS");
                listeEnnemy.add(boss);
                HBennemy.getChildren().add(new RepPersonnages(imageBoss, boss));
            });
            pauseTransition.play();
        }
    }

    public static void InitialisationInventaire(ArrayList<Hero> TH) {
        for (Hero i : TH) {
            for (int q = 0; q < i.getQuantiteNouriture(); q++) {
                i.addInventory(new Food());
            }

            for (int q = 0; q < i.getQuantitePotion(); q++) {
                i.addInventory(new Potion());
            }
        }
    }


    private void gameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            }
        };

        gameTimer.start();
    }

    private Timeline setBarHero(Hero combatant, ProgressBar bar) {
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