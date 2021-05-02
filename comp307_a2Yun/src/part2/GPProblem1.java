package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Constant;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

/**
 * This is purposefully "dumb". Do not use any of the GP design (terminals, nodes, types, ...)
 * in this class in your assignment --- it is wrong!
 * 
 * 
 * <p>
 * * https://cvalcarcel.wordpress.com/2009/08/04/jgap-a-firstsimple-tutorial/
 * <p>
 * Above is the helpful tutorial that I found.
 * 
 */
public class GPProblem1 extends GPProblem {

    /** this list map to the Y_LIST */
    List<Double> X_LIST = new ArrayList<>();

    /** this list map to the X_LIST */
    List<Double> Y_LIST = new ArrayList<>();

    private Constant zero;

    private Constant R;

    private Variable _xVariable, _yVariable;

    /**
     * A constructor. It construct a new instance of GPProblem1.
     * <p>
     * 
     * 1. Create a GP Configuration Object
     * <p>
     * 2. Create an initial Genotype
     * <p>
     * 3. Evolve the population
     * <p>
     * 4. Optionally implement custom functions and terminals (a mutable static number)
     *
     * 
     * @throws InvalidConfigurationException
     */
    public GPProblem1() throws InvalidConfigurationException {
        super(new GPConfiguration());
        readFile("regression.txt");// read files

        /*
         * 1. Create a GP Configuration Object
         */
        GPConfiguration config = getGPConfiguration();

        this._xVariable = Variable.create(config, "X", CommandGene.DoubleClass);
        this._yVariable = Variable.create(config, "Y", CommandGene.DoubleClass);

        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setMaxInitDepth(4);
        config.setPopulationSize(1000);
        config.setMaxCrossoverDepth(8);
        config.setReproductionProb((float) 0.015);// 0.015 == 1.5% probability
        config.setMutationProb((float) 0.015);

        // assigned my fitness function to GPConfiguration using setFitnessFunction().
        config.setFitnessFunction(
                new MyFitnessFunction(this.X_LIST, this.Y_LIST,
                        _xVariable, _yVariable));
        config.setStrictProgramCreation(true);

        // config.setPopulationSize(1000);
        zero = new Constant(config, CommandGene.DoubleClass, 0);
        this.R = new Constant(config, CommandGene.DoubleClass, new Random().nextDouble());
    }

    /**
     * Step2:Create an initial Genotype
     * <p>
     * The genotype represents a configured GP environment.
     * 
     * This is where we pass the references to _xVariable (inputs) used by the fitness
     * function.
     * 
     * @see org.jgap.gp.GPProblem#create()
     */
    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();

        // The return type of the GP program.
        Class[] types = { CommandGene.DoubleClass };// inputs are double, so use double

        // Arguments of result-producing chromosome: none
        Class[][] argTypes = { {} };

        // Next, we define the set of available GP commands
        // and terminals to use.
        CommandGene[][] nodeSets = {
                {
                        _xVariable,
                        // _yVariable,
                        // terminal represents inputs
                        new Terminal(config, CommandGene.DoubleClass, 0.0, 10.0, true),

                        new Add(config, CommandGene.DoubleClass),
                        new Multiply(config, CommandGene.DoubleClass),
                        // add multiply & subtracts
                        new org.jgap.gp.function.Subtract(config, CommandGene.DoubleClass),
                        new org.jgap.gp.function.Multiply(config, CommandGene.DoubleClass)
                // new org.jgap.gp.function.Abs(config, CommandGene.DoubleClass),
                // new org.jgap.gp.function.Sine(config, CommandGene.DoubleClass),
                // new org.jgap.gp.function.Cosine(config, CommandGene.DoubleClass),
                // new org.jgap.gp.function.Log(config, CommandGene.DoubleClass),
                // new org.jgap.gp.function.Exp(config, CommandGene.DoubleClass)

                }
        };

        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);

        return result;

        // GPConfiguration config = getGPConfiguration();
        // Class[] types = { CommandGene.VoidClass };
        // Class[][] argTypes = { {} };
        // CommandGene[][] nodeSets = { { zero } };
        // GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
        // nodeSets, 20, true);
        // return result;
    }

    /**
     * Description: <br/>
     * Read the specific regression.txt file and transform & assign it into the java data
     * structure.
     * 
     * @author Yun Zhou
     * @param filePath
     *            the filepath to be specified
     */
    public void readFile(String filePath) {
        try {
            Scanner scan = new Scanner(new File(filePath));
            // first 2 lines are reduntatnt, so skip it
            scan.nextLine();
            scan.nextLine();
            // do the iteration to read remianing lines
            while (scan.hasNext()) {
                // String regex = "\\s+"; // it represents sequence of one or more whitespace
                // // characters,which is used to split
                // line.split(regex);
                String line = scan.nextLine();
                Scanner scanLine = new Scanner(line);
                int index = -1;
                while (scanLine.hasNextDouble()) {
                    index++;
                    double val = scanLine.nextDouble();
                    if (index == 0) {
                        X_LIST.add(val);
                    } else if (index == 1) {
                        Y_LIST.add(val);
                    }
                    // System.out.println(val);
                }
                scanLine.close();// close to save resources
            }
            scan.close();// close to save resources
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // postCondition check to check whether they have the same size, for debugging
        assert this.X_LIST.size() == this.Y_LIST.size();
    }
}
