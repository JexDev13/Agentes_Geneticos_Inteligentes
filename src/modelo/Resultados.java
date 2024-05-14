package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Isma2
 */
public class Resultados implements Serializable {

    private double score;
    private int X;
    private int Y;

    ArrayList<Configuracion> configuraciones = new ArrayList<>();

    public Resultados(double score, int X, int Y) {
        this.score = score;
        this.X = X;
        this.Y = Y;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public ArrayList<Configuracion> getConfiguraciones() {
        return configuraciones;
    }

    public void setConfiguraciones(Configuracion conf) {
        configuraciones.add(conf);
    }

    @Override
    public String toString() {
        return """
            ======RESULTADOS======
               Score: %s 
               X: %d; Y: %d""".formatted(score, X, Y);
    }
}
