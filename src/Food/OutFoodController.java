package Food;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.AlertConfigure;
import logic.Var;
import logic.jsonDeal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class OutFoodController {

    private double meat;
    private double grass;
    private double general;
    private double rice;

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
        checkData();
        HashMap<String, String> request = new HashMap<>();
        JSONObject riceObject = new JSONObject();
        JSONObject meatObject = new JSONObject();
        JSONObject grassObject = new JSONObject();
        JSONObject generalObject = new JSONObject();

        riceObject.put(Var.KEY_FOOD_ID, Var.FOOD_RICE);
        riceObject.put(Var.KEY_FOOD_AMOUNT, rice);
        meatObject.put(Var.KEY_FOOD_ID, Var.FOOD_MEAT);
        meatObject.put(Var.KEY_FOOD_AMOUNT, meat);
        grassObject.put(Var.KEY_FOOD_ID, Var.FOOD_GRASS);
        grassObject.put(Var.KEY_FOOD_AMOUNT, grass);
        generalObject.put(Var.KEY_FOOD_ID, Var.FOOD_GENERAL);
        generalObject.put(Var.KEY_FOOD_AMOUNT, general);

        JSONArray foodList = new JSONArray();
        foodList.add(riceObject);
        foodList.add(meatObject);
        foodList.add(grassObject);
        foodList.add(generalObject);
        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_FOOD_LIST, foodList.toString());
        try {
            JSONObject json = new jsonDeal().loadJSON(Var.METHOD_OUT_FOOD, request);
            boolean result = (boolean) json.get("out_food");
            if(result == true){
                new AlertConfigure().loadAlertInfo("SUCCESSFULLY!", "You have just taken out some amount of food");

            }
            else
                new AlertConfigure().loadAlertError("FAILURE", "You have just failed to take out food");
        }
        catch(Exception e){
            System.out.println("JSON error");
        }
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
            new AlertConfigure().loadAlertInfo("Fail to send request", "Some fields are left blank");
            return false;
        }
    }

}
