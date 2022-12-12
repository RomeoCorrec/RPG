package Fenetre;

import ElementsJeu.Combatant;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

import java.util.ArrayList;

public class MenuChoixEnemy extends grilleBouton{

    public MenuChoixEnemy(Button bouton1, Button bouton2, Button bouton3, Button bouton4) {
        super(bouton1, bouton2, bouton3, bouton4);
    }

    public MenuChoixEnemy(Button bouton1, Button bouton2, Button bouton3) {
        super(bouton1, bouton2, bouton3);
    }
    public MenuChoixEnemy(Button bouton1, Button bouton2) {
        super(bouton1, bouton2);
    }
    public MenuChoixEnemy(Button bouton1) {
        super(bouton1);
    }
    public MenuChoixEnemy() {
    }

    public MenuChoixEnemy(MenuAction menuAction) {
    }

    public MenuChoixEnemy instantiationMenuChoixEnemy(int i, fenetreJeu FJ, MenuAction menuAction) {
        MenuChoixEnemy menuChoixEnemy;
        switch (i) {
            case 1:
                MenuChoixEnemy menuChoixEnemy1 = new MenuChoixEnemy(new Button("Enemy 1"));
                menuChoixEnemy1.getBouton1().setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //Combatant combatant = FJ.getCombatantActuel();
                        menuAction.setVisible(true);
                        menuChoixEnemy1.setVisible(false);
                        FJ.getTextDialogue().setText("allo");
                    }
                });
                menuChoixEnemy = menuChoixEnemy1;

                break;


            case 2:
                MenuChoixEnemy menuChoixEnemy2 = new MenuChoixEnemy(new Button("Enemy 1"), new Button("Enemy 2"));
                menuChoixEnemy = menuChoixEnemy2;
                break;

            case 3:
                MenuChoixEnemy menuChoixEnemy3 = new MenuChoixEnemy(new Button("Enemy 1"), new Button("Enemy 2"), new Button("Enemy 3"));
                menuChoixEnemy = menuChoixEnemy3;
                break;

            case 4:
                MenuChoixEnemy menuChoixEnemy4 = new MenuChoixEnemy(new Button("Enemy 1"), new Button("Enemy 2"), new Button("Enemy 3"), new Button("Enemy 4"));
                menuChoixEnemy = menuChoixEnemy4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + i);
        }
        return menuChoixEnemy;
    }


}
