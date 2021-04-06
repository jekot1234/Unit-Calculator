package pl.polsl.lab.model;

import java.util.ArrayList;

/**
 * Class representing conversion of one unit to another.
 * @author Jeremi Kot
 * @version 2.0
 */
public class Conversion {

    /**
     * List containing basic operations required during conversion
     */
    private ArrayList<Calculator> calculations;
    /**
     * String containing symbol of target of conversion
     */
    private String unit;
    /**
     * String containing symbol of origin of conversion
     */
    private String originUnit;

    /**
     * Instantiates a new Conversion.
     *
     * @param key String containing symbol of target of conversion
     */
    public Conversion(String key, String originUnit){
        this.unit = key;
        this.originUnit = originUnit;
        this.calculations = new ArrayList<>();
    }

    /**
     * Put add operation.
     *
     * @param arg the argument of operation
     */
    public void putAddOperation(Double arg){
        calculations.add(new Add(arg));
    }

    /**
     * Put subtract operation.
     *
     * @param arg the argument of operation
     */
    public void putSubtractOperation(Double arg){
        calculations.add(new Subtract(arg));
    }

    /**
     * Put multiply operation.
     *
     * @param arg the argument of operation
     */
    public void putMultiplyOperation(Double arg){
        calculations.add(new Multiply(arg));
    }

    /**
     * Put divide operation.
     *
     * @param arg the argument of operation
     */
    public void putDivideOperation(Double arg){
        calculations.add(new Divide(arg));
    }

    /**
     * Get calculations array list.
     *
     * @return the array list of calculations
     */
    public ArrayList<Calculator> getCalculations(){
        return calculations;
    }

    public String getUnit() { return unit; }
    public String getFactor(){
        Calculator op = calculations.get(0);
        Double factor = op.calculate(1, op.operation);
        return(Double.toString(factor));
    }
    public String getConstant(){
        Calculator op = calculations.get(1);
        return(Double.toString(op.calculate(0, op.operation)));
    }
    public String getOriginUnit() {
        return originUnit;
    }
}
