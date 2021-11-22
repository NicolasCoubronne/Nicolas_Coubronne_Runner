import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class MenuScene extends Scene {

    private StaticThing font = new StaticThing(0, 0, "/assets/desert.png");;
    private Button play = new Button("Play !");

    public MenuScene(Pane pane, Pane pane2, int i, int i1, boolean b) {
        super(pane2, i, i1, b);



        // Paramètre du bouton dans la fenêtre
        play.setPrefSize(100, 40);
        play.setLayoutX(250);
        play.setLayoutY(150);

        play.setOnAction(actionEvent ->  {
            System.out.println("click");
            Main.getStage().setScene(Main.createGameScene(Main.getStage()));    // créer et lance une gameScene
            Main.stageSelector();
        });

        pane2.getChildren().add(this.font.getImageView());
        pane2.getChildren().add(this.play);

    }
}
