import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HP {
    private double x;
    private double y;
    private int nbr_hp;
    private ImageView imageView;

    public HP (double x, double y, String fileName) {
        this.x = x;
        this.y = y;
        Image image = new Image(fileName);
        imageView = new ImageView(image);
        imageView.setY(y);
        imageView.setX(x);
    }

    public ImageView getImageView() {       // Va servir à afficher l'image ?
        return imageView;
    }

    public int getNbr_hp() {
        return nbr_hp;
    }

    public void setNbr_hp(int nbr_hp) {
        if (nbr_hp >= 1) {              // Si le nombre de PV est >= 1 on réduit la taille du rectangle2D pour afficher le nombre de coeur
            this.nbr_hp = nbr_hp;
            imageView.setViewport(new Rectangle2D(50, 0, nbr_hp * 130, 215));
        }
        else {          // Si le héro n'a plus de PV on décale le rectangle2D pour n'afficher aucun coeur
            this.nbr_hp=0;
            imageView.setViewport(new Rectangle2D(500, 500, 130, 215));
            Main.getStage().close();
        }
    }
}
