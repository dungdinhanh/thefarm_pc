package Finance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Var;
import logic.jsonDeal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {

    @FXML
    private TableView<Finance> transactionTable;

    @FXML
    private TableColumn<Finance, ?> transactionIDColumn;

    @FXML
    private TableColumn<Finance, ?> typeColumn;

    @FXML
    private TableColumn<Finance, ?> actionColumn;

    @FXML
    private TableColumn<Finance, ?> moneyColumn;

    @FXML
    private TableColumn<Finance, ?> dateColumn;

    @FXML
    private TableColumn<Finance, ?> noteColumn;

    @FXML
    void goBack(MouseEvent event) {
        Stage stage = (Stage)transactionTable.getScene().getWindow();
        stage.hide();
    }
    ObservableList<Finance> listTrans = FXCollections.observableArrayList();

    public  void initialize(URL url, ResourceBundle resourceBundle){
        getData();
        initTable();

    }
    public void getData(){
        listTrans.clear();
        HashMap<String, String> request= new HashMap<>();
        request.put(Var.KEY_USER, new Var().getUserName());
        JSONArray jsonArray = new JSONArray();
        try {
             jsonArray = new jsonDeal().loadJSON(Var.METHOD_GET_TRANSACTION, request);

        }catch (Exception e){
            System.out.println("JSON error");
        }
        for(Object a : jsonArray){
            JSONObject jsonObject = (JSONObject) a;
            Finance finance = new Finance();
            finance.setTransactionID(Integer.parseInt((String)jsonObject.get("Transaction_ID")));
            finance.setAction((String)jsonObject.get("Action"));
            finance.setAmount(Double.parseDouble((String)jsonObject.get("Money")));
            finance.setDate((String)jsonObject.get("Trans_Date"));
            finance.setType((String)jsonObject.get("Type"));
            //finance.setNote((String)jsonObject.get("Note"));
            listTrans.add(finance);
        }
    }

    public void initTable(){
        transactionTable.setEditable(true);
        transactionIDColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
       // noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
      //  transactionTable.getColumns().addAll(transactionIDColumn, typeColumn, actionColumn, moneyColumn, dateColumn);
        transactionTable.setItems(listTrans);
    }

}
