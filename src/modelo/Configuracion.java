package modelo;

import java.io.Serializable;

/**
 *
 * @author Isma2
 */
public class Configuracion implements Serializable {

    private int poblacion, longCrom, iteraccion, evoluciones;

    public Configuracion(int poblacion, int longCrom, int iteraccion, int evoluciones) {
        this.poblacion = poblacion;
        this.longCrom = longCrom;
        this.iteraccion = iteraccion;
        this.evoluciones = evoluciones;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getLongCrom() {
        return longCrom;
    }

    public void setLongCrom(int longCrom) {
        this.longCrom = longCrom;
    }

    public int getIteraccion() {
        return iteraccion;
    }

    public void setIteraccion(int iteraccion) {
        this.iteraccion = iteraccion;
    }

    public int getEvoluciones() {
        return evoluciones;
    }

    public void setEvoluciones(int evoluciones) {
        this.evoluciones = evoluciones;
    }

    @Override
    public String toString() {
        return """
            ====CONFIGURACIÓN=====
               Población: %d
               Longitud de cromosomas: %d
               Iteracciones: %d
               Evoluciones: %d""".formatted(poblacion, longCrom, iteraccion, evoluciones);
    }

}
