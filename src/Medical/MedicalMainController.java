package Medical;

import Cattle.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MedicalMainController implements Initializable {

    public void initialize(URL url, ResourceBundle resourceBundle){
        initTable();
        getData(Var.METHOD_GET_MEDICAL_ANIMALS, animalList);
        getData(Var.METHOD_GET_SICK_ANIMALS, animalSickList);

    }




    @FXML
    private TableView<Animal> animalTable;

    @FXML
    private TableColumn<Animal, ?> cattleIDColumn;

    @FXML
    private TableColumn<Animal, ?> animalIDColumn;

    @FXML
    private TableColumn<Animal, ?> weightColumn;

    @FXML
    private TableColumn<Animal, ?> tempratureColumn;

    @FXML
    private Pane upPane;

    @FXML
    private TableView<Animal> animalSickTable;

    @FXML
    private TableColumn<Animal, ?> sickCattleIDColumn;

    @FXML
    private TableColumn<Animal, ?> sickAnimalIDColumn;

    @FXML
    private TableColumn<Animal, ?> sickWeightColumn;

    @FXML
    private TableColumn<Animal, ?> sickTemperature;

    ObservableList<Animal> animalList = FXCollections.observableArrayList();
    ObservableList<Animal> animalSickList = FXCollections.observableArrayList();

    public void initTable(){
        cattleIDColumn.setCellValueFactory(new PropertyValueFactory<>("cattleID"));
        animalIDColumn.setCellValueFactory(new PropertyValueFactory<>("animalID"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tempratureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        animalTable.setEditable(true);
        animalTable.setItems(animalList);

        sickCattleIDColumn.setCellValueFactory(new PropertyValueFactory<>("cattleID"));
        sickAnimalIDColumn.setCellValueFactory(new PropertyValueFactory<>("animalID"));
        sickWeightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        sickTemperature.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        animalSickTable.setEditable(true);
        animalSickTable.setItems(animalSickList);

    }



    public void getData(int code, ObservableList<Animal> list){
        try{
            list.clear();

            HashMap<String, String> request = new HashMap<>();
            request.put(Var.KEY_USER, new Var().getUserName());
            JSONArray jsonArray = new jsonDeal().loadJSON(code, request);
            for( Object temp : jsonArray){
                JSONObject json = (JSONObject)temp;
                Animal animal = new Animal();
                animal.setAnimalID(Integer.parseInt((String) json.get("Animal_ID")));
                animal.setCattleID(Integer.parseInt((String)json.get("Id")));
                animal.setWeight(Double.parseDouble((String)json.get("Weight")));
                animal.setTemperature(Double.parseDouble((String)json.get("Temperature")));
                list.add(animal);
            }


        }catch (Exception e){
            System.out.println("JSON error");
        }
    }



    @FXML
    void goBack(MouseEvent event) {
        try {
            openStage("/MainScreen/MainBoard.fxml");
        }
        catch (Exception e){
            System.out.println("MainBoard Error");
        }
    }

    @FXML
    void goHome(MouseEvent event) {

    }

    @FXML
    void reloadMedicalStatus(MouseEvent event) {

    }

    @FXML
    void reloadSickAnimals(MouseEvent event) {

    }

    @FXML
    public void setSick(MouseEvent event){
        HashMap<String, String> request = new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        try {
            JSONObject json = new jsonDeal().loadJSON(Var.METHOD_SET_SICK, request);
            boolean result = (boolean) json.get("set_sick");

            if(result == true)
            {
                getData(Var.METHOD_GET_MANIMALS, animalList);
                getData(Var.METHOD_GET_SICK_ANIMALS, animalSickList);
            }
            else
                new AlertConfigure().loadAlertError("FAILURE", "Can not change data ");
        }catch (Exception e){
            System.out.println("JSON error");
        }
    }

    public void openStage(String fxml) throws Exception{
        Stage currentStage = (Stage) animalSickTable.getScene().getWindow();
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        window.initStyle(StageStyle.UNDECORATED);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.show();
        currentStage.hide();
    }

}
