package part2;

import java.util.List;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

/**
 * Description: <br/>
 * A fitness function which reflects the ability of an individual to survive and reproduce
 * ("survival of the fittest").
 * 
 * <p>
 * https://cvalcarcel.wordpress.com/2009/08/04/jgap-a-firstsimple-tutorial/
 * 
 * @author Yun Zhou 300442776
 * @version
 */
public class MyFitnessFunction extends GPFitnessFunction {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = 1L;

    List<Double> x_LIST, y_LIST;

    /**
     * they are references to objects under the control of the chromosomes in the population
     * and having them means we can give the chromosomes new values to help them do their job:
     * figuring out what the formula is that will get us the desired output.
     */
    Variable _xVariable, _yVariable;

    /**
     * A constructor. It construct a new instance of MyFitnessFunction.
     *
     * @param x_LIST
     *            input
     * @param y_LIST
     *            output
     * @param _xVariable
     * @param _yVariable
     */
    public MyFitnessFunction(
            List<Double> x_LIST, List<Double> y_LIST, Variable _xVariable,
            Variable _yVariable) {
        // TODO Auto-generated constructor stub
        this._xVariable = _xVariable;
        this._yVariable = _yVariable;
        this.x_LIST = x_LIST;
        this.y_LIST = y_LIST;

    }

    /**
     * This function is for evaluating in order to choose the instance.
     * 
     * @see org.jgap.gp.GPFitnessFunction#evaluate(org.jgap.gp.IGPProgram)
     */
    @Override
    protected double evaluate(IGPProgram program) {
        double result = 0.0f;
        long longResult = 0;
        for (int i = 0; i < x_LIST.size(); i++) {
            // Set the input values
            _xVariable.set(x_LIST.get(i));
            // _yVariable.set(_input2[i]);//in our assignment, y is not input

            // Execute the genetically engineered algorithm

            // by looking at the source code and the code from the tutorial website ,
            // I can obtain NO_ARG is new Object[0]
            long value = (long) program.execute_double(0, new Object[0]);

            // The closer longResult gets to 0 the better the algorithm.
            longResult += Math.abs(value - y_LIST.get(i));
        }

        result = longResult;

        return result;
    }

}
