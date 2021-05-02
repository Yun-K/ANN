package part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

public class JGAPTest {

    List<Double> X_LIST = new ArrayList<>();

    List<Double> Y_LIST = new ArrayList<>();

    private void readFile() {
        String filePath = "regression.txt";
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
    }

    public static void main(String args[]) throws InvalidConfigurationException {
        GPProblem problem = new GPProblem1();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(100);
        System.out.println("Yay, I can run!");
    }
}
