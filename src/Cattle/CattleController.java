package Cattle;

/**
 * Created by Dung Dinh on 5/14/2017.
 */

import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.*;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.scene.Node;


import javax.swing.text.html.ImageView;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;

public class CattleController extends CattleMainController implements Initializable {
    private ObservableList<Animal> animalList = FXCollections.observableArrayList();

    private ObservableList<Animal> temp = FXCollections.observableArrayList();

    @FXML
    private Pane sellPane;

    @FXML
    private javafx.scene.image.ImageView sellImage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private javafx.scene.image.ImageView deleteImage;

    @FXML
    private Pane deletePane;

    @FXML
    private Pane bottomPane;

    @FXML
    private TextField inputCattleID;

    @FXML
    private TextField inputSex;

    @FXML
    private TextField inputHealthIndex;

    @FXML
    private TextField inputWeight;

    @FXML
    private TextField inputSource;

    @FXML
    private TextField inputDateImport;

    @FXML
    private TableView<Animal> tableList;

    @FXML
    private TableColumn<Animal, String> cattleID;

    @FXML
    private TableColumn<Animal, String> animalID;

    @FXML
    private TableColumn<Animal, ?> sex;

    @FXML
    private TableColumn<Animal, ?> healthIndex;

    @FXML
    private TableColumn<Animal, ?> weight;

    @FXML
    private TableColumn<Animal, ?> source;

    @FXML
    private TableColumn<Animal, ?> dateImport;

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
    private MaterialDesignIconView iconDialog;

    @FXML
    private Pane upPane;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpRipplers();
        tableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            getData();
            showTable();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void setUpRipplers() {
        JFXRippler rippler = new JFXRippler(deleteImage, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler rippler1 = new JFXRippler(sellImage, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        sellPane.getChildren().add(rippler1);
        deletePane.getChildren().add(rippler);
    }

    public void getData() throws Exception {
        animalList.clear();
        HashMap<String, String> a = new HashMap<>();
        a.put(Var.KEY_ANIMAL_ID, String.valueOf(super.getAnimalID()));
        a.put(Var.KEY_USER, new Var().getUserName());

        JSONArray animals = new jsonDeal().loadJSON(Var.METHOD_ANIMAL, a);
        // System.out.println(animals.toString());
        for (Object object : animals) {
            JSONObject jsonValue = (JSONObject) object;
            Animal animal = new Animal();
            animal.setAnimalID(Integer.parseInt((String) jsonValue.get("Animal_ID")));
            animal.setCattleID(Integer.parseInt((String) jsonValue.get("Id")));
            animal.setHealthIndex(Integer.parseInt((String) jsonValue.get("Health_Index")));
            animal.setSexCode(Integer.parseInt((String) jsonValue.get("Sex")));
            animal.setSex();
            animal.setSource((String) jsonValue.get("Source"));
            animal.setDateImport((String) jsonValue.get("Date_Import"));
            animal.setWeight(Double.parseDouble((String) jsonValue.get("Weight")));

            animalList.add(animal);
        }
    }

    public void showTable() {

        tableList.setEditable(true);
        animalID.setCellValueFactory(new PropertyValueFactory<>("animalID"));
        cattleID.setCellValueFactory(new PropertyValueFactory<>("cattleID"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        healthIndex.setCellValueFactory(new PropertyValueFactory<>("healthIndex"));
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        source.setCellValueFactory(new PropertyValueFactory<>("source"));
        dateImport.setCellValueFactory(new PropertyValueFactory<>("dateImport"));
        tableList.setItems(animalList);

    }

    public void goHome() {
        try {
            openStage("/MainScreen/MainBoard.fxml");
        } catch (Exception e) {
            System.out.println("MainScreen problem");
        }
    }

    public void goBack() {
        try {
            openStage("CattleMain.fxml");
        } catch (Exception e) {
            System.out.println("Cattle main problem");
        }
    }

    public void openStage(String fxml) throws Exception {
        Stage currentStage = (Stage) tableList.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        Stage window = new Stage(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.show();
        currentStage.hide();
    }

    public void addAnimal() {
        JSONObject jsonAnimal = new JSONObject();

        HashMap<String, String> requestMap = new HashMap<>();
        Animal animal = new Animal();
        animal.setAnimalID(super.getAnimalID());

        requestMap.put(Var.KEY_ANIMAL_ID, String.valueOf(super.getAnimalID()));
        String sex = inputSex.getText();
        // System.out.println(sex);
        int codeSex;
        if (sex.equals("male")) {
            codeSex = 1;
        } else
            codeSex = 0;

        requestMap.put(Var.KEY_SEX, String.valueOf(codeSex));

        requestMap.put(Var.KEY_DATE, inputDateImport.getText());

        requestMap.put(Var.KEY_HEALTH_INDEX, inputHealthIndex.getText());
        requestMap.put(Var.KEY_WEIGHT, inputWeight.getText());
        requestMap.put(Var.KEY_SOURCE, inputSource.getText());
        requestMap.put(Var.KEY_USER, new Var().getUserName());

        // jsonAnimal.put(Var.KEY_METHOD, Var.METHOD_ADD);

        try {
            JSONObject object = new jsonDeal().loadJSON(Var.METHOD_ADD, requestMap);
            boolean add = (boolean) object.get("add");

            if (add == true) {
                //  animalList.clear();
                getData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("SUCCESSFULLY");
                alert.setContentText("You have just add an animal to list");
                Button button = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
                button.setDefaultButton(true);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("You have a problem related to connection with server");
            alert.setHeaderText("Connection error");
            alert.showAndWait();
        }
    }


    private static JFXPopup staticJFXpopup = new JFXPopup();

    @FXML
    public void openDialog(MouseEvent event) {
        JFXPopup jfxPopup = new JFXPopup();
        jfxPopup.setPopupContent(popUpPane);
        jfxPopup.show(upPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX() + 10, event.getY() + 70);
        staticJFXpopup = jfxPopup;
    }

    @FXML
    public void showListsNode(MouseEvent event) {
        try {
            if (staticJFXpopup.isShowing()) staticJFXpopup.hide();
            Parent root = FXMLLoader.load(getClass().getResource("AddListDialog.fxml"));
            Scene scene = new Scene(root);
            Stage window = new Stage(StageStyle.DECORATED);
            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
            getData();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    public void showBookingNode() {
    }

    @FXML
    public void deleteAll() {
        HashMap<String, String> request = new HashMap<>();
        JSONArray jsonArray = new JSONArray();
        for (Animal animal : animalList) {
            jsonArray.add(animal.getCattleID());
        }
        ;
        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_ARRAY_ID, jsonArray.toString());
        try {
            JSONObject json = new jsonDeal().loadJSON(Var.METHOD_DELETE, request);
            boolean result = (boolean) json.get("delete");
            if (result == true) {
                getData();
                new AlertConfigure().loadAlertInfo("SUCCESSFUL", "You have just delted all animal");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void deleteAnimal() {
        HashMap<String, String> request = new HashMap<>();

        ObservableList<Animal> selectedAnimals = tableList.getSelectionModel().getSelectedItems();
        JSONArray jsonArrayRequest = new JSONArray();
        for (Animal temp : selectedAnimals) {
            jsonArrayRequest.add(temp.getCattleID());
        }
        ;
        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_ARRAY_ID, jsonArrayRequest.toString());

        try {
            JSONObject jsonObject = new jsonDeal().loadJSON(Var.METHOD_DELETE, request);
            boolean result = (boolean) jsonObject.get("delete");
            if (result == true) {
                animalList.clear();
                getData();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("The Farm Message");
                alert.setHeaderText("Delete successfully");
                alert.setContentText("You have just delete some animals");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("The Farm Message");
                alert.setHeaderText("Request Failure");
                alert.setContentText("Request you have just sent get some errors");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    public void sellAnimal(MouseEvent event) {
        // System.out.println("A");
        //System.out.println("" + event.getClickCount());
        HashMap<String, String> request = new HashMap<>();
        temp = tableList.getSelectionModel().getSelectedItems();

        JSONArray jsonArray = new JSONArray();
        for (Animal test : temp) {
            jsonArray.add(test.getCattleID());
        }

        request.put(Var.KEY_USER, new Var().getUserName());
        request.put(Var.KEY_ARRAY_ID, jsonArray.toString());
        try {
            JSONObject jsonResult = new jsonDeal().loadJSON(Var.METHOD_SELL, request);
            getData();
            boolean result = (boolean) jsonResult.get("delete");
            if (result == true) {
                String amount = (String) jsonResult.get("total_money");
                new AlertConfigure().loadAlertInfo("SUCCESSFUL!", "You have get " + amount + " from selling");
                //System.out.println("B");
            } else
                new AlertConfigure().loadAlertWarning("FAILURE!", "Fail to sell animals");
        } catch (Exception e) {
            System.out.println("JSON Error");
        }
        //System.out.println("Hello world");
    }
}
