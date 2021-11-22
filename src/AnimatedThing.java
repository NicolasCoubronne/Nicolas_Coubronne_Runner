import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class AnimatedThing {

    private double x;
    private double y;
    private ImageView imageView;
    private int attitude, index, frameDuration, frameOffset, numberOfFrame;

    public AnimatedThing(double x, double y, int attitude, int index, int frameDuration, int windowSize, int frameOffset, int numberOfFrame, String fileName) {
        this.x = x;
        this.y = y;
        Image image = new Image(fileName);
        this.imageView = new ImageView(image);
        this.attitude = attitude;           // courir, sauter...
        this.index = index;         // index de la sprite sur la spritesheet
        this.frameDuration = frameDuration;     // nbr d'update pour changer de sprite
        this.numberOfFrame = numberOfFrame;     // nombre de sprites pour un état de l'objet
        this.frameOffset = frameOffset;        // nbr d'update depuis un changement de sprite

    }


    // Méthodes Getter
    public ImageView getImageView() {
        return imageView;
    }

    public int getAttitude() {
        return attitude;
    }

    public int getIndex() {
        return index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Methode setter
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNumberOfFrame(int numberOfFrame) {
        this.numberOfFrame = numberOfFrame;
    }

    // Detection d'une collision entre deux objets
    public boolean rectangle2DGetHitBox(ImageView object1, ImageView object2) {
        if (object1.getBoundsInParent().intersects(object2.getBoundsInParent())) {
            return true;
        }
        return false;
    }

    // Autres méthodes
    public void update(long time) {

        if (this.frameOffset < frameDuration) {         // On ne change pas l'index du sprite à afficher
            this.frameOffset++;
        }
        else {                                          // On va changer l'index du sprite à aficher
            this.frameOffset = 0;                       // On remet le compteur d'updates à 0
            if (this.index < this.numberOfFrame) {
                this.index = index + 1;                 // On change l'index du sprite à afficher
            } else {
                this.index = 0;                         // On change l'index du sprite à afficher
            }
        }
    }
}
