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
 * @version
 */
public class JGAPTest {

    public static void main(String args[]) throws InvalidConfigurationException {

        GPProblem problem = new GPProblem1();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        // System.out.println("Yay, I can run!");

        gp.evolve(200);
        System.out.println("\n Formula:  ");
        gp.outputSolution(gp.getAllTimeBest());

    }
}
