package part2;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

/**
 * Description: <br/>
 * The class that has main method which can test the JGAP.
 * <p>
 * https://cvalcarcel.wordpress.com/2009/08/04/jgap-a-firstsimple-tutorial/
 * <p>
 * Above is the helpful tutorial that I found.
 *
 * @author Yun Zhou 300442776
 */
public class JGAPTest {

    public static void main(String args[]) throws InvalidConfigurationException {

        GPProblem problem = new GPProblem1();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        // System.out.println("Yay, I can run!");

        /*
         * loop 200 generation times, stop it till it's in the 200th generation OR the fitness
         * value reaches 0
         */
        int generation_time = 0;
        while (++generation_time < 200) {
            gp.evolve(generation_time);// evolve it
            double fitnessValue = gp.getFittestProgramComputed().getFitnessValue();
            // check whether the fitness value is smaller than a predefined value,
            // if so, then meet the stopping criteria
            if (fitnessValue == 0) {
                // System.out.println("stop time" + time);
                break;
            }
        }
        // gp.evolve(200);

        System.out.println("\nFinal Formula:  ");
        gp.outputSolution(gp.getAllTimeBest());

    }
}
