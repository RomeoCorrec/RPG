package ElementsJeu;

import Fenetre.fenetreJeu;
import Fenetre.grilleBouton;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Combat {

    private ArrayList<Hero> listeHero;
    private ArrayList<Enemy> listeEnemy;
    private ArrayList<Combatant> listeCombatant;
    private grilleBouton GB = new grilleBouton();
    private fenetreJeu FJ;

    public Combat(ArrayList<Hero> listeHero, ArrayList<Enemy> listeEnemy, fenetreJeu FJ) {
        this.listeHero = listeHero;
        this.listeEnemy = listeEnemy;

        for (Hero H : listeHero) {
            Hero heroActuel = H;
            GB.getChildren().clear();
            Button attaqueButon = new Button("Attaquer");
            attaqueButon.setPrefWidth(120);
            attaqueButon.setPrefHeight(20);
            attaqueButon.setOnAction(event -> {
                chooseEnemy(H, FJ);
            });


        }

        FJ.getHBsubscene().getChildren().add(GB);

    }

    public void chooseEnemy(Hero heroActuel, fenetreJeu FJ) {
        this.GB.getChildren().clear();
        int x = 0;
        int y = 0;
        for (Enemy E : listeEnemy) {

            Button button = new Button(E.getName());
            button.setPrefHeight(20);
            button.setPrefWidth(120);
            button.setOnAction(event -> {
                Attaque(heroActuel, FJ);
            });

            this.GB.add(button, x, y);

            x++;
        }
    }

    public void Attaque(Hero heroActuel, fenetreJeu FJ) {
        Hero hero;
        Enemy enemy;
        hero = heroActuel;
        Random rd = new Random();
        int CH = rd.nextInt(listeEnemy.size());
        if (heroActuel.getClasse().equals("Warrior")) {
            ((Warrior) heroActuel).coupDepee(((Warrior) heroActuel), listeEnemy.get(CH));
            setMessage(heroActuel.getName() + " attaque " + listeEnemy.get(CH).getName(), FJ);

        }


    }

    private void setMessage(String string, fenetreJeu FJ) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }
            protected void interpolate(double frac) {
                final int length = string.length();
                final int n = Math.round(length * (float) frac);
                FJ.getTextDialogue().setText(string.substring(0, n));
            }
        };
        animation.play();
    }


    public void launchCombat(fenetreJeu FJ) {

    }
}