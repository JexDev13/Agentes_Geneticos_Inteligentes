package modelo;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

/**
 *
 * @author Isma2
 */
public class FuncionAptitud extends FitnessFunction {

    @Override
    protected double evaluate(IChromosome ic) {
        double score = 0;

        int sx = (int) ic.getGene(0).getAllele();
        int sy = (int) ic.getGene(6).getAllele();

        String vX = (int) ic.getGene(1).getAllele() + ""
                + (int) ic.getGene(2).getAllele() + ""
                + (int) ic.getGene(3).getAllele() + ""
                + (int) ic.getGene(4).getAllele() + ""
                + (int) ic.getGene(5).getAllele();

        String vY = (int) ic.getGene(7).getAllele() + ""
                + (int) ic.getGene(8).getAllele() + ""
                + (int) ic.getGene(9).getAllele() + ""
                + (int) ic.getGene(10).getAllele() + ""
                + (int) ic.getGene(11).getAllele();

        int valX = Integer.parseInt(vY, 2);
        int valY = Integer.parseInt(vX, 2);

        if (sx == 0) {
            valX = -valX;
        }

        if (sy == 0) {
            valY = -valY;
        }

        score = (valX * valX * valX) + (valY * valY * valY * valY);

        if (score < 0) {
            score = 0;
        }
        return score;
    }
}
