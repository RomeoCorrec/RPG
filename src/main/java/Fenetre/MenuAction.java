package Fenetre;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class MenuAction extends grilleBouton{

    private boolean estVisible = true;

    public MenuAction(HBox HBsubscene, fenetreJeu FJ) {
        super(new Button("Attaquer"), new Button("se défendre"), new Button("Items"));

        this.getBouton1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuChoixEnemy menuChoixEnemy;
                MenuChoixEnemy menuChoixEnemy1 = new MenuChoixEnemy();
                menuChoixEnemy = menuChoixEnemy1.instantiationMenuChoixEnemy(FJ.getListeEnnemy().size(), FJ, getThis());
                setVisibilite(!estVisible);
                HBsubscene.getChildren().add(menuChoixEnemy);
                menuChoixEnemy.setAlignment(Pos.CENTER);
                HBox.setMargin((menuChoixEnemy), new Insets(0, 100, 0, 0));

            }
        });

        this.getBouton2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        this.getBouton3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


    }


    private void setVisibilite(boolean visibilite) {

        this.setVisible(visibilite);
        this.estVisible = !estVisible;
    }

    private MenuAction getThis() {
        return this;
    }

}
