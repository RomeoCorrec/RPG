package Fenetre;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

public class grilleMBoutons extends GridPane {

    private MenuButton bouton1;
    private Button bouton2;
    private MenuButton bouton3;


    public grilleMBoutons(MenuButton bouton1, Button bouton2, MenuButton bouton3, int i) {
        for(int j = 0; j < i; j++) {
            bouton1.getItems().add(new MenuItem("Enemy" + j));
            int finalJ = j;
            bouton1.getItems().get(j).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    bouton1.setText(bouton1.getItems().get(finalJ).getText());
                }
            });
        }
        this.bouton2 = bouton2;
        this.bouton3 = bouton3;

        this.add(bouton1, 0, 0, 2, 1);
        this.add(bouton2, 0, 1, 2, 1);
        this.add(bouton3, 0, 2, 2, 1);
    }
}
