package Cattle;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import logic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import logic.Var;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddListDialogController extends CattleMainController implements Initializable{

    @FXML
    private TextField sourceInput;

    @FXML
    private TextField minHealthInput;

    @FXML
    private TextField minWeightInput;

    @FXML
    private TextField numberInput;

    @FXML
    private ComboBox<String> genderChoice;

    @FXML
    private Button done;

    @FXML
    void doneAction(ActionEvent event) {
        HashMap<String, String> request = new HashMap<>();
        request.put(Var.KEY_HEALTH_INDEX, minHealthInput.getText());
        request.put(Var.KEY_WEIGHT, minWeightInput.getText());
        request.put(Var.KEY_SOURCE, sourceInput.getText());
        int codeGender;
        if(genderChoice.getValue() == null) return;
        if(genderChoice.getValue().equals("Male")){
            codeGender = 1;
        }else codeGender= 0;
        request.put(Var.KEY_SEX, String.valueOf(codeGender));
        request.put(Var.KEY_NUMBER, numberInput.getText());
        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_ANIMAL_ID, String.valueOf(super.getAnimalID()));
//        System.out.println(""+ super.getAnimalID());

        try {


            JSONObject result = new jsonDeal().loadJSON(Var.METHOD_ADD_LIST, request);
            Object confirm = (Object) result.get("add_list");
            if(confirm instanceof String){
                String confirmMess = (String)confirm;
                new AlertConfigure().loadAlertWarning("Failure", confirmMess);
            }
            //boolean confirm = (boolean) result.get("add_list");
            else {
                Boolean a = (Boolean)confirm;


                if (a == true) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("The Farm Message");
                    alert.setHeaderText("SUCCESSFULLY");
                    alert.setContentText("You have just add cattles to your farm");
                    alert.showAndWait();
                    Stage currentStage = (Stage) minHealthInput.getScene().getWindow();
                    currentStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("The Farm Message");
                    alert.setHeaderText("FAIL");
                    alert.setContentText("your balance is not enough to buy those");
                    alert.showAndWait();
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle){
        genderChoice.getItems().clear();
        genderChoice.getItems().addAll("Male", "Female");
    }



}
