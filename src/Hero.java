import javafx.geometry.Rectangle2D;

public class Hero extends AnimatedThing{

    private double invincibility=0;     // le héro est invincible si la variable est > 0
    private double time;                //

    public Hero(double x, double y, int attitude, int index, int frameDuration, int windowSize, int frameOffset, int numberOfFrame, String fileName) {
        super(x, y, attitude, index, frameDuration, windowSize, frameOffset, numberOfFrame, fileName);
    }

    public double getInvincibility() {
        return invincibility;
    }

    public double getTime() {
        return time;
    }

    public void setInvincibility(double invincibility) {
        this.invincibility = invincibility;
    }

    public void setTime(double time) {
        this.time = time;
    }

    // Positionne le héro dans la fenêtre
    public void positionHero(double x, double y) {
        this.getImageView().setX(x);
        this.getImageView().setY(y);
    }

    // Selectionne le sprite du héro
    public void spriteHero(int attitude, int index){
        this.setAttitude(attitude);
        this.setIndex(index);
        this.getImageView().setViewport(new Rectangle2D(index*85,attitude*160,82,105));
    }

    // Vérifie si le héro est invincible
    public boolean isInvincible() {
        if (invincibility<=0) {
            return false;
        }
        return true;
    }
}
