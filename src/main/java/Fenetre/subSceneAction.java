package Fenetre;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.web;

public class subSceneAction extends SubScene {
    private HBox HBsubscene;
    private Stage subStage;
    private Scene scene;
    public subSceneAction() {
        super(new GridPane(), 400, 200);
        prefHeight(200);
        prefWidth(400);

        GridPane root2 = (GridPane) this.getRoot();
        HBsubscene = new HBox();
        HBsubscene.setLayoutX(100);
        HBsubscene.setLayoutY(100);
        root2.getChildren().add(HBsubscene);
        root2.setBackground(new Background(new BackgroundFill(web("#11F1E0"), new CornerRadii(0), new Insets(0))));

        scene = new Scene(root2,400, 200);
        subStage = new Stage();
        subStage.setScene(scene);

    }

    public GridPane getPane() {
        return (GridPane) this.getRoot();
    }

    public HBox getHBsubscene() {
        return HBsubscene;
    }

    public Stage getSubStage() {
        return subStage;
    }
}
