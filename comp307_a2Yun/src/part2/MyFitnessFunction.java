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
 * 
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

    Variable _xVariable, _yVariable;

    public MyFitnessFunction(
            List<Double> x_LIST, List<Double> y_LIST, Variable _xVariable,
            Variable _yVariable) {
        // TODO Auto-generated constructor stub
        this._xVariable = _xVariable;
        this._yVariable = _yVariable;
        this.x_LIST = x_LIST;
        this.y_LIST = y_LIST;

    }

    @Override
    protected double evaluate(IGPProgram arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}
