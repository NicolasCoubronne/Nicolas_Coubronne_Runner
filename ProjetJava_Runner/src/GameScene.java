import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameScene extends Scene {

    public static Camera camera;
    private static StaticThing backgroundLeft = new StaticThing(0, 0, "/assets/desert.png");
    private static StaticThing backgroundRight = new StaticThing(800, 0, "/assets/desert.png");         // Ces deux objets servent à afficher le fond à gauche et à droite de la scène
    private static HP hp;
    private static Hero hero;
    private ArrayList<Foe> foes = new ArrayList<Foe>();         // Liste qui va contenir les ennemis



    public GameScene(Pane pane, int i, int i1, boolean b, int cameraX, int cameraY) {
        super(pane, i, i1, b);

        camera = new Camera(cameraX, cameraY);

        // Affichage des backgrounds
        pane.getChildren().add(this.backgroundLeft.getImageView());
        pane.getChildren().add(this.backgroundRight.getImageView());

        // Affichage de la barre de PV
        hp = new HP(15,5,"/assets/HP.png");
        hp.getImageView().setPreserveRatio(true);
        hp.getImageView().setFitHeight(50);
        hp.setNbr_hp(3);
        pane.getChildren().add(hp.getImageView());

        // Creation et affichage du héro
        hero = new Hero(100, 250, 0, 0, 5, 0, 0, 5,"/assets/heros.png");
        hero.positionHero(hero.getX(), hero.getY());
        hero.spriteHero(0, 0);
        pane.getChildren().add(this.hero.getImageView());

        //  Création et affichage des ennemis
            //Création nombre aléatoire d'ennemis
        int xFoeMin = 1000;     // coordonnée min en x du prochain ennemi créé

        for (int k=0; k<25 + Math.random()*(50-25);k++) {
            xFoeMin = (int) (xFoeMin + 500 + Math.random()*(700-500));      // distance min par rapport au dernier ennemi + distance aléatoire
            int spriteFoe = (int) (Math.random()*2);                        // Apparence du cactus
            Foe newFoe = new Foe(xFoeMin, 250,0,spriteFoe,5,0,0,1,"/assets/Cactus.png");
            newFoe.getImageView().setPreserveRatio(true);
            newFoe.getImageView().setFitHeight(110);
            newFoe.positionFoe(newFoe.getX(),newFoe.getY());
            newFoe.spriteFoe(newFoe.getAttitude(),newFoe.getIndex());
            pane.getChildren().add(newFoe.getImageView());
            foes.add(newFoe);
        }

        // Lancement Timer
        timer.start();

        // Code relatif aux commandes du joueur
        this.setOnKeyPressed((event)-> {

            if (event.getCode()==KeyCode.SPACE) {
                camera.setJumping(true);
            }
            if (event.getCode()==KeyCode.D) {
                camera.setMovingRight(true);
            }
            if (event.getCode()==KeyCode.Q) {
                camera.setMovingLeft(true);
            }
        });

        this.setOnKeyReleased((event)-> {

            if (event.getCode()==KeyCode.D) {
                camera.setMovingRight(false);
            }
            if (event.getCode()==KeyCode.Q) {
                camera.setMovingLeft(false);
            }
        });

    }

    // Update de la GameScene
    static void update(long time, ArrayList<Foe> foes,  HP hp) {
        hero.spriteHero(hero.getAttitude(), hero.getIndex());

        // Déplacement du background

        backgroundRight.getImageView().setX(backgroundRight.getImageView().getX() - 5);        // décallage des background
        backgroundLeft.getImageView().setX(backgroundLeft.getImageView().getX() - 5);

        if (backgroundLeft.getImageView().getX() <= -800) {         // Si l'un des deux background sort de la fenêtre il est replacé à droite
            backgroundLeft.getImageView().setX(800);
        }
        if (backgroundRight.getImageView().getX() <= -800) {
            backgroundRight.getImageView().setX(800);
        }


        // Detection collision, nouvelle collision si temps de collision suffisant => solution aux problèmes de la hitbox carré
        if (hero.isInvincible() == false) {
            for (int j = 0; j < foes.size(); j++) {         // On test la collision avec chaque ennemi
                Foe foe = foes.get(j);
                if (hero.rectangle2DGetHitBox(hero.getImageView(), foe.getImageView())) {       // Si il y a collision :
                    foe.setTimeColliding(foe.getTimeColliding()+1);                             // On augmente le temps de collision
                    if (foe.getTimeColliding() > 8 & hero.getInvincibility() <= 0) {            // Si temps de collision suffisant :
                        hp.setNbr_hp(hp.getNbr_hp() - 1);                                       // On enlève un PV
                        hero.setTime((double) System.nanoTime());       // temps au début de l'invincibilité
                        hero.setInvincibility(25000000000.0);           // Durée de l'invincibilité
                    }
                }

            }
        }
        hero.setInvincibility(hero.getInvincibility() + hero.getTime() - (double) System.nanoTime());   // On diminue la durée de l'invincibilité en fonction du temps passé depuis la collision
    }

    // création du timer
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long time) {
            hero.update(time);
            camera.update(time, hero);
            GameScene.update(time, foes, hp);
            Foe.update(time, foes);
        }
    };
}
