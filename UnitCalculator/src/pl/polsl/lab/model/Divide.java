package pl.polsl.lab.model;

/**
 * Class representing basic operation of division
 * @author Jeremi Kot
 * @version 1.1
 */
class Divide extends Calculator {

    /**
     * Instantiates a new Divide.
     *
     * @param divider divider of division operation
     */
    public Divide(double divider){
        this.operation = (a, b) -> a / b;
        this.argument = divider;
    }
}
