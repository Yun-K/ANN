package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Constant;

/**
 * This is purposefully "dumb". Do not use any of the GP design (terminals, nodes, types, ...)
 * in this class in your assignment --- it is wrong!
 */
public class GPProblem1 extends GPProblem {

    /** this list map to the Y_LIST */
    List<Double> X_LIST = new ArrayList<>();

    /** this list map to the X_LIST */
    List<Double> Y_LIST = new ArrayList<>();

    private Constant zero;

    public GPProblem1() throws InvalidConfigurationException {
        super(new GPConfiguration());
        readFile("regression.txt");

        GPConfiguration config = getGPConfiguration();
        config.setPopulationSize(1000);
        zero = new Constant(config, CommandGene.IntegerClass, 0);
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();
        Class[] types = { CommandGene.VoidClass };
        Class[][] argTypes = { {} };
        CommandGene[][] nodeSets = { { zero } };
        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);
        return result;
    }

    private void readFile(String filePath) {
        try {
            Scanner scan = new Scanner(new File(filePath));
            // first 2 lines are reduntatnt, so skip it
            scan.nextLine();
            scan.nextLine();

            // do the iteration to read remianing lines
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Scanner scanLine = new Scanner(line);
                int index = -1;
                while (scanLine.hasNextDouble()) {
                    index++;
                    if (index == 0) {
                        X_LIST.add(scanLine.nextDouble());
                    } else if (index == 1) {
                        Y_LIST.add(scan.nextDouble());
                    } else {
                        // assertion check, should not be exxecuted
                        assert false;
                    }
                }
                scanLine.close();// close to save resources
            }
            scan.close();// close to save resources
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assert this.X_LIST.size() == this.Y_LIST.size();
    }
}
