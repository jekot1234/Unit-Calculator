package pl.polsl.lab;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.polsl.lab.model.Conversion;
import pl.polsl.lab.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller of unit search conversion view file
 * @author Jeremi Kot
 * @version 2.0
 */
public class UnitsController {

    private Model model;

    @FXML
    private TextField textField;

    @FXML
    private Button findButton;

    @FXML
    private TableView<Conversion> unitsTable;

    @FXML
    private TableColumn<Conversion, String> constantColumn;
    @FXML
    private TableColumn<Conversion, String> fromColumn;
    @FXML
    private TableColumn<Conversion, String> factorColumn;
    @FXML
    private TableColumn<Conversion, String> toColumn;

    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Initialisation of table view
     */
    public void init(){
        constantColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getConstant()));
        fromColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getOriginUnit()));
        factorColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getFactor()));
        toColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUnit()));
        unitsTable.getItems().setAll(model.getConversionList());
    }

    /**
     * Find button pressed event method
     * @param event Action event class
     */
    @FXML
    void find(ActionEvent event){

        String input = textField.getText();
        List<String> filter = model.getUnitMap().keySet().stream().filter(p -> p.contains(input)).collect(Collectors.toList());

        ArrayList <Conversion> filteredList = new ArrayList<>(model.getConversionList());

        for(String unit : filter){
            filteredList.removeIf(conversion -> !conversion.getUnit().equals(unit));
        }

        if(!input.isEmpty()){
            unitsTable.getItems().setAll(filteredList);
        }
        else {
            unitsTable.getItems().setAll(model.getConversionList());
        }

    }
}
