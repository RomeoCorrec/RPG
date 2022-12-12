package Fenetre;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class grilleBouton extends GridPane {

    private Button bouton1;
    private Button bouton2;
    private Button bouton3;
    private Button bouton4;


    public grilleBouton(Button bouton1) {

        this.bouton1 = bouton1;

        setDropBouton(this.bouton1);


        bouton1.setPrefHeight(20);
        bouton1.setPrefWidth(120);

        this.setVgap(20);

        this.add(bouton1, 0, 0, 2, 1);
    }
    public grilleBouton(Button bouton1, Button bouton2) {

        this.bouton1 = bouton1;
        this.bouton2 = bouton2;

        setDropBouton(this.bouton1);
        setDropBouton(this.bouton2);


        bouton1.setPrefHeight(20);
        bouton1.setPrefWidth(120);
        bouton2.setPrefHeight(20);
        bouton2.setPrefWidth(120);


        this.setVgap(20);

        this.add(bouton1, 0, 0, 2, 1);
        this.add(bouton2, 0, 1, 2, 1);
    }

    public grilleBouton(Button bouton1, Button bouton2, Button bouton3) {

        this.bouton1 = bouton1;
        this.bouton2 = bouton2;
        this.bouton3 = bouton3;

        setDropBouton(this.bouton1);
        setDropBouton(this.bouton2);
        setDropBouton(this.bouton3);

        bouton1.setPrefHeight(20);
        bouton1.setPrefWidth(120);
        bouton2.setPrefHeight(20);
        bouton2.setPrefWidth(120);
        bouton3.setPrefHeight(20);
        bouton3.setPrefWidth(120);

        this.setVgap(20);

        this.add(bouton1, 0, 0, 2, 1);
        this.add(bouton2, 0, 1, 2, 1);
        this.add(bouton3, 0, 2, 2, 1);
    }

    public grilleBouton(Button bouton1, Button bouton2, Button bouton3, Button bouton4) {

        this.bouton1 = bouton1;
        this.bouton2 = bouton2;
        this.bouton3 = bouton3;
        this.bouton4 = bouton4;

        setDropBouton(this.bouton1);
        setDropBouton(this.bouton2);
        setDropBouton(this.bouton3);
        setDropBouton(this.bouton4);

        bouton1.setPrefHeight(20);
        bouton1.setPrefWidth(120);
        bouton2.setPrefHeight(20);
        bouton2.setPrefWidth(120);
        bouton3.setPrefHeight(20);
        bouton3.setPrefWidth(120);
        bouton4.setPrefHeight(20);
        bouton4.setPrefWidth(120);

        this.setVgap(20);

        this.add(bouton1, 0, 0, 2, 1);
        this.add(bouton2, 0, 1, 2, 1);
        this.add(bouton3, 0, 2, 2, 1);
        this.add(bouton4, 0, 3, 2, 1);
    }

    public grilleBouton() {

    }


    public void setDropBouton(Button bouton) {


        bouton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bouton.setEffect(new DropShadow());
            }
        });

        bouton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bouton.setEffect(null);
            }
        });

    }
        public Button getBouton1 () {
            return bouton1;
        }

        public Button getBouton2 () {
            return bouton2;
        }

        public Button getBouton3 () {
            return bouton3;
        }

    public Button getBouton4() {
        return bouton4;
    }

    public void setBouton1(Button bouton1) {
        this.bouton1 = bouton1;
    }

    public void setBouton2(Button bouton2) {
        this.bouton2 = bouton2;
    }

    public void setBouton3(Button bouton3) {
        this.bouton3 = bouton3;

    }

    public void setBouton4(Button bouton4) {
        this.bouton4 = bouton4;
    }

    public void setBoutons(ArrayList<Button> listeBouton) {
        DropShadow dropShadow = new DropShadow();
        switch (listeBouton.size()) {
            case 1:
                setBouton1(listeBouton.get(0));
                this.add(bouton1, 0, 1, 2, 1);
                listeBouton.get(0).setPrefHeight(20);
                listeBouton.get(0).setPrefWidth(120);
                listeBouton.get(0).setEffect(dropShadow);
                break;

            case 2:
                setBouton1(listeBouton.get(0));
                this.add(bouton1, 0, 1, 2, 1);
                listeBouton.get(0).setPrefHeight(20);
                listeBouton.get(0).setPrefWidth(120);
                listeBouton.get(0).setEffect(dropShadow);

                setBouton2(listeBouton.get(1));
                this.add(bouton1, 0, 2, 2, 1);
                listeBouton.get(1).setPrefHeight(20);
                listeBouton.get(1).setPrefWidth(120);
                listeBouton.get(1).setEffect(dropShadow);
                break;

            case 3:
                setBouton1(listeBouton.get(0));
                this.add(bouton1, 0, 1, 2, 1);
                listeBouton.get(0).setPrefHeight(20);
                listeBouton.get(0).setPrefWidth(120);
                listeBouton.get(0).setEffect(dropShadow);

                setBouton2(listeBouton.get(1));
                this.add(bouton1, 0, 2, 2, 1);
                listeBouton.get(1).setPrefHeight(20);
                listeBouton.get(1).setPrefWidth(120);
                listeBouton.get(1).setEffect(dropShadow);

                setBouton3(listeBouton.get(2));
                this.add(bouton1, 0, 2, 2, 1);
                listeBouton.get(2).setPrefHeight(20);
                listeBouton.get(2).setPrefWidth(120);
                listeBouton.get(2).setEffect(dropShadow);
                break;

            case 4:
                setBouton1(listeBouton.get(0));
                this.add(bouton1, 0, 1, 2, 1);
                listeBouton.get(0).setPrefHeight(20);
                listeBouton.get(0).setPrefWidth(120);
                listeBouton.get(0).setEffect(dropShadow);

                setBouton2(listeBouton.get(1));
                this.add(bouton1, 0, 2, 2, 1);
                listeBouton.get(1).setPrefHeight(20);
                listeBouton.get(1).setPrefWidth(120);
                listeBouton.get(1).setEffect(dropShadow);

                setBouton3(listeBouton.get(2));
                this.add(bouton1, 0, 3, 2, 1);
                listeBouton.get(2).setPrefHeight(20);
                listeBouton.get(2).setPrefWidth(120);
                listeBouton.get(2).setEffect(dropShadow);

                setBouton4(listeBouton.get(3));
                this.add(bouton1, 0, 3, 2, 1);
                listeBouton.get(3).setPrefHeight(20);
                listeBouton.get(3).setPrefWidth(120);
                listeBouton.get(3).setEffect(dropShadow);
                break;

        }
    }
}
