package MainScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.lang.management.PlatformLoggingMXBean;
import java.net.URL;
import java.util.ResourceBundle;

public class MainBoardController implements Initializable {

    @FXML
    private AnchorPane parentContainer;

    @FXML
    private MaterialDesignIconView iconClose;

    @FXML
    private HBox menusHolder;

    @FXML
    private AnchorPane CATTLE;

    @FXML
    private Group groupCattle;

    @FXML
    private AnchorPane FINANCE;

    @FXML
    private Group groupFinance;

    @FXML
    private AnchorPane FOOD;

    @FXML
    private Group groupFood;

    @FXML
    private AnchorPane MEDICAL_CARE;

    @FXML
    private Group groupMedical;

    @FXML
    private ImageView iconLogOut;

    private AnchorPane addition = new AnchorPane();

    public void initialize(URL url, ResourceBundle resourceBundle){
        setUpRipplers();
    }

    public void setUpRipplers(){
        JFXRippler rippler1 = new JFXRippler(CATTLE, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler2 = new JFXRippler(FINANCE, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler3 = new JFXRippler(FOOD, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler4 = new JFXRippler(MEDICAL_CARE, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        menusHolder.getChildren().addAll(rippler1, rippler2, rippler3, rippler4);
    }

    @FXML
    void hideStage(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void openCattle(MouseEvent event) throws Exception {
        openStage("/Cattle/CattleMain.fxml");

    }

    @FXML
    void openFinance(MouseEvent event) throws Exception {
        openStage("/Finance/FinanceMain.fxml");

    }

    @FXML
    void openFood(MouseEvent event) throws Exception {
        openStage("/Food/FoodMain.fxml");
    }

    @FXML
    void openMedicalCare(MouseEvent event) throws Exception {
        openStage("/Medical/MedicalMain.fxml");

    }

    @FXML
    void showLogOutPopUp(){
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Log out");
        Tooltip.install(iconLogOut, tooltip);

    }
    @FXML
    void logOut()throws Exception{
        openStage("LogSign.fxml");

    }


    public void openStage(String fxml) throws Exception{
        Stage currentStage = (Stage) FINANCE.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = new Stage(StageStyle.UNDECORATED);
        //window.setTitle("The Farm");
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        currentStage.hide();
    }

}
