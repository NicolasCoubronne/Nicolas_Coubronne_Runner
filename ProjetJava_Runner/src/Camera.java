import java.util.ArrayList;

public class Camera {
    private double x;
    private int y;
    private double km, fm, dt, vx, vy, ax, ax_p, ay, ay_p, g;
    private boolean isJumping;
    private boolean isMovingRight;
    private boolean isMovingLeft;
    int t;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
        km = 1;
        fm = -1.2;
        dt = 0.060;
        t = 1;
        vx = 0;
        vy = 0;
        ax = 0;
        ay = 0;
        g = 100;
        isJumping = false;
        isMovingRight = false;
        isMovingLeft = false;
    }

    // Getter
    @Override
    public String toString() {
        return x + "," + y;
    }

    public double getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isJumping() {
        return isJumping;
    }

    // Setter
    public void setCamera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setAy(double ay) {
        this.ay = ay;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public void update(long time, Hero hero) {

        // Equation sur x héro
        double x_hero = hero.getX();

        if (isMovingRight == true & x_hero<=500) {              // Si "q" enfoncé et joueur non à la limite gauche
            vx = 100;
            x_hero = x_hero + dt * vx;
        }
        else if (isMovingLeft == true & x_hero>=0) {            // Si "d" enfoncé et joueur non à la limite gauche
            vx = -100;
            x_hero = x_hero + dt * vx;
        }

        else {
            x_hero = x_hero + dt * vx;              // équation type masse ressort ramenant le héro au centre de la caméra
            ax_p = ax;                            // sauvergarde de ax à l'instant précédent
            double delta = x_hero - this.x;
            ax = fm * vx - km * (delta);
            vx = vx + dt * ax_p;
        }

        hero.setX(x_hero);
        hero.getImageView().setX(x_hero);       // Rq perso : hero.x et la position en x du sprite sont deux choses différentes !!!

        //Equation sur y héro
        double y_hero = hero.getY();
        y_hero = y_hero + dt * vy;
        ay_p = ay;                             // sauvergarde de ay à l'instant précédent
        if (y_hero < 250) {                     // le t du héro est régi par la gravité
            ay = g;
            vy = vy + dt * ay_p;
            isJumping = false;
        } else {
            if (isJumping == true & y_hero==250) {      // Si le héro est au sol et "espace" enfoncée alors impulsion d'accélération
                ay = -1300;
                vy = vy + dt * ay_p;
            } else {
                ay = 0;
                vy = 0;
                y_hero = 250;
            }
        }

        // Modification du sprite du héro selon la direction du saut
        if (ay > 1 | ay < -1) {                        // si le héro saute alors on affiche la frame de saut
            hero.setNumberOfFrame(0);
            hero.spriteHero(1, 0);
        } else {                                  // si le héro est au sol alors on affiche les sprites de course
            hero.setNumberOfFrame(5);
            if (hero.getAttitude() != 0) {
                hero.setIndex(0);
                hero.setAttitude(0);
            }

        }

        hero.setY(y_hero);
        hero.getImageView().setY(y_hero);

    }
}
