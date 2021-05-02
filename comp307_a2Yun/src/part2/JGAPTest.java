package part2;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

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
