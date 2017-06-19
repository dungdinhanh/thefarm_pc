package Cattle;

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


public class CattleMainController implements Initializable {

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    @FXML
    private StackPane rootPane;

    @FXML
    private MaterialDesignIconView iconDialog;

    @FXML
    private HBox hoxImages;

    @FXML
    private ImageView imgInfo;

    @FXML
    private ImageView imgHome;

    @FXML
    private ImageView imgExit;

    @FXML
    private Pane popUpPane;

    @FXML
    private HBox boxPopUpMenusHolder;

    @FXML
    private VBox menuBook;

    @FXML
    private VBox menuDetail;

    @FXML
    private VBox menuList;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Rectangle shape1;

    @FXML
    private Rectangle shape2;

    @FXML
    private Rectangle shape3;

    @FXML
    private Rectangle shape4;

    private static int animalID;

    public void setAnimalID(int animalID)
    {
        this.animalID = animalID;
    }

    public int getAnimalID(){
        return animalID;
    }

    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void goHome(MouseEvent event) throws Exception {
        openStage("/MainScreen/MainBoard.fxml");
    }

    @FXML
    void goToBuffalo(MouseEvent event) {
        try {
            animalID = 2;
            openStage("Cattle.fxml");

        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
    }

    @FXML
    void goToChicken(MouseEvent event) {
        try{
            animalID = 4;
            openStage("Cattle.fxml");


        }
        catch (Exception e)
        {
            System.out.println("chicken error");
        }
    }

    @FXML
    void goToCow(MouseEvent event) {
        try{
            animalID = 3;
            openStage("Cattle.fxml");

        }
        catch(Exception e)
        {
            System.out.println("Cow error");
        }
    }

    @FXML
    void goToPig(MouseEvent event) throws  Exception{
        try{
            animalID = 1;
            openStage("Cattle.fxml");

        }
        catch (Exception e)
        {
            System.out.println("Pig error");
        }
//        animalID = 1;
//        openStage("Cattle.fxml");
    }
    private static JFXPopup staticJFXPopup =new JFXPopup();
    @FXML
    void openDialog(MouseEvent event) {
        JFXPopup fXPopup = new JFXPopup();
        fXPopup.setPopupContent(popUpPane);
        fXPopup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, 10, 70);
        staticJFXPopup = fXPopup;
    }

    @FXML
    void showBookingNode(MouseEvent event) {

    }

    @FXML
    void showDetailsNode(MouseEvent event) {

    }

    @FXML
    void showInfo(MouseEvent event) {

    }

    @FXML
    void showListsNode(MouseEvent event) {

    }

    @FXML
    public void logOut() throws Exception{
        openStage("/MainScreen/LogSign.fxml");
    }


    //AnchorPane addition = new AnchorPane();
    public void openStage(String fxml) throws Exception
    {
        Stage currentStage = (Stage) hoxImages.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();
        currentStage.close();
    }



}
