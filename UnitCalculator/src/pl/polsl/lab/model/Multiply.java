package pl.polsl.lab.model;

/**
 * Class representing basic operation of multiplication
 * @author Jeremi Kot
 * @version 1.1
 */
class Multiply extends Calculator {

    /**
     * Instantiates a new Multiply.
     *
     * @param factor factor of division operation
     */
    public Multiply(double factor){
        this.operation = (a, b) -> a * b;
        this.argument = factor;
    }
}
