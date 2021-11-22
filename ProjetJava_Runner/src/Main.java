import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// Reminder :
//--module-path C:/Users/User/Desktop/ENSEA_programmes/JavaFx/lib --add-modules javafx.controls

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {


        // Génère la fenêtre/root/pane/scene
        primaryStage.setTitle("Run !");
        Group root = new Group();
        Pane pane = new Pane(root);
        Pane pane2 = new Pane(root);
        //GameScene theScene = new GameScene(pane, 600, 400, true, 250,0);
        MenuScene menuScene = new MenuScene(pane, pane2, 600, 400, true);
        stage=primaryStage;

        // Affiche la fenêtre
        primaryStage.setScene(menuScene);
        primaryStage.show();

    }


    // Méthode permettant de créer une gameScene
    public static GameScene createGameScene(Stage primaryStage){

        primaryStage.setTitle("Run !");
        Group root = new Group();
        Pane pane = new Pane(root);
        GameScene theScene = new GameScene(pane, 600, 400, true, 250,0);
        return theScene;
    }


    public static void stageSelector() {
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    public static Stage getStage() {
        return stage;
    }


}
