package pl.polsl.lab;

/**
 * The type Input error exception.
 * @author Jeremi Kot
 * @version 1.0
 */
public class InputErrorException extends Exception{

    /**
     * Instantiates a new Input error exception.
     *
     * @param errorMessage the error message
     */
    public InputErrorException(String errorMessage){
        super(errorMessage);
    }

}
