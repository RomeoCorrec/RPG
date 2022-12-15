package Représentations;

import ElementsJeu.Combatant;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RepPersonnages extends VBox {

    private ImageView imagePersonnage;
    private ProgressBar barreDeVie;
    private Label nomCombatant;

    public RepPersonnages(String imageUrl, Combatant combatant) {

        this.imagePersonnage = new ImageView(new Image(getClass().getResourceAsStream(imageUrl)));
        this.barreDeVie = new ProgressBar();
        String nom = combatant.getName();
        this.nomCombatant = new Label(nom);
        this.barreDeVie.setProgress(1);
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);

        this.getChildren().add(this.nomCombatant);
        this.getChildren().add(this.imagePersonnage);
        this.getChildren().add(this.barreDeVie);


    }

    public ProgressBar getBarreDeVie() {
        return barreDeVie;
    }
    public void setBarreDeVie(int pdvMax, float pdvActuel) {
        barreDeVie.setProgress(pdvActuel/pdvMax);
    }

    public float RatioPdv(int pdvMax, float pdvActuel) {
        return pdvActuel/pdvMax;
    }

    public Label getNomCombatant() {
        return nomCombatant;
    }
}
