package pl.polsl.lab.model;

import pl.polsl.lab.InputErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Class representing data model of application
 * @author Jeremi Kot
 * @version 1.0
 */
public class Model {

    /**
     * Map containing pair of units and it's symbols being a key
     */
    private HashMap<String, Unit> unitMap;

    /**
     * Instantiates a new Model.
     */
    public Model(){
        unitMap = new HashMap<>();
    }

    /**
     * Execute query string.
     *
     * @param input string containing query
     * @return string containing query and result
     * @throws InputErrorException input error exception thrown when bad input is occurs
     */
    public String executeQuery(String input) throws InputErrorException {

        double result;
        String[] inputs;
        if(input != null){
            inputs = input.split("\\s+");
        } else {
            throw new InputErrorException("Input is null");
        }


        if(input.isEmpty()){
            throw new InputErrorException("Input is blank");
        }
        else {
            double value = 0.0;

            try{
                value = Double.parseDouble(inputs[0]);
            }
            catch (Exception e){
                throw new InputErrorException("Input is invalid");
            }

            boolean isOnList = false;

            if(inputs.length < 4) {
                throw new InputErrorException("Input is invalid");
            }

            if(unitMap.containsKey(inputs[1]))
                isOnList = true;


            if(!isOnList){
                throw new InputErrorException("Symbol " + inputs[1] + " not found");
            }
            if(!inputs[2].contains("=")){
                throw new InputErrorException("No '=' sign");
            }

            isOnList = false;

            if(unitMap.containsKey(inputs[4]))
                isOnList = true;

            if(!isOnList){
                throw new InputErrorException("Symbol " + inputs[3] + " not found");
            }

            result = calculate(inputs[1], inputs[4], value);
        }

        return inputs[0] + " " + inputs[1] + " = " +
                result + " " + inputs[4];
    }

    /**
     * Executes query string.
     *
     * @param symbolA symbol of unit that result is calculated from
     * @param symbolB symbol of unit of result
     * @param value value that result is calculated from
     * @return result of conversion
     */
    private double calculate(String symbolA, String symbolB, double value){

        double result = value;

        for(Calculator op: getUnitConversion(symbolA, symbolB).getCalculations()){
            result = op.calculate(result, op.operation);
        }

        return result;
    }

    /**
     * Gets conversion from unit A to unit B
     * @param symbolA string containing symbol of unit A
     * @param symbolB string containing symbol of unit B
     * @return  Conversion
     */
    private Conversion getUnitConversion(String symbolA, String symbolB){
        return unitMap.get(symbolA).getConversions().get(symbolB);
    }

    /**
     * Adds a new unit to the model.
     * @param symbolB  symbol of new unit
     * @param symbolA  symbol of unit that new unit is defined on
     * @param factor   factor of conversion, value that unit is multiplied by while conversion
     * @param constant constant of conversion, value that is added to the unit while conversion
     * @throws InputErrorException input error exception thrown when bad input occurs
     */
    public void addUnit(String symbolB, String symbolA, String factor, String constant) throws InputErrorException {

        double dFactor;
        double dConstant;

        try{
            dFactor = Double.parseDouble(factor);
            dConstant = Double.parseDouble(constant);
        } catch (Exception e){
            throw new InputErrorException("Invalid input format");
        }

        if(symbolA == null || symbolA.equals(" ") || symbolA.isEmpty()){
            throw new InputErrorException("Invalid symbol A input");
        }

        if(symbolB == null || symbolB.equals(" ") || symbolB.isEmpty()){
            throw new InputErrorException("Invalid symbol B input");
        }

        if(unitMap.isEmpty()|| !unitMap.containsKey(symbolB)){

            Unit unitA = new Unit(symbolA);
            unitMap.put(unitA.getSymbol(), unitA);
            Unit unitB = new Unit(symbolB, unitA, dFactor, dConstant);
            unitMap.put(unitB.getSymbol(), unitB);
        } else if(unitMap.containsKey(symbolB)){

            if(unitMap.get(symbolB).getConversions().containsKey(symbolA)){
                throw new InputErrorException("Symbol " + symbolB + " is already defined in relation " + symbolA);
            }

            Unit unitB = unitMap.get(symbolB);
            Unit unitA = new Unit(symbolA, unitB, dFactor, dConstant);
            unitMap.put(unitA.getSymbol(), unitA);
        }
    }

    /**
     * Gets unit map.
     *
     * @return hash map of units
     */
    public HashMap<String, Unit> getUnitMap() { return unitMap; }

    /**
     * Gets list of all defined conversion
     * @return ArrayList containing conversions
     */
    public ArrayList<Conversion> getConversionList(){
        ArrayList<Conversion> conversionArrayList = new ArrayList<>();
        for(Unit u : unitMap.values()){
            conversionArrayList.addAll(u.getConversions().values());
        }
        return  conversionArrayList;
    }
}
