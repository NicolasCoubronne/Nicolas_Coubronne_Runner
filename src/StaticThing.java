import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


// Va être utilisé pour afficher des élements fixes comme l'arrière plan

public class StaticThing {

    private double x;
    private double y;
    private ImageView imageView;         //objet de classe ImageView servant à afficher l'image

    public StaticThing(double x, double y, String fileName) {
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
}
