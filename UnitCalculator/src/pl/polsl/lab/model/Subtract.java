package pl.polsl.lab.model;

/**
 * Class representing basic operation of subtraction
 * @author Jeremi Kot
 * @version 1.1
 */
class Subtract extends Calculator {

    /**
     * Instantiates a new Subtract.
     *
     * @param subtrahend subtrahend of subtraction operation
     */
    public Subtract(double subtrahend) {
        this.argument = subtrahend;
        this.operation = (a, b) -> a - b;
    }

}
