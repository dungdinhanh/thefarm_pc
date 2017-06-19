package Food;

import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Var;
import logic.jsonDeal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FoodMainController implements Initializable{

    public void initialize(URL url, ResourceBundle resourceBundle){
        setUpRipples();
        stockStatus();
    }

    private static double rice;
    private static double grass;
    private static double general;
    private static double meat;

    @FXML
    private javafx.scene.text.Text grassText;

    @FXML
    private javafx.scene.text.Text generalText;

    @FXML
    private javafx.scene.text.Text meatText;

    @FXML
    private javafx.scene.text.Text riceText;

    @FXML
    private ProgressBar grassBar;

    @FXML
    private ProgressBar meetBar;

    @FXML
    private ProgressBar generalBar;

    @FXML
    private ProgressBar riceBar;

    @FXML
    private Pane stock;

    @FXML
    private StackPane rootPane;

    @FXML
    private HBox hoxImages;

    @FXML
    private ImageView imgInfo;

    @FXML
    private ImageView imgHome;

    @FXML
    private MaterialDesignIconView iconClose;

    @FXML
    private HBox activityPane;

    @FXML
    private Pane setFoodPane;

    @FXML
    private Pane foodOutcomePane;

    @FXML
    private Pane stockPane;

    @FXML
    private Pane buyFoodPane;

    @FXML
    private VBox ricePane;

    @FXML
    private VBox grassPane;

    @FXML
    private VBox meatPane;

    @FXML
    private VBox generalPane;


    public void setUpRipples(){
        JFXRippler rippler = new JFXRippler(setFoodPane, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler1 = new JFXRippler(foodOutcomePane, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler2 = new JFXRippler(stockPane, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler3 = new JFXRippler(buyFoodPane, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);

        activityPane.getChildren().addAll(rippler, rippler1, rippler2, rippler3);
    }

    @FXML
    void buyFood(MouseEvent event) {
        try {
            openDialog("BuyFoodDialog.fxml");
        }catch (Exception e){
            System.out.println("Buy Food error");
        }
    }

    @FXML
    void closeStage(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void foodOutcome(MouseEvent event) {
        try {
            openDialog("OutFood.fxml");
        }catch (Exception e){
            System.out.println("Error OutFood.fxml");
        }
    }

    @FXML
    void goHome(MouseEvent event) {
        try {
            openStage("/MainScreen/MainBoard.fxml");
        }catch (Exception e){
            System.out.println("Main Board error");
        }
    }

    @FXML
    void setFood(MouseEvent event) {
        try{
            openDialog("SetFood.fxml");
        }catch (Exception e){
            System.out.println("Set food fail");
        }

    }

    @FXML
    void showInfo(MouseEvent event) {

    }

    @FXML
    void stockStatus() {
        HashMap<String, String>request = new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        try {
            JSONArray jsonArray = new jsonDeal().loadJSON(Var.METHOD_GET_FOOD, request);
            for(Object temp: jsonArray){
                JSONObject json = (JSONObject)temp;
                Food food = new Food();
                food.setFoodID(Integer.parseInt((String)json.get("Food_ID")));
                food.setQuantity(Double.parseDouble((String)json.get("Quantity")));
                food.setFoodName();
                if(food.getFoodID() == 1){
                    rice = food.getQuantity();
                    riceBar.setProgress(food.getQuantity()/(new Stock().getRiceQuantity()));
                   // riceText.setText("Rice: " + rice + "/" + new Stock().getRiceQuantity());
                }
                if(food.getFoodID() == 2){
                    grass = food.getQuantity();
                    grassBar.setProgress(food.getQuantity()/(new Stock().getGrassQuantity()));
                   // grassText.setText("Grass " + grass + "/" + new Stock().getGrassQuantity());
                }
                if(food.getFoodID() == 3){
                    general = food.getQuantity();
                    generalBar.setProgress(food.getQuantity()/ (new Stock().getGeneralQuantity()));
                   // generalText.setText("General: " + general + "/" + new Stock().getGeneralQuantity());

                }
                if(food.getFoodID() == 4){
                    meat = food.getQuantity();
                    meetBar.setProgress(food.getQuantity()/(new Stock().getMeatQuantity()));
                 //   meatText.setText("Meat: " + meat + "/" +new Stock().getMeatQuantity());
                }

            }

        }
        catch (Exception e){
            System.out.println("json error\n");
        }
    }

    public void riceStat(){

    }

    public void meatStat(){


    }

    public void grassStat(){

    }

    public void generalStat(){

    }


    public void openStage(String fxml) throws Exception{
        Stage currentStage = (Stage) activityPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.show();
        currentStage.close();
    }

    public void openDialog(String fxml) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = new Stage(StageStyle.DECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.showAndWait();
    }

}
