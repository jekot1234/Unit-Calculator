package pl.polsl.lab.model;

/**
 * Class representing basic operation of addition
 * @author Jeremi Kot
 * @version 1.1
 */
class Add extends Calculator {

    /**
     * Instantiates a new Add.
     *
     * @param element element of addition operation
     */
    public Add(double element) {
        this.operation = (a, b) -> a + b;
        this.argument = element;
    }

}
