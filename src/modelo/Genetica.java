package modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author Isma2
 */
public class Genetica {

    public Configuration configuracionAG(int tamanioPoblacion, int longGene) {
        Configuration.reset();
        Configuration conf = new DefaultConfiguration();
        try {
            conf.setFitnessFunction(new FuncionAptitud());
            conf.setPopulationSize(tamanioPoblacion);
            Chromosome ejemplo = new Chromosome(conf, genesLong(longGene, conf));
            conf.setSampleChromosome(ejemplo);
            return conf;
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(Genetica.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Genotype get_Poblacion(Configuration conf) {
        try {
            return Genotype.randomInitialGenotype(conf);
        } catch (InvalidConfigurationException ex) {
            Logger.getLogger(Genetica.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public double[] evolucionar(Genotype poblacion, int numEvoluciones, int numIteraccion) {
        double[] resultados;
        for (int i = 0; i < numIteraccion; i++) {
            poblacion.evolve(numEvoluciones);
//            System.out.println("===ITERACIÓN #" + (i+1) + "===");
            descomponerTodos(poblacion.getChromosomes());
//            System.out.println("-----Mejor de la iteración----");
            descomponerIndividuo(poblacion.getFittestChromosome());
//            System.out.println("------------------------------");
        }
//        System.out.println("--------Mejor de todos--------");
        resultados = descomponerIndividuo(poblacion.getFittestChromosome());
//        System.out.println("------------------------------");
        return resultados;
    }

    public void descomponerTodos(IChromosome[] ics) {
        for (IChromosome ic : ics) {
            descomponerIndividuo(ic);
        }
    }

    public double[] descomponerIndividuo(IChromosome ic) {
        double[] resultados = new double[3];
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
//        System.out.println("==Individuo==");
//        System.out.println("Score: " + ic.getFitnessValue());
//        System.out.println("X: " + valX + "; Y: " + valY);
//        System.out.println("=============");
        resultados[0] = ic.getFitnessValue();
        resultados[1] = valX;
        resultados[2] = valY;
        return resultados;
    }

    private Gene[] genesLong(int longGene, Configuration conf) {
        Gene[] genes = new Gene[longGene];
        for (int i = 0; i < longGene; i++) {
            try {
                genes[i] = new IntegerGene(conf, 0, 1);
            } catch (InvalidConfigurationException ex) {
                Logger.getLogger(Genetica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return genes;
    }
}