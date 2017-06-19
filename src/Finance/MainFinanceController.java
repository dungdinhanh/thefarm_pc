package Finance;

import com.sun.org.apache.xpath.internal.operations.Mod;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.AlertConfigure;
import logic.Var;
import logic.jsonDeal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainFinanceController implements Initializable{

    public void initialize(URL url, ResourceBundle resourceBundle){
        updateBalance();
    }
    @FXML
    private TextField amountAdding;

    @FXML
    private TextField amountSub;

    @FXML
    private javafx.scene.text.Text currentBalanceText;

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
    void closeStage(MouseEvent event) {
            Platform.exit();
    }

    @FXML
    void goHome(MouseEvent event) {
        try {
            openStage("/MainScreen/MainBoard.fxml");
        }catch (Exception e)
        {
            System.out.println("Error in loading MainBoard.fxml");
        }
    }

    @FXML
    void showInfo(MouseEvent event) {

    }

    @FXML
    void setUpTransactionHistory(){
        try{
        openDialog("Transaction.fxml");
        }
        catch (Exception e){
            System.out.println("Hello world");
        }
    }

    public void updateBalance(){
        HashMap<String, String>request= new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        try {
            JSONObject json = new jsonDeal().loadJSON(Var.METHOD_BALANCE, request);
            String balance = (String)json.get("Balance");
            try{
                Double.parseDouble(balance);
            }catch (NumberFormatException a)
            {
                return;
            }
            currentBalanceText.setText("Current Balance: " + balance);
        }catch (Exception e){
            System.out.println("JSON error");
        }
    }

    @FXML
    void setUpCurrentBalance(){
            updateBalance();
//            try {
//                openDialog("Balance.fxml");
//            }
//            catch(Exception e)
//            {
//                System.out.println("Error while opening Balance.fxml");
//            }
    }

    @FXML
    void setUpAddMoney(){
        if(amountAdding.getText() == null)return;
        HashMap<String, String>request = new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_AMOUNT, amountAdding.getText());
        try {
            JSONObject json = new jsonDeal().loadJSON(Var.METHOD_ADD_MONEY, request);
            boolean result = (boolean) json.get("add_money");
            if(result == true) new AlertConfigure().loadAlertInfo("SUCCESSFUL!", "You have just add"
        + amountAdding.getText() + " to your account");
            else new AlertConfigure().loadAlertInfo("FAILURE", "You can not perform this action");
        }catch (Exception e)
        {
            System.out.println("JSON error");
        }
    }

    @FXML
    void setUpWithDraw() throws  Exception{
        if(amountSub.getText() == null)return;
        HashMap<String, String>request = new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_AMOUNT, amountSub.getText());

        JSONObject json = new jsonDeal().loadJSON(Var.METHOD_WITHDRAW, request);
        boolean result = (boolean)json.get("withdraw");
        if(result == true) new AlertConfigure().loadAlertInfo("SUCCESSFUL!", "You have just withdraw"+
        amountSub.getText() + " from your account");
        else new AlertConfigure().loadAlertInfo("FAILURE!", "You can not perform this action");
    }
    public void openStage(String fxml) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage currentStage = (Stage)imgHome.getScene().getWindow();
        Scene scene = new Scene(root);
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        currentStage.hide();
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
