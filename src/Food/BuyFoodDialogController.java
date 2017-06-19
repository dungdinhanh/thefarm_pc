package Food;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.AlertConfigure;
import logic.Var;
import logic.jsonDeal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class BuyFoodDialogController {

    @FXML
    private TextField riceInput;

    private double rice =0 ;

    @FXML
    private TextField grassInput;

    private double grass = 0;

    @FXML
    private TextField generalInput;

    private double general = 0;

    @FXML
    private TextField meatInput;

    private double meat = 0;

    @FXML
    private Button done;

    @FXML
    void doneAction(ActionEvent event) {
        if(checkData()== false)return;
        if(checkStock() == false){
            new AlertConfigure().loadAlertError("Fail", "The stock is full cant not send request");


            return;
        }
        HashMap<String, String> request = new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        JSONArray jsonArray = new JSONArray();
        JSONObject riceObject = new JSONObject();
        JSONObject grassObject = new JSONObject();
        JSONObject generalObject = new JSONObject();
        JSONObject meatObject = new JSONObject();

        riceObject.put(Var.KEY_FOOD_ID, String.valueOf(Var.FOOD_RICE));
        riceObject.put(Var.KEY_FOOD_AMOUNT, String.valueOf(rice));
        grassObject.put(Var.KEY_FOOD_ID, String.valueOf(Var.FOOD_GRASS));
        grassObject.put(Var.KEY_FOOD_AMOUNT, String.valueOf(grass));
        generalObject.put(Var.KEY_FOOD_ID, String.valueOf(Var.FOOD_GENERAL));
        generalObject.put(Var.KEY_FOOD_AMOUNT, String.valueOf(general));
        meatObject.put(Var.KEY_FOOD_ID, String.valueOf(Var.FOOD_MEAT));
        meatObject.put(Var.KEY_FOOD_AMOUNT, String.valueOf(meat));

        jsonArray.add(riceObject);
        jsonArray.add(grassObject);
        jsonArray.add(generalObject);
        jsonArray.add(meatObject);
        request.put(Var.KEY_FOOD_LIST, jsonArray.toString());
        try {
            JSONObject jsonResult = new jsonDeal().loadJSON(Var.METHOD_BUY_FOOD, request);

            new AlertConfigure().loadAlertInfo("RESULT", "Rice: " + jsonResult.get("rice_mess") + "\n" + "Grass: " + jsonResult.get("grass_mess")
            + "\n"
            + "General: " + jsonResult.get("general_mess") + "\n"
            + "Meat: " + jsonResult.get("meat_mess") + "\n");
            Stage currentStage  = (Stage) generalInput.getScene().getWindow();
            currentStage.close();
        }catch (Exception e){
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

    public boolean checkStock() {
        Stock stock = new Stock();
        HashMap<String, String> request = new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        try {
            JSONArray getData = new jsonDeal().loadJSON(Var.METHOD_GET_FOOD, request);
            for (Object a : getData) {
                JSONObject json = (JSONObject) a;
                Food food = new Food();
                food.setFoodID(Integer.parseInt((String) json.get("Food_ID")));
                food.setQuantity(Double.parseDouble((String) json.get("Quantity")));
                if (food.getFoodID() == 1) {
                    double test = food.getQuantity() + rice;
                    if (test > stock.getRiceQuantity()) return false;
                } else if (food.getFoodID() == 2) {
                    double test = food.getQuantity() + grass;
                    if (test > stock.getGrassQuantity()) return false;
                } else if (food.getFoodID() == 3) {
                    double test = food.getQuantity() + general;
                    if (test > stock.getGeneralQuantity()) return false;
                } else {
                    double test = food.getQuantity() + meat;
                    if (test > stock.getMeatQuantity()) return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("JSON error\n");
            return false;

        }
    }

}
