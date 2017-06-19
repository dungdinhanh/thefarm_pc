package Food;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.AlertConfigure;

public class SetFoodController {
    private double rice;
    private double meat;
    private double grass;
    private double general;

    @FXML
    private TextField riceInput;

    @FXML
    private TextField grassInput;

    @FXML
    private TextField generalInput;

    @FXML
    private TextField meatInput;

    @FXML
    private Button done;

    @FXML
    void doneAction(ActionEvent event) {
        if(checkData() == false)return;
        Stock stock = new Stock();
        stock.setGeneralQuantity(general);
        stock.setGrassQuantity(grass);
        stock.setMeatQuantity(meat);
        stock.setRiceQuantity(rice);
        new AlertConfigure().loadAlertInfo("SUCCESS!", "You have just set your stock");
        Stage currentStage = (Stage)riceInput.getScene().getWindow();
        currentStage.close();
    }

    public boolean checkData() {
        try {
            if (riceInput.getText() == null) rice = 0;
            else rice = Double.parseDouble(riceInput.getText());
            if (grassInput.getText() == null) grass = 0;
            else grass = Double.parseDouble(grassInput.getText());
            if (meatInput.getText() == null) meat = 0;
            else meat = Double.parseDouble(meatInput.getText());
            if (generalInput.getText() == null) general = 0;
            else general = Double.parseDouble(generalInput.getText());
            return true;
        }catch (NumberFormatException e){
            e.printStackTrace();
            new AlertConfigure().loadAlertInfo("Fail to send request", "Some fields are not number");
            return false;
        }
    }

}
