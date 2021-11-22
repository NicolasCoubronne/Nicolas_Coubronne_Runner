import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class Foe extends AnimatedThing {

    int timeColliding=0;       // renvoie le temps depuis la collision avec le héro

    public Foe(double x, double y, int attitude, int index, int frameDuration, int windowSize, int frameOffset, int numberOfFrame, String fileName) {
        super(x, y, attitude, index, frameDuration, windowSize, frameOffset, numberOfFrame, fileName);
    }

    public int getTimeColliding() {
        return timeColliding;
    }

    public void setTimeColliding(int timeColliding) {
        this.timeColliding = timeColliding;
    }

    // Affichage de l'ennemi
    public void spriteFoe(int attitude, int index) {
        this.setAttitude(attitude);
        this.setIndex(index);
        this.getImageView().setViewport(new Rectangle2D(index * 200 + 954, attitude * 160 + 235, 185, 235));
    }

    // Positionne l'ennemi dans la fenêtre
    public void positionFoe(double x, double y) {
        this.getImageView().setX(x);
        this.getImageView().setY(y);
    }

    // Update de l'ennemi
    public static void update(long time, ArrayList<Foe> foes) {
        for (int k=0; k<foes.size(); k++) {
            foes.get(k).setX(foes.get(k).getX()-5);
            foes.get(k).getImageView().setX(foes.get(k).getX());         // On déplace l'ennemi de -5 sur la GameScene
        }
    }
}