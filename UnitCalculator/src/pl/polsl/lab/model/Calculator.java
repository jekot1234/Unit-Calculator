package pl.polsl.lab.model;

/**
 * The interface Calculator representing basic operations.
 * @author Jeremi Kot
 * @version 1.1
 */

public abstract class Calculator {

    public Operation operation;
    public double argument;

    /**
     * Interface for lambda expression
     */
    interface Operation {
        double operation(double a, double b);
    }

    /**
     * Operation implementation using lambda expression
     * @param a argument of operation
     * @param op operation
     * @return result of operation
     */
    double calculate(double a, Operation op) {
        return op.operation(a, argument);
    }

}




