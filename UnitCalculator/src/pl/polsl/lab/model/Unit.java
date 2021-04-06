package pl.polsl.lab.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class representing unit with its conversions
 * @author Jeremi Kot
 * @version 1.0
 */
public class Unit {
    /**
     * Map of possible conversions to other units and it's symbols being a key
     */
    private HashMap<String, Conversion> conversions;
    /**
     * Symbol of unit
     */
    private String symbol;

    /**
     * Instantiates a new Unit.
     *
     * @param symbol symbol of unit
     */
    public Unit(String symbol) {
        this.conversions = new HashMap<>();
        this.symbol = symbol;
    }

    /**
     * Instantiates a new Unit.
     *
     * @param symbol   symbol of new unit
     * @param unitB    unit that new unit is defined on
     * @param factor   factor of conversion, value that unit is multiplied by while conversion
     * @param constant constant of conversion, value that is added to the unit while conversion
     */
    public Unit(String symbol, Unit unitB, double factor, double constant) {
        this.symbol = symbol;
        this.conversions = new HashMap<>();
        this.conversions.put(unitB.getSymbol(), new Conversion(unitB.getSymbol(), symbol));
        this.conversions.get(unitB.getSymbol()).putMultiplyOperation(factor);
        this.conversions.get(unitB.getSymbol()).putAddOperation(constant);

        unitB.conversions.put(symbol, new Conversion(symbol, unitB.getSymbol()));
        unitB.conversions.get(symbol).putDivideOperation(factor);
        unitB.conversions.get(symbol).putSubtractOperation(constant);

    }


    /**
     * Gets conversions.
     *
     * @return conversions hashmap
     */
    public HashMap<String, Conversion> getConversions() {
        return conversions;
    }

    /**
     * Gets symbol.
     *
     * @return symbol of unit
     */
    public String getSymbol() {
        return symbol;
    }

}
