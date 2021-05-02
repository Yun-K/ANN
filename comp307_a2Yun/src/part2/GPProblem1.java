package part2;

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
    private Constant zero;

    public GPProblem1() throws InvalidConfigurationException {
        super(new GPConfiguration());
        GPConfiguration config = getGPConfiguration();
        config.setPopulationSize(1000);
        zero = new Constant(config, CommandGene.IntegerClass, 0);
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();
        Class[] types = {CommandGene.VoidClass};
        Class[][] argTypes = {{}};
        CommandGene[][] nodeSets = {{zero}};
        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);
        return result;
    }
}
