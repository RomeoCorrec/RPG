package jeudeplateau;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RepPersonnages extends VBox {

    private ImageView imagePersonnage;
    private ProgressBar barreDeVie;

    public RepPersonnages(String imageUrl) {

        this.imagePersonnage = new ImageView(new Image(getClass().getResourceAsStream(imageUrl)));
        barreDeVie = new ProgressBar();
        barreDeVie.setProgress(1);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(this.imagePersonnage);
        this.getChildren().add(barreDeVie);


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

}
