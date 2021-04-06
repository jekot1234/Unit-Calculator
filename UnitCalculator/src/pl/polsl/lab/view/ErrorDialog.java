package pl.polsl.lab.view;

import javafx.scene.control.Alert;

public class ErrorDialog {

    public Alert alert;
    public ErrorDialog(String title, String message){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
