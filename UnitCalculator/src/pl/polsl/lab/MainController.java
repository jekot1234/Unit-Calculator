package pl.polsl.lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.polsl.lab.model.Model;
import pl.polsl.lab.view.ErrorDialog;

import java.io.IOException;

public class MainController {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private TextField inputTextField;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private Button calculateButton;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button unitsButton;

    /**
     * Calculate button pressed event method
     * @param event Action event class
     */
    @FXML
    private void calculate(ActionEvent event){
        try{
            resultTextArea.setText(model.executeQuery(inputTextField.getText()) + "\n"
                    + resultTextArea.getText());
        } catch (InputErrorException exception){
            new ErrorDialog("Input error", exception.getMessage());
        }
    }

    /**
     * Clear button pressed event method
     * @param event Action event class
     */
    @FXML
    private void clearOutput(ActionEvent event) {
        resultTextArea.setText("");
    }

    /**
     * Add button pressed event method
     * @param event Action event class
     */
    @FXML
    private void openAddWindow(ActionEvent event) {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddView.fxml"));

        try {
            root = loader.load();
            AddController controller = loader.getController();
            controller.setModel(this.model);
            Stage stage = new Stage();
            stage.setTitle("Add new unit");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Units button pressed event method
     * @param event Action event class
     */
    @FXML
    private void openFindUnitsWindow(ActionEvent event) {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnitsView.fxml"));
        try {
            root = loader.load();
            UnitsController controller = loader.getController();
            controller.setModel(this.model);
            controller.init();
            Stage stage = new Stage();
            stage.setTitle("Find unit");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
