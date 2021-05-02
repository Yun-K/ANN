package part2;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

public class JGAPTest {
    public static void main(String args[]) throws InvalidConfigurationException {
        GPProblem problem = new GPProblem1();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(100);
        System.out.println("Yay, I can run!");
    }
}
