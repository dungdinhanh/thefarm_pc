package logic;

import javafx.scene.control.Alert;

/**
 * Created by Dung Dinh on 5/24/2017.
 */
public class AlertConfigure {

    public void loadAlertInfo(String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The Farm Message");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void loadAlertWarning(String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("The Farm Message");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void loadAlertError(String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("The Farm Message");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
