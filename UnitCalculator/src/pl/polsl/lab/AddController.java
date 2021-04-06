package pl.polsl.lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.polsl.lab.InputErrorException;
import pl.polsl.lab.model.Model;
import pl.polsl.lab.view.ErrorDialog;

/**
 * Controller of add conversion view file
 * @author Jeremi Kot
 * @version 2.0
 */
public class AddController {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private TextField unitATextField;

    @FXML
    private TextField unitBTextField;

    @FXML
    private TextField factorTextField;

    @FXML
    private TextField constantTextField;

    @FXML
    private Button acceptButton;

    /**
     * Accept button pressed event method
     * @param event Action event class
     */
    @FXML
    private void accept(ActionEvent event) {

        try{
            model.addUnit( unitATextField.getText(), unitBTextField.getText(),
                    factorTextField.getText(), constantTextField.getText());

            Stage stage = (Stage) acceptButton.getScene().getWindow();
            stage.close();
        }
        catch (InputErrorException exception) {
            new ErrorDialog("Input error", exception.getMessage());
        }

    }
}
